package network.one.workshopiotnetwork.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import network.one.workshopiotnetwork.domain.Device;
import network.one.workshopiotnetwork.dto.DeviceInformation;

@Mapper
public interface DeviceMapper {

    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    @Mapping(target = "device.name", source = "name")
    DeviceInformation map(Device device);

    List<DeviceInformation> map(Collection<Device> devices);

}
