import React from 'react';


export class Message extends React.Component<{id: string, senderName: string, text: string}> {

    render() {
        return (
            <div className='message-item'>
                <div><b>{this.props.senderName}</b></div>
                <span>{this.props.text}</span>
            </div>
        )
    }
}