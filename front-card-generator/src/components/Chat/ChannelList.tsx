import Channel from "./Channel";

interface Props {
    channels: any[];
    onSelectChannel: (...params: any[]) => void;
}

export default function ChannelList({channels, onSelectChannel}: Props) {
    const handleClick = id => {
        onSelectChannel(id);
    }

    let list: any = <div className="no-content-message">There is no channels to show</div>;
    if (channels && channels.length > 0) {
        list = channels.map(c => <Channel key={c.id} id={c.id} name={c.name} participants={c.participants} selected={c.selected} onClick={handleClick} />);
    }

    return (
        <div className='channel-list' style={{flex: 1}}>
            {list}
        </div>
    );
}