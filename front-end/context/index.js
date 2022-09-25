import React, { createContext, useState } from "react";

export const Context = createContext();

export const ContextProvider = (props) => {
    const [id, setId] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [cpf, setCpf] = useState("");
    const [address, setAddress] = useState("");
    const [privateChats, setPrivateChats] = useState("");
    const [publicChats, setPublicChats] = useState("");
    const [tab, setTab] = useState("");
    const [userData, setUserData] = useState("");

    const value = {
        id,
        setId,
        username,
        setUsername,
        password,
        setPassword,
        email,
        setEmail,
        cpf,
        setCpf,
        address,
        setAddress,
        privateChats,
        setPrivateChats,
        publicChats,
        setPublicChats,
        tab,
        setTab,
        userData,
        setUserData
    };

    return <Context.Provider value={value}>{props.children}</Context.Provider>;
};