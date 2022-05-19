package network.one.workshopiotnetwork.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Device.class)
public abstract class Device_ {

	public static volatile SingularAttribute<Device, Date> lastSeen;
	public static volatile SingularAttribute<Device, String> name;
	public static volatile SingularAttribute<Device, DeviceLocation> location;
	public static volatile SingularAttribute<Device, Integer> version;

	public static final String LAST_SEEN = "lastSeen";
	public static final String NAME = "name";
	public static final String LOCATION = "location";
	public static final String VERSION = "version";

}

