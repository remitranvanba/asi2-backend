import React from 'react';


export class Message extends React.Component<{id: string, senderName: string, text: string}> {

    render() {
        return (
            <div className='message-item'>
                <div><b>{this.props.senderName}</b></div>
                <span style={{ display: "inline-block", wordBreak: "break-word", margin: "1em", backgroundColor: "lightblue", boxShadow: "5px 5px 5px grey", borderRadius: "1em", padding: "1em" }}>{this.props.text}</span>
            </div>
        )
    }
}