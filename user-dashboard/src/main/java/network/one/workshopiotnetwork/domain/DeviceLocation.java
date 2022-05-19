package network.one.workshopiotnetwork.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "device_location")
public class DeviceLocation implements Serializable, Persistable<String> {

    @Id
    private String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn(name = "name", referencedColumnName = "name")
    private Device device;

    @Version
    @Column
    private Integer version;

    @Column
    private Double accuracy;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private Double altitude;

    @Column
    private Double altitudeAccuracy;

    @Column
    private Double heading;

    @Column
    private Double speed;

    @Override
    public String getId() {
        if (device == null) {
            return null;
        }

        return device.getId();
    }

    @Override
    public boolean isNew() {
        return version == null;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(final Device device) {
        this.device = device;

        if (this.device != null) {
            this.name = this.device.getName();
        }
    }

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
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final DeviceLocation other = (DeviceLocation) obj;

        if (device == null) {
            if (other.device != null) {
                return false;
            }
        } else if (!device.equals(other.device)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "DeviceLocation [accuracy=" + accuracy + ", altitude=" + altitude + ", altitudeAccuracy="
                + altitudeAccuracy + ", device=" + name + ", heading=" + heading + ", latitude=" + latitude
                + ", longitude=" + longitude + ", speed=" + speed + "]";
    }

}
