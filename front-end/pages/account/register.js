import React, { useContext } from "react";
import { Context } from "../../context";
import { Router, useRouter } from "next/dist/client/router";
import axios from "axios";

const Auth = () => {
  const {
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
  } = useContext(Context);

  const router = useRouter();

  function onRegister(e) {
    e.preventDefault()

    //verifica se o usuario digitou alguma senha ou id
    if (username.length === 1 || password.length === 1) return

    axios
      .post(
        'http://localhost:9091/users/register',
        {id:id, username:username, password:password, email:email, cpf:cpf, address:address}
      )
      
      .then((r) => { 
        localStorage.setItem('apiToken', r.data.token);
        router.push('/');
      });
  }

  return (

    <div className="background">
      <div className="auth-container-register">

         {/* Register */}
        <form className="auth-form" onRegister={(e) => onRegister(e)}>
          <div className="auth-title">Register</div>

          <div className="input-container">
            <input
            placeholder="Id"
            className="text-input"
            onChange={(e) => setId(e.target.value)}
            />
          </div>
          <div className="input-container">
            <input
            placeholder="Username"
            className="text-input"
            onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className="input-container">
            <input
            type="password"
            placeholder="Password"
            className="text-input"
            onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <div className="input-container">
            <input
            placeholder="E-mail"
            className="text-input"
            onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="input-container">
            <input
            placeholder="CPF"
            className="text-input"
            onChange={(e) => setCpf(e.target.value)}
            />
          </div>
          <div className="input-container">
            <input
            placeholder="Address"
            className="text-input"
            onChange={(e) => setAddress(e.target.value)}
            />
          </div>
          <button type="submit" className="submit-only-button">Send</button>

        </form>
      </div>
    </div>
  );
};

export default Auth;