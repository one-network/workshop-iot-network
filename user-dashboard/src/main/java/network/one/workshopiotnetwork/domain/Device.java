package network.one.workshopiotnetwork.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.domain.Persistable;

import network.one.workshopiotnetwork.dto.Location;
import network.one.workshopiotnetwork.mapper.LocationMapper;

@Entity
@Table(name = "device")
public class Device implements Serializable, Persistable<String> {

    @Id
    private String name;

    @Version
    @Column
    private Integer version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_seen")
    private Date lastSeen;

    @OneToOne(mappedBy = "device", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private DeviceLocation location;

    @Override
    public String getId() {
        return name;
    }

    @Override
    public boolean isNew() {
        return version == null;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(final Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public DeviceLocation getLocation() {
        return location;
    }

    public void addLocation(final DeviceLocation location) {
        this.location = location;

        if (this.location != null) {
            this.location.setDevice(this);
        }
    }
    
    public void updateLastSeen() {
        this.setLastSeen(new Date());
    }

    public void updateLocation(final Location location) {
        final DeviceLocation devLocation;

        if (this.location == null) {
            devLocation = new DeviceLocation();
            this.addLocation(devLocation);
        } else {
            devLocation = this.location;
        }

        LocationMapper.INSTANCE.mapDeviceLocation(location, devLocation);
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
        final Device other = (Device) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Device [lastSeen=" + lastSeen + ", location=" + location + ", name=" + name + "]";
    }

}
