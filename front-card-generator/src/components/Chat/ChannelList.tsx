import { Box } from "@mui/material";
import Channel from "./Channel";
import React from "react";

export default class ChannelList extends React.Component<{channels: any[], onSelectChannel: (...params: any[]) => void;}> {

    handleClick = id => {
        this.props.onSelectChannel(id);
    }

    render() {

        let list: any = <div className="no-content-message">There is no channels to show</div>;
        if (this.props.channels && this.props.channels.map) {
            list = this.props.channels.map(c => <Channel key={c.id} id={c.id} name={c.name} participants={c.participants} onClick={this.handleClick} />);
        }
        return (
            <div className='channel-list'>
                {list}
            </div>);
    }

}