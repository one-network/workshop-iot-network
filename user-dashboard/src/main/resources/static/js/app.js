/*global L*/
(function () {
  "use strict";

  /****************************/
  /* VIEW CONTROLLERS         */
  /****************************/

  function fieldUpdater(rootEl, cmpPrefix, fields) {
    let curData;

    return (data) => {
      if (curData === data) {
        return;
      }

      if (!data) {
        rootEl.style.display = "none";
      } else {
        rootEl.style.display = "block";
      }

      fields.forEach((name) => {
        rootEl.querySelector(`.${cmpPrefix}__${name}`).innerText =
          data?.[name] || "N/A";
      });

      curData = data;
    };
  }

  function Orientation(el) {
    return fieldUpdater(el, "orientation", ["x", "y", "z", "w"]);
  }

  function Location(el) {
    return fieldUpdater(el, "location", [
      "latitude",
      "longitude",
      "accuracy",
      "altitude",
      "altitudeAccuracy",
      "heading",
      "speed",
    ]);
  }

  function Device(el) {
    return fieldUpdater(el, "device", ["name"]);
  }

  function StatusBar(el) {
    let curData;

    return (data) => {
      if (curData === data) {
        return;
      }

      el.innerText = data || "";

      curData = data;
    };
  }

  function Details(el) {
    const statusBar = StatusBar(el.querySelector(".statusbar"));
    const device = Device(el.querySelector(".device"));
    const location = Location(el.querySelector(".location"));
    const orientation = Orientation(el.querySelector(".orientation"));

    return (data) => {
      statusBar(data?.status);
      device(data?.device);
      location(data?.location);
      orientation(data?.orientation);
    };
  }

  function DeviceUpdatesPolling(dispatch) {
    let curData;
    let boundaries;

    function requestData() {
      if (!boundaries) {
        return;
      }

      dispatch({ type: "DEVICES_LOADING" });

      fetch(`devices?bbox=${boundaries}`)
        .then((response) => {
          if (!response.ok) {
            return [];
          }

          return response.json();
        })
        .then((devices) => {
          dispatch({
            type: "DEVICES_SHOW",
            devices,
          });
        });
    }

    window.setInterval(requestData, 1000);

    return (data) => {
      if (curData === data) {
        return;
      }

      boundaries = data?.bounds;

      curData = data;
    };
  }

  function DeviceMarkers(map, dispatch) {
    let curData;
    const markers = {};

    return (data) => {
      if (curData === data) {
        return;
      }

      data.forEach((device) => {
        const {
          device: { name },
          location: { latitude, longitude },
        } = device;

        if (!latitude || !longitude) {
          const marker = markers[name];

          if (marker) {
            marker.remove();
            delete markers[name];
          }
        } else {
          if (markers[name]) {
            markers[name].setLatLng([latitude, longitude]);
          } else {
            markers[name] = L.marker([latitude, longitude])
              .on("click", () => {
                dispatch({
                  type: "DEVICE_SELECT",
                  device: {
                    name,
                  },
                });
              })
              .addTo(map);
          }
        }
      });

      const visibleDevices = new Set(data.map((d) => d.device.name));

      Object.keys(markers)
        .filter((e) => !visibleDevices.has(e))
        .forEach((m) => {
          markers[m].remove();
          delete markers[m];
        });

      curData = data;
    };
  }

  function Map(el, dispatch) {
    const deviceUpdatesPolling = DeviceUpdatesPolling(dispatch);
    const map = L.map(el);

    function onBoundariesChanged() {
      const bounds = map.getBounds().toBBoxString();

      dispatch({
        type: "BOUNDARIES_CHANGED",
        bounds,
      });
    }

    map.on("moveend", onBoundariesChanged);

    map.setView([0, 0], 2);

    L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution:
        '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap contributors</a>',
      maxZoom: 19,
    }).addTo(map);

    const deviceMarkers = DeviceMarkers(map, dispatch);

    return (data) => {
      deviceUpdatesPolling(data);
      deviceMarkers(data?.devices);
    };
  }

  function App(el, dispatch) {
    const map = Map(el.querySelector(".map"), dispatch);
    const details = Details(el.querySelector(".details"));

    return (data) => {
      map(data?.map);
      details(data?.details);
    };
  }

  /****************************/
  /* STATE MANAGEMENT         */
  /****************************/

  function initReducer(state) {
    return {
      ...state,
      details: {
        ...state?.details,
        status: "Click on a device on the map to see more details.",
      },
    };
  }

  function boundariesChangedReducer(state, msg) {
    return {
      ...state,
      map: {
        ...state?.map,
        bounds: msg.bounds,
      },
    };
  }

  function getSelectedDevice(devices, selected) {
    return (devices || []).find((d) => d.device.name === selected);
  }

  function devicesReducer(state, msg) {
    switch (msg.type) {
      case "DEVICES_LOADING":
        return state;
      case "DEVICES_SHOW":
        return {
          ...state,
          map: {
            ...state?.map,
            devices: msg.devices,
          },
          details: {
            ...state?.details,
            ...getSelectedDevice(msg.devices, state.selected),
            status: `Last updated: ${new Date().toISOString()}`,
          },
        };
    }
    return state;
  }

  function deviceSelectReducer(state, msg) {
    return {
      ...state,
      selected: msg.device.name,
      details: {
        ...state.details,
        ...getSelectedDevice(state.map?.devices, msg.device.name),
      },
    };
  }

  function State() {
    let renderFn;
    let state = {};
    const queue = [];
    let requestId;

    function reduceMessage(msg) {
      switch (msg.type) {
        case "INIT":
          state = initReducer(state, msg);
          break;
        case "BOUNDARIES_CHANGED":
          state = boundariesChangedReducer(state, msg);
          break;
        case "DEVICES_SHOW":
        case "DEVICES_LOADING":
          state = devicesReducer(state, msg);
          break;
        case "DEVICE_SELECT":
          state = deviceSelectReducer(state, msg);
          break;
        default:
          throw `Unknown message type: ${msg.type}`;
      }
    }

    function reduceMessages() {
      queue.forEach((msg) => {
        try {
          reduceMessage(msg);
        } catch (e) {
          console.error(e);
        }
      });
      queue.length = 0;
    }

    function handleWork() {
      reduceMessages();
      renderFn(state, dispatch);
      requestId = null;
    }

    function dispatch(msg) {
      if (msg) {
        queue.push(msg);
      }

      if (!requestId) {
        requestId = window.requestAnimationFrame(handleWork);
      }
    }

    function setRenderFn(newRenderFn) {
      renderFn = newRenderFn;

      dispatch({
        type: "INIT",
      });
    }

    return {
      dispatch,
      setRenderFn,
    };
  }

  /****************************/
  /* ENTRYPOINT               */
  /****************************/
  function init() {
    const { setRenderFn, dispatch } = State();

    setRenderFn(App(document.getElementById("root"), dispatch));
  }

  window.addEventListener("load", init, false);
})();
