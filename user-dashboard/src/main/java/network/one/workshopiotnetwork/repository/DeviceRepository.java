package network.one.workshopiotnetwork.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import network.one.workshopiotnetwork.domain.Device;

public interface DeviceRepository extends CrudRepository<Device, String>, JpaSpecificationExecutor<Device> {

}
