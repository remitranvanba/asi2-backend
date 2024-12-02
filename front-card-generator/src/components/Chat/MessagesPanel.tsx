import React, { useEffect, useRef, useState } from 'react';
import Message from './Message';

interface Props {
    channel: any;
    onSendMessage: (...params: any[]) => void;
}

export default function MessagesPanel({channel, onSendMessage}: Props) {
    const messagesList = useRef<any>(null);
    const [inputValue, setInputValue] = useState("");

    const send = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (inputValue && inputValue != '') {
            onSendMessage(channel.id, inputValue);
            setInputValue("");
        }
    }

    useEffect(() => {
        if (messagesList.current) {
            messagesList.current.scrollTop = messagesList.current.scrollHeight;
        }
    }, [channel]);

    const handleInput = e => {
        setInputValue(e.target.value);
    }

    let list = <div className="no-content-message">There is no messages to show</div>;
    if (channel && channel.messages) {
        list = channel.messages.map(m => <Message key={m.id} id={m.id} senderName={m.senderName} text={m.text} />);
    }
    return (
        <div style={{ flex: 3 }}>
            <div className='messages-panel' style={{ display: 'flex', flexDirection: 'column', height: 'calc(100vh - 128px)', justifyContent: 'space-between' }}>
                <div ref={messagesList} className="messages-list" style={{ margin: "0 3em", overflowY: 'auto' }}>{list}<div ref={messagesList}></div></div>
                {channel &&
                <form onSubmit={send}>
                    <div className="messages-input" style={{ display: 'flex', margin: "1em" }}>
                        <input style={{ flex: 1, borderRadius: "2em", padding: "1em" }} type="text" onChange={handleInput} value={inputValue} />
                        <button type="submit" style={{ marginLeft: "1em", padding: "1em", backgroundColor: "lightblue"}}>Send</button>
                    </div>
                </form>
                }
            </div>
        </div>
    );
}