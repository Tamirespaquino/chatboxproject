import React from "react";

export default Home;

function Home() {

    return (
        <div className="background">
            <div className="auth-container">
                <div className="auth-title">Welcome to ChatApp!</div>
                
                <button href="/account/login" type="submit" className="submit-button">Login</button>
            </div>
        </div>
    );
}