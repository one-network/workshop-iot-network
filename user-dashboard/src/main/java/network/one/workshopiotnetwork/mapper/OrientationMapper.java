package network.one.workshopiotnetwork.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import network.one.workshopiotnetwork.domain.DeviceOrientation;
import network.one.workshopiotnetwork.dto.Orientation;

@Mapper
public interface OrientationMapper {

    OrientationMapper INSTANCE = Mappers.getMapper(OrientationMapper.class);

    void mapDeviceOrientation(
            Orientation orientation,
            @MappingTarget DeviceOrientation deviceOrientation);

}
