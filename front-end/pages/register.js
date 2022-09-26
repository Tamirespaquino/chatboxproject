import React, { useContext } from "react";
import { Context } from "../context";
import { Router, useRouter } from "next/dist/client/router";
import axios from "axios";

const Auth = () => {
  const {
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
    setAddress
  } = useContext(Context);

  const router = useRouter();

  function onRegister(e) {
    e.preventDefault()

    //verifica se o usuario digitou alguma senha ou id
    if (username.length === 1 || password.length === 1 || confirmPassword.length === 1) return

    axios
      .post(
        'http://localhost:9091/users/register',
        {firstname:firstname, lastname:lastname, username:username, password:password, confirmPassword:confirmPassword, email:email, cpf:cpf, address:address}
      )
      
      .then((r) => { 
        // localStorage.setItem('apiToken', r.data.token);
        router.push('/login');
      });
  }

  return (

    <div className="background">
      <div className="auth-container-register">

         {/* Register */}
        <form className="auth-form" onSubmit={onRegister}>
          <div className="auth-title">Register</div>

          <div className="input-container">
            <input
            placeholder="Firstname"
            className="text-input"
            onChange={(e) => setFirstname(e.target.value)}
            />
          </div>
          <div className="input-container">
            <input
            placeholder="Lastname"
            className="text-input"
            onChange={(e) => setLastname(e.target.value)}
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
            type="password"
            placeholder="Confirm password"
            className="text-input"
            onChange={(e) => setConfirmPassword(e.target.value)}
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
          <button type="submit" className="submit-only-button">
            <a>Send</a>
          </button>
        </form>
      </div>
    </div>
  );
};

export default Auth;