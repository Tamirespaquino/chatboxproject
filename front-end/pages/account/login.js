import React, { useContext } from "react";
import { Context } from "../../context";
import { Router, useRouter } from "next/dist/client/router";
import axios from "axios";

const Auth = () => {
  const {
    username,
    setUsername,
    password,
    setPassword
  } = useContext(Context);

  const router = useRouter();

  function onSubmit(e) {
    e.preventDefault()

    //verifica se o usuario digitou alguma senha ou id
    if (username.length === 1 || password.length === 1) return

    axios
      .post(
        'http://localhost:9091/users/login',
        {username:username, password:password}
      )
      
      .then((r) => { 
        localStorage.setItem('apiToken', r.data.token);
        router.push('../message/chats');
      });
  }

  return (

    <div className="background">
      <div className="auth-container">

        {/* Login */}
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

          <button type="submit" className="submit-button">Sign in</button>
          <button type="submit" className="submit-button">
            <a href="/account/register">Sign up</a>
          </button>        
        </form>
      </div>
    </div>    
  );
};

export default Auth;