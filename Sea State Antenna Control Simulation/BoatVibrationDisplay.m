function angleDisplay()
    dataFile = 'degreeValues.txt';  % Path to the text file with roll, pitch, yaw data
    updateInterval = 0.2;     % Update time
    hFig = figure('Name', 'Rotating Platform', 'CloseRequestFcn', @closeFigure);
    axis equal;
    grid on;
    hold on;
    xlabel('X');
    ylabel('Y');
    zlabel('Z');
    view(3);

    % Create a platform
    [X, Y, Z] = create_platform();
    hPlatform = surf(X, Y, Z, 'FaceColor', 'flat', 'CData', ones(size(Z)));

    hTimer = timer('Period', updateInterval, 'ExecutionMode', 'fixedRate', ...
                   'TimerFcn', @(~, ~) updatePlatform(hPlatform, dataFile));
    start(hTimer);

    function updatePlatform(hPlatform, dataFile)
        % Read angles from the text file
        angles = read_angles(dataFile);
        if isempty(angles)
            stop(hTimer);
            disp('No more data to read.');
            return;
        end

        % Extract roll, pitch, yaw
        roll = angles(1);
        pitch = angles(2);
        yaw = angles(3);

        % Create rotation matrices
        Rx = [1 0 0; 0 cosd(roll) -sind(roll); 0 sind(roll) cosd(roll)];
        Ry = [cosd(pitch) 0 sind(pitch); 0 1 0; -sind(pitch) 0 cosd(pitch)];
        Rz = [cosd(yaw) -sind(yaw) 0; sind(yaw) cosd(yaw) 0; 0 0 1];

        % Combine rotation matrices
        R = Rz * Ry * Rx;

        % Rotate the platform
        newVertices = (R * [X(:)'; Y(:)'; Z(:)'])';
        hPlatform.XData = reshape(newVertices(:, 1), size(X));
        hPlatform.YData = reshape(newVertices(:, 2), size(Y));
        hPlatform.ZData = reshape(newVertices(:, 3), size(Z));

        % Display the current angles
        title(sprintf('Roll: %.2f°, Pitch: %.2f°, Yaw: %.2f°', roll, pitch, yaw));
    end

    function angles = read_angles(filename)
        persistent fileID dataIndex data
        if isempty(fileID)
            fileID = fopen(filename, 'r');
            if fileID == -1
                error('Cannot open file: %s', filename);
            end
            data = fscanf(fileID, '%f %f %f', [3 Inf])';
            dataIndex = 1;
        end
        if dataIndex > size(data, 1)
            angles = [];
        else
            angles = data(dataIndex, :);
            dataIndex = dataIndex + 1;
        end
    end

    function closeFigure(~, ~)
        stop(hTimer);
        delete(hTimer);
        fclose('all');
        delete(hFig);
    end
end

function [X, Y, Z] = create_platform()
    % Create a simple square platform
    [X, Y] = meshgrid(-1:0.1:1, -1:0.1:1);
    Z = zeros(size(X));
end
