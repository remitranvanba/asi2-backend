import React from 'react';
import { Message } from './Message';

export class MessagesPanel extends React.Component<{channel: any, onSendMessage: (...params: any[]) => void;}> {
    state = { input_value: '' }
    send = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (this.state.input_value && this.state.input_value != '') {
            this.props.onSendMessage(this.props.channel.id, this.state.input_value);
            this.setState({ input_value: '' });
        }
    }

    handleInput = e => {
        this.setState({ input_value: e.target.value });
    }

    render() {

        let list = <div className="no-content-message">There is no messages to show</div>;
        if (this.props.channel && this.props.channel.messages) {
            list = this.props.channel.messages.map(m => <Message key={m.id} id={m.id} senderName={m.senderName} text={m.text} />);
        }
        return (
            <div style={{ flex: 3 }}>
                <div className='messages-panel' style={{ display: 'flex', flexDirection: 'column' }}>
                    <div className="messages-list" style={{ maxHeight: '60em', overflowY: 'scroll' }}>{list}</div>
                    {this.props.channel &&
                    <form onSubmit={this.send}>
                        <div className="messages-input" style={{ display: 'flex', margin: "1em" }}>
                            <input style={{ flex: 1, borderRadius: "2em" }} type="text" onChange={this.handleInput} value={this.state.input_value} />
                            <button type="submit" style={{ marginLeft: "1em", padding: "2em", backgroundColor: "lightblue"}}>Send</button>
                        </div>
                    </form>
                    }
                </div>
            </div>);
    }

}