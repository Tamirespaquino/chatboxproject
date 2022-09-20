import React, { createContext, useState } from "react";

export const Context = createContext();

export const ContextProvider = (props) => {
    const [id, setId] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [cpf, setCpf] = useState("");
    const [address, setAddress] = useState("");

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
        setAddress
    };

    return <Context.Provider value={value}>{props.children}</Context.Provider>;
};