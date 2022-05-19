import React, { useEffect } from "react";
import Message from "../Message";
import "./style.css";

export default function MessageList(props) {
  const messages = props.messages?.map((msg) => (
    <Message key={msg.id} message={msg} />
  ));

  return <div className="messagelist">{messages}</div>;
}
