import PropTypes from "prop-types";
import React, { useEffect, useReducer, useState } from "react";

function SensorsProvider(props) {
  function updateSensor(sensors, s) {
    return { ...sensors, [s.type]: s.value };
  }

  const [locationPermission, setLocationPermission] = useState(null);
  const [sensors, dispatchSensorUpdate] = useReducer(updateSensor, {});
  const [orientationPermission, setOrientationPermission] = useState(null);

  // ask for permission to use the sensors
  useEffect(() => {
    if (typeof orientationPermission !== "boolean") {
      if (!("permissions" in navigator)) {
        console.warn("Your browser does not support the permissions API");
        setOrientationPermission(false);
        return;
      }

      try {
        Promise.all([
          navigator.permissions.query({ name: "accelerometer" }),
          navigator.permissions.query({ name: "magnetometer" }),
          navigator.permissions.query({ name: "gyroscope" }),
        ]).then((results) => {
          setOrientationPermission(
            results.every((result) => result.state === "granted")
          );
        });
      } catch (e) {
        console.error(e);
        setOrientationPermission(false);
      }
    }
  }, [orientationPermission]);

  // install orientation sensor listeners
  useEffect(() => {
    if (!orientationPermission) {
      return;
    }

    const options = { frequency: 60, referenceFrame: "device" };
    const sensor = new AbsoluteOrientationSensor(options);

    sensor.addEventListener("reading", () => {
      const [x, y, z, w] = sensor.quaternion;

      dispatchSensorUpdate({
        type: "orientation",
        value: {
          x,
          y,
          z,
          w,
        },
      });
    });

    sensor.addEventListener("error", (error) => {
      if (error.name == "NotReadableError") {
        console.log("Sensor is not available.");
      }
    });

    sensor.start();

    return () => {
      sensor.stop();
    };
  }, [orientationPermission]);

  useEffect(() => {
    if (typeof locationPermission !== "boolean") {
      if ("geolocation" in navigator) {
        if ("permissions" in navigator) {
          navigator.permissions
            .query({ name: "geolocation" })
            .then((result) => {
              setLocationPermission(result.state !== "denied");
            });
        } else {
          setLocationPermission(true);
        }
      } else {
        setLocationPermission(false);
      }
    }
  }, [locationPermission]);

  // install geolocation listener
  useEffect(() => {
    if (!locationPermission) {
      return;
    }

    const watchId = navigator.geolocation.watchPosition(
      (pos) => {
        const {
          latitude,
          longitude,
          altitude,
          accuracy,
          altitudeAccuracy,
          heading,
          speed,
        } = pos.coords;

        dispatchSensorUpdate({
          type: "location",
          value: {
            latitude,
            longitude,
            altitude,
            accuracy,
            altitudeAccuracy,
            heading,
            speed,
          },
        });
      },
      (err) => {
        console.error("Error getting location", err);
        dispatchSensorUpdate({
          type: "location",
          value: null,
        });
      }
    );

    return () => {
      navigator.geolocation.clearWatch(watchId);
    };
  }, [locationPermission]);

  // publish sensor data
  useEffect(() => {
    if (typeof props.onChange === "function" && sensors) {
      props.onChange(sensors);
    }
  }, [sensors]);

  return null;
}

SensorsProvider.propTypes = {
  onChange: PropTypes.func.isRequired,
};

export default SensorsProvider;
