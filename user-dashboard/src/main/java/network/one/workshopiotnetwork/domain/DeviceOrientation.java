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
@Table(name = "device_orientation")
public class DeviceOrientation implements Serializable, Persistable<String> {

    @Id
    private String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn(name = "name", referencedColumnName = "name")
    private Device device;

    @Version
    @Column
    private Integer version;

    @Column
    private Double x;

    @Column
    private Double y;

    @Column
    private Double z;

    @Column
    private Double w;

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

    public Double getX() {
        return x;
    }

    public void setX(final Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(final Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(final Double z) {
        this.z = z;
    }

    public Double getW() {
        return w;
    }

    public void setW(final Double w) {
        this.w = w;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final DeviceOrientation other = (DeviceOrientation) obj;
        if (device == null) {
            if (other.device != null)
                return false;
        } else if (!device.equals(other.device))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DeviceOrientation [device=" + name + ", w=" + w + ", x=" + x + ", y=" + y + ", z=" + z + "]";
    }

}
