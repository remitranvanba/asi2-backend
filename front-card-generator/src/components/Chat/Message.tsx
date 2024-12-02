import React from 'react';
import { useSelector } from 'react-redux';
import { selectUserSurname } from '../../store/selectors/user.selectors';

interface Props {
    id: string; 
    senderName: string; 
    text: string;
}

export default function Message({id, senderName, text}: Props) {
    const username = useSelector(selectUserSurname);

    return (
        <div style={ {display: 'flex', flexDirection: 'column', margin: "1em", alignItems: senderName === username ? 'flex-end' : 'flex-start'}} className='message-item'>
            <div><b>{senderName}</b></div>
            <div style={{margin: "1em"}}>
            <span style={ 
            { wordBreak: "break-word",  margin: "1em", backgroundColor: senderName === username ? "blue" : "lightblue", color: senderName === username ? "white" : "black", boxShadow: "5px 5px 5px grey", borderRadius: "1em", padding: "1em" }}>{text}</span>
            </div>
        </div>
    )
}