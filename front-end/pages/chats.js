import React, { useState, useEffect, useContext } from "react";
import { Context } from "../context";
import { useRouter } from "next/dist/client/router";
import dynamic from "next/dynamic";

const ChatEngine = dynamic(() => 
    import("react-chat-engine").then((module) => module.MessageFormSocial)
);

const MessageFormSocial = dynamic(() => 
    import("react-chat-engine").then((module) => module.MessageFormSocial)
);

export default function Chats() {
    const { username, secret } = useContext(Context)
    const [showChat, setShowChat] = useState(false);
    const router = useRouter()

    useEffect(() => {
        if (typeof document !== null) {
            setShowChat(true)
        }
    }, []);

    useEffect(() => {
        if(username === "" || secret === "") {
            router.push("/");
        }
    
    }, [username, secret]);

    if(!showChat) return <div />;

    return (
        <div className="background">
            <div className="shadow">
                <ChatEngine
                    height="calc(100vh - 200px)"
                    projectID="" //id do projeto? nao entendi mto bem porque ele pega de um engine pronto
                    userName={username}
                    userSecret={secret}
                    renderNewMessageForm={() => <MessageFormSocial />}
                />
            </div>
        </div>
    );
}