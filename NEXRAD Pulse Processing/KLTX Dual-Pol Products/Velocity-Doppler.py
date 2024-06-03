from datetime import datetime, timedelta
import cartopy.crs as ccrs
import cartopy.feature as cfeature
import matplotlib.pyplot as plt
from metpy.plots import colortables, USSTATES, USCOUNTIES
import numpy as np
from pyproj import Proj
from siphon.catalog import TDSCatalog
import xarray as xr
import warnings
import pytz
warnings.filterwarnings("ignore")
date = datetime.utcnow()
data_url = (f'https://thredds.ucar.edu/thredds/catalog/nexrad/level2/KTLX/{date:%Y%m%d}/catalog.html')
x = TDSCatalog(data_url)

d = x.datasets[7] #change arraynum to get different timestamp on 5/11

obj = xr.open_dataset(d.access_urls['OPENDAP'], decode_times=False, decode_coords=False, mask_and_scale=True)
station = obj.Station
slat = obj.StationLatitude
slon = obj.StationLongitude
elevation = obj.StationElevationInMeters
vtime = datetime.strptime(obj.time_coverage_start, '%Y-%m-%dT%H:%M:%SZ')

ctime = vtime.astimezone(pytz.timezone('US/Central'))
                                         
d = Proj(f"+proj=stere +lat_0={slat} +lat_ts={slat} +lon_0={slon} +ellps=WGS84 +units=m")

sweep = 0
range = obj.distanceV_HI.values                #dop
azimuth = obj.azimuthV_HI.values[sweep]        #dop

dop = obj.RadialVelocity_HI.values[sweep]      #dop

x = range * np.sin(np.deg2rad(azimuth))[:, None]
y = range * np.cos(np.deg2rad(azimuth))[:, None]

longitude, latitude = d(x, y, inverse=True)

colormap = colortables.get_colortable('NWSVelocity') #dop

figure, axis = plt.subplots(1, 1, figsize=(10, 9), subplot_kw=dict(projection=ccrs.PlateCarree()))

image = axis.pcolormesh(longitude, latitude, dop, vmin=-20, vmax = 20, cmap=colormap) #dop


plt.colorbar(image, aspect=50, pad=0)

axis.add_image

axis.set_aspect('equal', 'datalim')
axis.add_feature(USCOUNTIES.with_scale('5m'), edgecolor='darkgrey')
axis.add_feature(USSTATES.with_scale('5m'))

plt.title(f'{station}: {obj.RadialVelocity_HI.name}', loc = 'left') #dop

plt.title(f'Valid Time: {ctime}', loc = 'right')
#axis.set_extent([-97.622172, -97.381014, 35.370951, 35.185512], ccrs.PlateCarree()) #OU ARRC view
axis.set_extent([-100, -95, 35, 36], ccrs.PlateCarree()) #oklahoma view
print(dir(obj))

plt.show()
