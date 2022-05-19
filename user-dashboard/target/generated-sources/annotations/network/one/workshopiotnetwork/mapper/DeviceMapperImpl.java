package network.one.workshopiotnetwork.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import network.one.workshopiotnetwork.domain.Device;
import network.one.workshopiotnetwork.domain.DeviceLocation;
import network.one.workshopiotnetwork.dto.DeviceInformation;
import network.one.workshopiotnetwork.dto.DeviceRef;
import network.one.workshopiotnetwork.dto.Location;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-19T16:05:23+0100",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 11.0.15 (Oracle Corporation)"
)
public class DeviceMapperImpl implements DeviceMapper {

    @Override
    public DeviceInformation map(Device device) {
        if ( device == null ) {
            return null;
        }

        DeviceInformation deviceInformation = new DeviceInformation();

        deviceInformation.setDevice( deviceToDeviceRef( device ) );
        deviceInformation.setLocation( deviceLocationToLocation( device.getLocation() ) );

        return deviceInformation;
    }

    @Override
    public List<DeviceInformation> map(Collection<Device> devices) {
        if ( devices == null ) {
            return null;
        }

        List<DeviceInformation> list = new ArrayList<DeviceInformation>( devices.size() );
        for ( Device device : devices ) {
            list.add( map( device ) );
        }

        return list;
    }

    protected DeviceRef deviceToDeviceRef(Device device) {
        if ( device == null ) {
            return null;
        }

        DeviceRef deviceRef = new DeviceRef();

        deviceRef.setName( device.getName() );

        return deviceRef;
    }

    protected Location deviceLocationToLocation(DeviceLocation deviceLocation) {
        if ( deviceLocation == null ) {
            return null;
        }

        Location location = new Location();

        location.setAccuracy( deviceLocation.getAccuracy() );
        location.setLatitude( deviceLocation.getLatitude() );
        location.setLongitude( deviceLocation.getLongitude() );
        location.setAltitude( deviceLocation.getAltitude() );
        location.setAltitudeAccuracy( deviceLocation.getAltitudeAccuracy() );
        location.setHeading( deviceLocation.getHeading() );
        location.setSpeed( deviceLocation.getSpeed() );

        return location;
    }
}
