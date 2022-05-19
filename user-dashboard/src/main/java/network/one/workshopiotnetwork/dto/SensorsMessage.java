package network.one.workshopiotnetwork.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorsMessage extends Message {

    @JsonProperty("location")
    private Location location;

    @JsonProperty("orientation")
    private Orientation orientation;

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(final Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SensorsMessage other = (SensorsMessage) obj;
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
        return "SensorsMessage [super= " + super.toString() + ", location=" + location + ", orientation=" + orientation
                + "]";
    }

}
