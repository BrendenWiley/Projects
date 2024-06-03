file = 'C:\Users\brend\Desktop\RadarData\1946pw128dup32_001_s002.mat';
load(file);

% Variables
azang = [gwx.hdr_scan.az_ang_d]; %azimuth angles
elang = unique([gwx.hdr_scan.el_ang_d]); %elevation angles
range = [gwx.hdr_cpi.rng_m];
rangeM = range(1:size(gwx.mfOut,1)); 
rangeKM = rangeM / 1000; %get range in km
cf = unique([gwx.hdr_wf.freq_center_Hz]);
lambda = 3e8/cf(1);
prf = unique([gwx.hdr_cpi.prf_Hz]);
sf = unique([gwx.hdr_sys.f_samp_Hz]);

outputH = gwx.mfOut(:, 1:2:end,:);



[rangeG, azangG] = meshgrid(rangeKM, azang);
[az,ra] = pol2cart(deg2rad(azangG.'), rangeG.');

%fft on each cpi integrated horizontal pulse
f = fftshift(fft(pulseH));

%convert fft to velocity
velocity = dop2speed(f,lambda);

%maximum
figure(); %plot horizontal data
pcolor(ra, az, f);
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
