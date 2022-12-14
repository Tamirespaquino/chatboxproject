import React, { createContext, useState } from "react";

export const Context = createContext();

export const ContextProvider = (props) => {
    const [id, setId] = useState("");
    const [username, setUsername] = useState("");
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
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
        firstname,
        setFirstname,
        lastname,
        setLastname,
        username,
        setUsername,
        password,
        setPassword,
        confirmPassword,
        setConfirmPassword,
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