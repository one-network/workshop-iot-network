import React from "react";
import "./style.css";

export default function Message(props) {
  return (
    <div className="message">
      <div className="message__from">{props.message.from}</div>
      <div className="message__text">{props.message.message}</div>
      <div className="message__received_at">{props.message.received_at.toISOString()}</div>
    </div>
  );
}
