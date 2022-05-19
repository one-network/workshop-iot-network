package network.one.workshopiotnetwork.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DeviceLocation.class)
public abstract class DeviceLocation_ {

	public static volatile SingularAttribute<DeviceLocation, Double> altitude;
	public static volatile SingularAttribute<DeviceLocation, Double> heading;
	public static volatile SingularAttribute<DeviceLocation, Double> latitude;
	public static volatile SingularAttribute<DeviceLocation, String> name;
	public static volatile SingularAttribute<DeviceLocation, Double> accuracy;
	public static volatile SingularAttribute<DeviceLocation, Double> altitudeAccuracy;
	public static volatile SingularAttribute<DeviceLocation, Device> device;
	public static volatile SingularAttribute<DeviceLocation, Integer> version;
	public static volatile SingularAttribute<DeviceLocation, Double> speed;
	public static volatile SingularAttribute<DeviceLocation, Double> longitude;

	public static final String ALTITUDE = "altitude";
	public static final String HEADING = "heading";
	public static final String LATITUDE = "latitude";
	public static final String NAME = "name";
	public static final String ACCURACY = "accuracy";
	public static final String ALTITUDE_ACCURACY = "altitudeAccuracy";
	public static final String DEVICE = "device";
	public static final String VERSION = "version";
	public static final String SPEED = "speed";
	public static final String LONGITUDE = "longitude";

}

