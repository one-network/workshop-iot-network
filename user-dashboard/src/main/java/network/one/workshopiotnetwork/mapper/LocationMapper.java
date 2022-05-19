package network.one.workshopiotnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import network.one.workshopiotnetwork.domain.DeviceLocation;
import network.one.workshopiotnetwork.dto.Location;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    void mapDeviceLocation(
            Location location,
            @MappingTarget DeviceLocation devLocation);

}
