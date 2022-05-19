import React, { useState, useReducer } from "react";
import Header from "../Header";
import ConnectBar from "../ConnectBar";
import MessageStore from "../MessageStore";
import MessageList from "../MessageList";
import SendMessageInput from "../SendMessageInput";
import SensorsProvider from "../SensorsProvider";
import "./style.css";

export default function App() {
  function reduceDevice(devices, device) {
    return { ...devices, [device.from]: device };
  }

  const [name, setName] = useState("");
  const [devices, setDevice] = useReducer(reduceDevice, {});
  const [publisher, setPublisher] = useState(null);

  function handleConnect(newName) {
    setName(newName);
  }

  function handleMessageStoreConnectionChanged(publisher) {
    setPublisher(publisher);
  }

  function handleMessageReceived(message) {
    setDevice(message);
  }

  function handleSensorsChanged(sensors) {
    publisher?.publishSensorsMessage(sensors);
  }

  function sendMessage(message) {
    publisher?.publishTextMessage(message);
  }

  return (
    <React.Fragment>
      <Header />
      <ConnectBar name={name} onConnect={handleConnect} />
      <MessageStore
        clientId={name}
        onConnectionChanged={handleMessageStoreConnectionChanged}
        onMessageReceived={handleMessageReceived}
      />
      {name && publisher && (
        <React.Fragment>
          <MessageList messages={Object.values(devices)} />
          <SendMessageInput onSendMessage={sendMessage} />
          <SensorsProvider onChange={handleSensorsChanged} />
        </React.Fragment>
      )}
    </React.Fragment>
  );
}
