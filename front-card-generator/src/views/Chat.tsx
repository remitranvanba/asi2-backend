import { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import { selectUserId, selectUserSurname } from "../store/selectors/user.selectors";
import socketClient, { Socket } from "socket.io-client";
import ChannelList from "../components/Chat/ChannelList";
import { DefaultEventsMap } from "socket.io";
import MessagesPanel from "../components/Chat/MessagesPanel";

export default function Chat() {
    const SERVER = import.meta.env.VITE_CHAT_SERVER_URL;
    const [_channel, setChannel] = useState<any>();
    const [_channels, setChannels] = useState<any[]>([]);

    const userId = useSelector(selectUserId);
    const username = useSelector(selectUserSurname);

    const _socket = useRef<Socket<DefaultEventsMap, DefaultEventsMap>>();

    useEffect(() => {
        // Configuration du socket
        
        const socket = socketClient(SERVER);
        
        socket.on("connection", () => {
            console.log("connection");
            if (_channel) {
                handleChannelSelect(_channel.id);
            }
        });

        socket.on("channel", (channel) => {
            console.log("Channel received", channel);

            // Mise à jour des participants du channel
            const updatedChannels = _channels.map((c) => {
                if (c.id === channel.id) {
                    return { ...c, participants: channel.participants };
                }
                return c;
            });
            setChannels(updatedChannels);
        });

        socket.on("message", (message) => {
            // Mise à jour des messages du channel
            const updatedChannels = _channels.map((c) => {
                if (c.id === message.channel_id) {
                    return {
                        ...c,
                        messages: [...c.messages, message],
                    };
                }
                return c;
            });
            setChannels(updatedChannels);
        });

        _socket.current = socket;

        // Nettoyage pour déconnecter le socket quand le composant est démonté
        return () => {
            if(_socket.current) {
                _socket.current.disconnect();
            }
        };
    }, [_channels, SERVER, _channel]);

    useEffect(() => {
        if (_channels.length === 0) {
            loadChannels(); // Charger les channels si vide
        }
    }, [_channels]);

    const loadChannels = async () => {
        console.log('Loading channels...')
        const response = await fetch("http://localhost:3000/getChannels");
        const data = await response.json();
        setChannels(data.channels);
    };

    const handleChannelSelect = (id: any) => {
        const channel = _channels.find((c) => c.id === id);
        if (channel !== _channel) {
            channel.selected = true;
            setChannel(channel);
            if (_socket.current) {
                _socket.current.emit("channel-join", id);
            }
        }
    };

    const handleSendMessage = (channel_id: any, text: any) => {
        const message = { channel_id, text, senderName: username, id: Date.now() };
        if (_socket.current) {
            console.log("Sending message:", message);
        _socket.current.emit("send-message", message);
        }
        
    };

    return (
        <div className="chat-app" style={{ display: "flex", width: "100%", gap: "1em" }}>
            <ChannelList channels={_channels} onSelectChannel={handleChannelSelect} />
            {_channel ? <MessagesPanel onSendMessage={handleSendMessage} channel={_channel} /> : null}
        </div>
        
    );
}