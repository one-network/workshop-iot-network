package network.one.workshopiotnetwork.mapper;

import javax.annotation.processing.Generated;
import network.one.workshopiotnetwork.domain.DeviceLocation;
import network.one.workshopiotnetwork.dto.Location;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-19T16:05:23+0100",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 11.0.15 (Oracle Corporation)"
)
public class LocationMapperImpl implements LocationMapper {

    @Override
    public void mapDeviceLocation(Location location, DeviceLocation devLocation) {
        if ( location == null ) {
            return;
        }

        devLocation.setAccuracy( location.getAccuracy() );
        devLocation.setLatitude( location.getLatitude() );
        devLocation.setLongitude( location.getLongitude() );
        devLocation.setAltitude( location.getAltitude() );
        devLocation.setAltitudeAccuracy( location.getAltitudeAccuracy() );
        devLocation.setHeading( location.getHeading() );
        devLocation.setSpeed( location.getSpeed() );
    }
}
