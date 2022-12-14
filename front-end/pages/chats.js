import React, { useState, useEffect } from "react";
import basePath from '../api/basePath/producer';
import service from '../api/basePath/service';
import {over} from 'stompjs';
import SockJS from 'sockjs-client';
import axios from "axios";

var stompClient = null;

export default function ChatRoom() {
    const [privateChats, setPrivateChats] = useState(new Map());
    const [publicChats, setPublicChats] = useState([]);
    const [insert, setInsert] = useState("");
    const [tab, setTab] = useState("CHATROOM");    
    const [userData, setUserData] = useState({
        username: '',
        receiver: '',
        connected: false,
        message: ''
    });

    useEffect(() => {
        console.log(userData);
    }, [userData]);

    useEffect(() => {
        console.log(publicChats);
    }, [publicChats]);

    useEffect(async () => {
        const retorno = await axios.get(service.url + "users/12233",
            {
                headers:
                    { "Authorization": "Bearer " + localStorage.getItem("apiToken") }
            });

        console.log(retorno);
        await setUserData({ ...userData, "username": retorno.data.username });
        setInsert("insert");
        
    }, []);

    useEffect(() => {
        connect();
    }, [insert]);

    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/chat');
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    }

    const onConnected = () => {
        setUserData({...userData, "connected":true});
        stompClient.subscribe('/chatroom/public', onMessageReceived);
        stompClient.subscribe('/user/'+ userData.username + '/private', onPrivateMessaage);
        userJoin();
    }

    const userJoin = () => {
        var chatMessage = {
            sender: userData.username,
            status: "JOIN"
        };
        stompClient.send("/message/message", {}, JSON.stringify(chatMessage));
    }

    const onMessageReceived = (payload) => {
        var payloadData = JSON.parse(payload.body);
        console.log("payload - inicio", payload);
        switch(payloadData.status) {
            case "JOIN":
                if(!privateChats.get(payloadData.sender)){
                    privateChats.set(payloadData.sender,[]);
                    setPrivateChats(new Map(privateChats));
                }
                break;
            case "MESSAGE":
                publicChats.push(payloadData);
                setPublicChats([...publicChats]);
                break;
        }
        console.log("public chats", publicChats);
    }

    const onPrivateMessaage = (payload) => {
        
        var payloadData = JSON.parse(payload.body);
        if(privateChats.get(payloadData.sender)){
            privateChats.get(payloadData.sender).push(payloadData);
            setPrivateChats(new Map(privateChats));
        } else {
            let list = [];
            list.push(payloadData);
            privateChats.set(payloadData.sender, list);
            setPrivateChats(new Map(privateChats));
        }
    }

    const onError = (err) => {
        console.log(err);
    }

    const handleMessage = (event) => {
        const {value} = event.target;
        setUserData({...userData, "message": value});
    }

    const sendValue = () => {
        if(stompClient) {
            var chatMessage = {
                sender: userData.username,
                message: userData.message,
                status: "MESSAGE"
            };
            console.log(chatMessage);
            stompClient.send("/message/message", {}, JSON.stringify(chatMessage));
            setUserData({...userData, "message": ""});
        }
    }

    const sendPrivateValue = () => {
        if (stompClient) {
            var chatMessage = {
                sender: userData.username,
                receiver: tab,
                message: userData.message,
                status: "MESSAGE"
            };

            if(userData.username !== tab) {
                privateChats.get(tab).push(chatMessage);
                setPrivateChats(new Map(privateChats));
            }

            axios
            .post(
                basePath.url,
                chatMessage                
            )
            .then((r) => { 
                console.log(r);
            }).catch((err) => console.log(err));

            stompClient.send("/message/private-message", {}, JSON.stringify(chatMessage));
            setUserData({...userData, "message": ""});
        }
    } 
    
    /*const handleUsername = (event) => {
        const {value} = event.target;
        setUserData({...userData, "username": value});
    }

    const registerUser = () => {
        connect();
    }*/

    return (
        <div className="container">
            <div className="chat-box">
                <div className="member-list">
                    <ul>
                        <li onClick={()=>{setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Chatroom</li>
                        {[...privateChats.keys()].map((name,index)=>(
                            <li onClick={()=>{setTab(name)}} className={`member ${tab===name && "active"}`} key={index}>{name}</li>
                        ))}
                    </ul>
                </div>
                {tab==="CHATROOM" && <div className="chat-content">
                    <ul className="chat-messages">
                        {publicChats.map((chat, index) => (
                            <li className={`message ${chat.sender === userData.username && "self"}`} key={index}>
                                {chat.sender !== userData.username && <div className="avatar">{chat.sender}</div>}
                                <div className="message-data">{chat.message}</div>
                                {chat.sender === userData.username && <div className="avatar self">{chat.sender}</div>}
                            </li>
                        ))}
                    </ul>

                    <div className="send-message">
                        <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage}/>
                        <button type ="button" className="send-button" onClick={sendValue}>send</button>
                    </div>
                </div>}
                {tab!=="CHATROOM" && <div className="chat-content">
                    <ul className="chat-messages">
                        {[...privateChats.get(tab)].map((chat, index)=>(
                            <li className={`message ${chat.sender === userData.username && "self"}`} key={index}>
                                {chat.sender !== userData.username && <div className="avatar">{chat.sender}</div>}
                                <div className="message-data">{chat.message}</div>
                                {chat.sender === userData.username && <div className="avatar self">{chat.sender}</div>}
                            </li>
                        ))}
                    </ul>

                    <div className="send-message">
                        <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} />
                        <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
                    </div>
                </div>}                
            </div>
        </div>
    );
}