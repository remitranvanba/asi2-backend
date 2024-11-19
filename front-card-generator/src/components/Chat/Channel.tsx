import React from "react";


export default class Channel extends React.Component<{id: string; name: string; participants: string[]; onClick:(...params: any[]) => void;}> {

    click = () => {
        this.props.onClick(this.props.id);
    }

    render() {
        return (
            <div className='channel-item' onClick={this.click}>
                <div>{this.props.name}</div>
                <span>{this.props.participants}</span>
            </div>
        )
    }
}