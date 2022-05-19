package network.one.workshopiotnetwork.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class DeviceInformation {

    @JsonProperty("device")
    private DeviceRef device;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("orientation")
    private Orientation orientation;

    public DeviceRef getDevice() {
        return device;
    }

    public void setDevice(final DeviceRef device) {
        this.device = device;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((device == null) ? 0 : device.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeviceInformation other = (DeviceInformation) obj;
        if (device == null) {
            if (other.device != null)
                return false;
        } else if (!device.equals(other.device))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (orientation == null) {
            if (other.orientation != null)
                return false;
        } else if (!orientation.equals(other.orientation))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DeviceInformation [device=" + device + ", location=" + location + ", orientation=" + orientation + "]";
    }

}
