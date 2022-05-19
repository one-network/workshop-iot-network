import React, { useEffect, useState } from "react";
import * as mqtt from "precompiled-mqtt";

class MessagePublisher {
  #clientId;
  #client;

  constructor(clientId, client) {
    this.#clientId = clientId;
    this.#client = client;
  }

  publishTextMessage(message) {
    this.publish({
      type: "text",
      message,
    });
  }

  publishSensorsMessage(message) {
    this.publish({
      type: "sensors",
      ...message,
    });
  }

  publish(msg) {
    if (!this.#clientId) {
      return;
    }

    const message = {
      id: crypto.randomUUID(),
      from: this.#clientId,
      created_at: new Date(),
      ...msg,
    };

    this.#client.publish("messages", JSON.stringify(message));
  }
}

export default function MessageStore(props) {
  const [client, setClient] = useState(null);

  useEffect(() => {
    if (!props.clientId) {
      return;
    }

    console.debug("Connecting to MQTT broker...");

    let newClient;

    try {
      newClient = mqtt.connect(`wss://${location.host}/mosquitto/`);

      console.debug("Setting up handlers");

      newClient.on("connect", () => {
        console.debug("Connected to MQTT.");
        console.debug("Subscribing to topic...");
        newClient.subscribe("messages");
      });

      setClient(newClient);

      if (typeof props.onConnectionChanged === "function") {
        props.onConnectionChanged(
          new MessagePublisher(props.clientId, newClient)
        );
      }
    } catch (e) {
      console.error(e);
      alert("Error connecting to server");
    }

    return () => {
      if (newClient) {
        console.debug("Disconnecting from MQTT broker...");
        newClient.end();
      }
    };
  }, [props.clientId]);

  useEffect(() => {
    if (!client) {
      return;
    }

    client.on("message", (_, message) => {
      props.onMessageReceived(
        Object.assign({}, JSON.parse(message), {
          received_at: new Date(),
        })
      );
    });
  }, [client]);

  return null;
}
