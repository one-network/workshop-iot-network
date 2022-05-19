import PropTypes from "prop-types";
import React, { useEffect, useReducer, useState } from "react";

function SensorsProvider(props) {
  function updateSensor(sensors, s) {
    return { ...sensors, [s.type]: s.value };
  }
  const [locationPermission, setLocationPermission] = useState(null);
  const [sensors, dispatchSensorUpdate] = useReducer(updateSensor, {});

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
