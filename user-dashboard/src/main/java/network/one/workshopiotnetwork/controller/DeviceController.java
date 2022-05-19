package network.one.workshopiotnetwork.controller;

import static network.one.workshopiotnetwork.specifications.DeviceSpecifications.withinBounds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import network.one.workshopiotnetwork.domain.Device;
import network.one.workshopiotnetwork.dto.BoundingBox;
import network.one.workshopiotnetwork.dto.DeviceInformation;
import network.one.workshopiotnetwork.mapper.DeviceMapper;
import network.one.workshopiotnetwork.repository.DeviceRepository;

@RestController
public class DeviceController {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping(path = "/devices")
    public ResponseEntity<List<DeviceInformation>> listDevices(@RequestParam("bbox") final String bboxStr) {
        final BoundingBox bbox = BoundingBox.fromString(bboxStr);

        if (bbox == null) {
            return ResponseEntity.badRequest().build();
        }

        final List<Device> devices = deviceRepository.findAll(withinBounds(bbox));
        final List<DeviceInformation> response = DeviceMapper.INSTANCE.map(devices);

        return ResponseEntity.ok(response);
    }

}
