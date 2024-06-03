file = 'C:\Users\brend\Desktop\RadarData\1946pw128dup32_001_s002.mat';
load(file);

% Variables
azang = [gwx.hdr_scan.az_ang_d]; %azimuth angles
elang = unique([gwx.hdr_scan.el_ang_d]); %elevation angles
range = [gwx.hdr_cpi.rng_m];
rangeM = range(1:size(gwx.mfOut,1)); 
rangeKM = rangeM / 1000; %get range in km

outputH = gwx.mfOut(:, 1:2:end,:);
outputV = gwx.mfOut(:, 2:2:end,:);

integrateH = squeeze(sqrt(sum(abs(outputH).^2, 2)));
integrateV = squeeze(sqrt(sum(abs(outputV).^2, 2)));

pulseH = 10*log10(integrateH); %calculate dB value
pulseV = 10*log10(integrateV); 

medH = median(median(pulseH)); %find median
medV = median(median(pulseV)); 

pulseH(pulseH < medH) = medH; %replace values below pulse data limit with median
pulseV(pulseV < medV) = medV;

[rangeG, azangG] = meshgrid(rangeKM, azang);
[az,ra] = pol2cart(deg2rad(azangG.'), rangeG.');

pulseTotal = pulseH - pulseV;

figure(); %plot horizontal data
pcolor(ra, az, pulseTotal);
title('136cpi/100pulsesH at 3deg EL');
bar = colorbar;
colormap(jet) % Blue to red colormap
shading flat;
% Set the x-axis properties
set(gca, 'XTick', [-60 -30 0 30 60]);
set(gca, 'XTickLabel', {'-60', '-30', '0', '30', '60'});
% Set the y-axis properties
ylabel('Range (km)');
tickval =  1:round(rangeKM(end));
set(gca, 'YTick', tickval);
set(gca, 'YTickLabel', tickval);
title(bar, 'dB');
grid on;
