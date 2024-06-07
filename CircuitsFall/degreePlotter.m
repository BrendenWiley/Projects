% Define the filename
filename = "value.txt";
% Read the data from the file
data = dlmread(filename);

% Extract pitch, roll, and yaw from the data
pitch = data(:, 1);
roll = data(:, 2);
yaw = data(:, 3);

% Plot the data
figure;
hold on;
plot(pitch, 'r', 'DisplayName', 'Pitch');
plot(roll, 'g', 'DisplayName', 'Roll');
plot(yaw, 'b', 'DisplayName', 'Yaw');
hold off;

% Add title and labels
title('Pitch, Roll, and Yaw Data');
xlabel('Sample Index');
ylabel('Degrees');
legend show;
grid on;
