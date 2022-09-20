import React, { useState, useEffect, useContext } from "react";
import { Context } from "../context";
import { useRouter } from "next/dist/client/router";
import dynamic from "next/dynamic";

const ChatEngine = dynamic(() => 
    import("react-chat-engine").then((module) => module.ChatEngine)
);

const MessageFormSocial = dynamic(() => 
    import("react-chat-engine").then((module) => module.MessageFormSocial)
);

export default function Home() {
    const { username, password } = useContext(Context)
    const [showChat, setShowChat] = useState(false);
    const router = useRouter();

    useEffect(() => {
        if (typeof document !== undefined) {
            setShowChat(true);
        }
    }, []);

    useEffect(() => {
        if(username === "" || password === "") {
            router.push("/");
        }
    
    }, [username, password]);

    if(!showChat) return <div />;

    return (
        <div className="background">
            <div className="shadow">
                <ChatEngine
                    height="calc(100vh - 212px)"
                    projectID="" //id do projeto? nao entendi mto bem porque ele pega de um engine pronto
                    userName={username}
                    userPassword={password}
                    renderNewMessageForm={() => <MessageFormSocial />}
                />
            </div>
        </div>
    );
}