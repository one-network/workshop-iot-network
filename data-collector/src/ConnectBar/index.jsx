import React, { useState } from "react";
import "./style.css";

export default function ConnectBar(props) {
  const [name, setName] = useState(props.name);

  function handleNameChanged(event) {
    setName(event.target.value);
  }

  function handleFormSubmit(event) {
    props.onConnect(name);
    event.preventDefault();
  }

  return (
    <form className="connectbar" onSubmit={handleFormSubmit}>
      <div className="connectbar__userfield">
        <label>Name:</label>
        <input type="text" value={name} onChange={handleNameChanged} />
      </div>
      <input className="connectbar__connect" type="submit" value="Connect" />
    </form>
  );
}
