import React from "react";
import { Link } from "../context/link";

export default Home;

function Home() {

    return (
        <div className="background">
            <div className="auth-container">
                <form className="auth-form">
                    <div className="auth-title">Welcome to ChatApp!</div>
                    <button type="submit" className="submit-only-button">
                        <a href="/account/login">Login</a>
                    </button>   
                </form>                     
            </div>
        </div>
    );
}