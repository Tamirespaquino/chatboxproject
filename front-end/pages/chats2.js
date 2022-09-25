import React, { useState, useEffect, useContext } from "react";
import { Context } from "../context";
import {over} from 'stompjs';
import SockJS from 'sockjs-client';
import { useRouter } from "next/dist/client/router";
import dynamic from "next/dynamic";

const ChatEngine = dynamic(() => 
    import("react-chat-engine").then((module) => module.ChatEngine)
);

const MessageFormSocial = dynamic(() => 
    import("react-chat-engine").then((module) => module.MessageFormSocial)
);

export default function ChatRoom() {
    const { username, password } = useContext(Context);
    const [showChat, setShowChat] = useState(false);
    const [userData, setUserData] = useState({
        sender: '',
        receiver: '',
        connected: false,
        message: ''
    });
    const router = useRouter();

    useEffect(() => {
        if (typeof document !== undefined) {
            setShowChat(true);
        }
    }, []);

    /*useEffect(() => {
        if(username === "" || password === "") {
           // router.push("/");
        }
    
    }, [username, password]);*/
    useEffect(() => {
        console.log(userData);
    }, [userData]);

    if(!showChat) return <div />;

    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/chat'); // colocar url correta
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    }

    const onConnected = () => {
        setUserData({...userData, "connected":true});
        stompClient.subscribe('/chatroom/public', onMessageReceived);
        stompClient.subscribe('user'+ userData.username + '/private', onPrivateMessaage);
    }

    const userJoin = () => {
        var chatMessage = {
            sender: userData.username,
            status: "JOIN"
        };
        stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
    }

    const onMessageReceived = (payload) => {
        var payloadData = JSON.parse(payload.body);
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
    }

    const onPrivateMessaage = (payload) => {
        console.log(payload);
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
            stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
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

            if(userData.userData !== tab) {
                privateChats.get(tab).push(chatMessage);
                setPrivateChats(new Map(privateChats));
            }
            stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
            setUserData({...userData, "message": ""});
        }
    } 
    
    const handleUsername = (event) => {
        const {value} = event.target;
        setUserData({...userData, "username": value});
    }

    const registerUser = () => {
        connect();
    }

    return (
        <div className="background">
            {userData.connected?
            <div className="shadow">
                <ChatEngine
                    height="calc(100vh - 212px)"
                    userName={username}
                    userPassword={password}
                    renderNewMessageForm={() => <MessageFormSocial />}
                />
                <div className="member-list">
                    <ul>
                        <li onClick={()=>{setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Chatroom</li>
                        {[...privateChats.keys()].map((name,index)=>(
                            <li onClick={()=>{setTab(name)}} className={`member ${tab===name && "active"}`} key={index}>{name}</li>
                        ))}
                    </ul>
                </div>
                {tab==="CHATROOM" && <div className="chat-content">
                    <ul className="chat-message">
                        {publicChats.map((chat, index) => {
                            <li className={`message ${chat.sender === userData.user && "self"}`} key={index}>
                                {chat.sender !== userData.username && <div className="avatar">{chat.sender}</div>}
                                <div className="message-data">{chat.message}</div>
                                {chat.sender === userData.username && <div className="avatar self">{chat.sender}</div>}
                            </li>
                        })}
                    </ul>

                    <div className="send-message">
                        <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage}/>
                        <button type ="button" className="input-message" onClick={sendValue}>send</button>
                    </div>
                </div>}
                {tab!=="CHATROOM" && <div className="chat-content">
                    <ul className="chat-message">
                        {[...privateChats.get(tab)].map((chat, index)=>{
                            <li className={`message ${chat.sender === userData.username && "self"}`} key={index}>
                                {chat.sender !== userData.username && <div className="avatar">{chat.sender}</div>}
                                <div className="message-data">{chat.message}</div>
                                {chat.sender === userData.username && <div className="avatar self">{chat.sender}</div>}
                            </li>
                        })}
                    </ul>

                    <div className="send-message">
                        <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} />
                        <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
                    </div>
                </div>}                
            </div>
            :
            <div className="register">
                <input
                id="user-name"
                placeholder="Enter your name"
                name="username"
                value={userData.username}
                onChange={handleUsername}
                margin="normal"
                />
                <button type="button" onClick={registerUser}>
                    connect
                </button>
            </div>}
        </div>
    );
}