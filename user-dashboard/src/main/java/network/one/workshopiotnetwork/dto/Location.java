package network.one.workshopiotnetwork.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class Location {

    @JsonProperty("accuracy")
    private Double accuracy;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("altitude")
    private Double altitude;

    @JsonProperty("altitudeAccuracy")
    private Double altitudeAccuracy;

    @JsonProperty("heading")
    private Double heading;

    @JsonProperty("speed")
    private Double speed;

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(final Double accuracy) {
        this.accuracy = accuracy;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(final Double altitude) {
        this.altitude = altitude;
    }

    public Double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    public void setAltitudeAccuracy(final Double altitudeAccuracy) {
        this.altitudeAccuracy = altitudeAccuracy;
    }

    public Double getHeading() {
        return heading;
    }

    public void setHeading(final Double heading) {
        this.heading = heading;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(final Double speed) {
        this.speed = speed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accuracy == null) ? 0 : accuracy.hashCode());
        result = prime * result + ((altitude == null) ? 0 : altitude.hashCode());
        result = prime * result + ((altitudeAccuracy == null) ? 0 : altitudeAccuracy.hashCode());
        result = prime * result + ((heading == null) ? 0 : heading.hashCode());
        result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
        result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
        result = prime * result + ((speed == null) ? 0 : speed.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Location other = (Location) obj;
        if (accuracy == null) {
            if (other.accuracy != null)
                return false;
        } else if (!accuracy.equals(other.accuracy))
            return false;
        if (altitude == null) {
            if (other.altitude != null)
                return false;
        } else if (!altitude.equals(other.altitude))
            return false;
        if (altitudeAccuracy == null) {
            if (other.altitudeAccuracy != null)
                return false;
        } else if (!altitudeAccuracy.equals(other.altitudeAccuracy))
            return false;
        if (heading == null) {
            if (other.heading != null)
                return false;
        } else if (!heading.equals(other.heading))
            return false;
        if (latitude == null) {
            if (other.latitude != null)
                return false;
        } else if (!latitude.equals(other.latitude))
            return false;
        if (longitude == null) {
            if (other.longitude != null)
                return false;
        } else if (!longitude.equals(other.longitude))
            return false;
        if (speed == null) {
            if (other.speed != null)
                return false;
        } else if (!speed.equals(other.speed))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Location [accuracy=" + accuracy + ", altitude=" + altitude + ", altitudeAccuracy="
                + altitudeAccuracy + ", heading=" + heading + ", latitude=" + latitude + ", longitude=" + longitude
                + ", speed=" + speed + "]";
    }

}