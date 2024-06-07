% Define the filename
filename = "waveheight.txt";

% Read the data from the file
data = dlmread(filename);

% Extract pitch, roll, and yaw from the data
waveHeight = data(:, 1);

% Plot the data
figure;
hold on;
plot(waveHeight, 'r', 'DisplayName', 'Wave Height');
hold off;

% Add title and labels
title('Wave Height Data');
xlabel('Sample Index');
ylabel('Feet');
legend show;
grid on;
