package network.one.workshopiotnetwork.routes;

import java.util.Optional;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import network.one.workshopiotnetwork.domain.Device;
import network.one.workshopiotnetwork.dto.Message;
import network.one.workshopiotnetwork.dto.SensorsMessage;
import network.one.workshopiotnetwork.dto.TextMessage;
import network.one.workshopiotnetwork.repository.DeviceRepository;

@Component
public class MQTTRoutes extends RouteBuilder {

    private final DeviceRepository deviceRepository;

    @Autowired
    public MQTTRoutes(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void configure() throws Exception {
        from("paho:messages")
                .unmarshal().json(Message.class)
                .choice()
                .when(body().isInstanceOf(TextMessage.class)).to("direct:handleTextMessage")
                .when(body().isInstanceOf(SensorsMessage.class)).to("direct:handleSensorsMessage")
                .otherwise().to("direct:handleUnknownMessage")
                .routeId("handleDeviceMessageRoute");

        from("direct:handleTextMessage")
                .log(LoggingLevel.INFO, log, "Received text message: ${body}")
                .routeId("handleTextMessage");

        from("direct:handleSensorsMessage")
                .transacted()
                .log(LoggingLevel.INFO, log, "Received sensors message: ${body}")
                .process().body(SensorsMessage.class, this::updateDeviceInformation)
                .routeId("handleSensorsMessage");

        from("direct:handleUnknownMessage")
                .log(LoggingLevel.WARN, log, "Received invalid message, discarding.")
                .routeId("handleUnknownMessage");
    }

    private void updateDeviceInformation(final SensorsMessage message) {
        final String deviceName = message.getFrom();

        if (deviceName == null || deviceName.isBlank()) {
            log.warn("Device name cannot be null or empty. Discarding.");
            return;
        }

        final Device device = deviceRepository.findById(deviceName).orElseGet(() -> {
            final Device newDevice = new Device();
            newDevice.setName(deviceName);
            return newDevice;
        });

        Optional.ofNullable(message.getLocation()).ifPresent(device::updateLocation);
        Optional.ofNullable(message.getOrientation()).ifPresent(device::updateOrientation);
        device.updateLastSeen();

        deviceRepository.save(device);
    }

}
