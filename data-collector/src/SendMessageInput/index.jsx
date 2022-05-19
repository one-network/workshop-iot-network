import React, { useState } from "react";
import "./style.css";

export default function SendMessageInput(props) {
  const [message, setMessage] = useState("");

  function handleMessageChanged(e) {
    setMessage(e.target.value);
  }

  function handleSendMessage(e) {
    e.preventDefault();
    props.onSendMessage(message);
    setMessage("");
  }

  return (
    <form className="sendmessage" onSubmit={handleSendMessage}>
      <input
        type="text"
        className="sendmessage__message"
        placeholder="Type your message here..."
        value={message}
        onChange={handleMessageChanged}
      />
      <input
        type="submit"
        className="sendmessage__send"
        value="Send"
      />
    </form>
  );
}
