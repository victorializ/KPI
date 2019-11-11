import React, { Component } from 'react';
import sha256 from 'crypto-js/sha256';
import decode from "jwt-decode";
import { Link } from "react-router-dom";
import './style/Form.css';
import Header from './Header';

class SignIn extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: null, 
            isSingedIn: false, 
            login: '',
            password: '',
            about: '', 
            blog: '', 
            img: './style/images/unicorn.jpeg'
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleLogOut = this.handleLogOut.bind(this);
    }

    componentDidMount() {
        let token = localStorage.getItem("token");
        if (token) this.setState({currentUser: decode(token).login});
    }

    handleLogOut() {
        localStorage.removeItem("token");
    }

    handleChange(event) {
        const {name, value} = event.target;
        this.setState({[name]: value})
    }
    handleSubmit(event) {
        event.preventDefault();  
        const headers = {
            Accept: "application/json",
            "Content-Type": "application/json"
        }; 
        fetch('http://localhost:5000/signin', {
            method: 'POST',
            headers,
            body: JSON.stringify({ 
                name: this.state.login, 
                password: this.state.password 
            })
        })
        .then(response => response.text())
        .then((token) => {
            localStorage.setItem("token", token);
            alert(`Welcome. \n Login: ${this.state.login}`);
            this.setState({isSingedIn: true});
        })
        .catch((error) => {
            alert(error.message);
        }
        );    
    }

    render() {
        return (
        this.state.currentUser ? (
            <div>
            <Header text="Sing in"/>
            <section className="Form">
                <h1>You are already logged in</h1>
                <button onClick={this.handleLogOut} id="LogOut"><Link to='./'>Log out</Link></button>
            </section>
            </div> 
            ) : (
            <div>
            <Header text="Sing in"/>
            <form onSubmit={this.handleSubmit} className="Form" id="signUp" style={{background: '#E8A87C'}}> 
                    <input type="text" 
                        placeholder="login" 
                        name="login"
                        value={this.state.login}
                        onChange={this.handleChange} required/>
                    <input type="password" 
                        name="password" 
                        value={this.state.password} 
                        onChange={this.handleChange}/>
                    <button onClick={this.handleSubmit}><Link to='./user'>Submit</Link></button> 
            </form>
            </div>
        )
    )}
}

export default SignIn;