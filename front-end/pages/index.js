import React, { useContext } from "react";
import { Context } from "../context";
import { Router, useRouter } from "next/dist/client/router";
import axios from "axios";

const Auth = () => {
  const {
    username,
    password,
    setUsername,
    setPassword
  } = useContext(Context);

  const router = useRouter();

  //pegar a senha que gera no service e colocar aqui
  function onSubmit(e) {
    e.preventDefault()

    //verifica se o usuario digitou alguma senha ou id
    if (username.length === 1 || password.length === 1) return

    //colocar a url de conexao com o back-end. Aqui, eh do login do usuario
    axios
      .post(
        'http://localhost:9091/users/login',
        {username:username, password:password},
        //{headers: {"Private-key": 'UUID gerado no service'}}
      )
      
      .then((r) => { 
        localStorage.setItem('apiToken', r.data.token);
        router.push('/chats');
      });
  }

  return (

    <div className="background">
      <div className="auth-container">
        <form className="auth-form" onSubmit={(e) => onSubmit(e)}>
          <div className="auth-title">ChatApp</div>

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

          <button type="submit" className="submit-button">Login</button>
          <button type="submit" className="submit-button">Sign Up</button>

        </form>
      </div>
    </div>
  );
};

export default Auth;