import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { selectUserId } from "../store/selectors/user.selectors";
import socketClient  from "socket.io-client";
import { fetchUserInfoById } from "../api/user";
import ChannelList from "../components/Chat/ChannelList";
import { MessagesPanel } from "../components/Chat/MessagesPanel";

export default function Chat() {
    const SERVER = import.meta.env.VITE_CHAT_SERVER_URL;
    const [_channel, setChannel] = useState<any>();
    const [_channels, setChannels] = useState<any[]>([]);
    const [_socket, setSocket] = useState<any>();

    const userId = useSelector(selectUserId);

    const configureSocket = () => {
        const socket = socketClient(SERVER);
        socket.on('connection', () => {
            if (_channel) {
                handleChannelSelect(_channel.id);
            }
        });
        socket.on('channel', channel => {
            // update les participants du channel
            const channels = _channels;
            channels.forEach(c => {
                if (c.id === channel.id) {
                    c.participants = channel.participants;
                }
            });
            setChannels(channels);
        });
        socket.on('message', message => {
            // ajoute le message au channel correspondant
            const channels = _channels;
            channels.forEach(c => {
                if (c.id === message.channel_id) {
                    if (!c.messages) {
                        c.messages = [message];
                    } else {
                        c.messages.push(message);
                    }
                }
            });
            setChannels(channels);
        });
        setSocket(socket);
    }

    const loadChannels = async () => {
        fetch('http://localhost:3000/getChannels').then(async response => {
            const data = await response.json();
            setChannels(data.channels);
        })
        console.log("test");
    }

    const handleChannelSelect = (id: any) => {
        const channel = _channels.find(c => {
            return c.id === id;
        });
        setChannel(channel);
        _socket.emit('channel-join', id, ack => {
        });
    }

    const handleSendMessage = (channel_id: any, text: any) => {
        _socket.emit('send-message', { channel_id, text, senderName: _socket.id, id: Date.now() });
    }

    if (!_channels || _channels.length <= 0) {
        loadChannels();        
    }
    
    if (!_socket) {
        configureSocket();
    }
    
/*
<ChannelList channels={_channels} onSelectChannel={handleChannelSelect} />
            <MessagesPanel onSendMessage={handleSendMessage} channel={_channel} />
*/
  return (
        <div className='chat-app'>
            <ChannelList channels={_channels} onSelectChannel={handleChannelSelect} />
            <MessagesPanel onSendMessage={handleSendMessage} channel={_channel} />
        </div>
    );
}
