package network.one.workshopiotnetwork.specifications;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;

import org.springframework.data.jpa.domain.Specification;

import network.one.workshopiotnetwork.domain.Device;
import network.one.workshopiotnetwork.domain.DeviceLocation;
import network.one.workshopiotnetwork.domain.DeviceLocation_;
import network.one.workshopiotnetwork.domain.Device_;
import network.one.workshopiotnetwork.dto.BoundingBox;

public class DeviceSpecifications {

    public static Specification<Device> withinBounds(final BoundingBox bbox) {
        return (root, query, cb) -> {
            final Join<Device, DeviceLocation> location = root.join(Device_.location);
            final Path<Double> latitude = location.get(DeviceLocation_.latitude);
            final Path<Double> longitude = location.get(DeviceLocation_.longitude);

            return cb.and(
                    cb.ge(latitude, bbox.getSouthWest().getLatitude()),
                    cb.lt(latitude, bbox.getNorthEast().getLatitude()),
                    cb.ge(longitude, bbox.getSouthWest().getLongitude()),
                    cb.lt(longitude, bbox.getNorthEast().getLongitude()));
        };
    }

}
