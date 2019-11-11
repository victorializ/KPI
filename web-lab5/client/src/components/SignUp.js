import React, { Component } from 'react';
import sha256 from 'crypto-js/sha256';
import decode from "jwt-decode";
import './style/Form.css';
import Header from './Header';

class SignUp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: null, 
            isSuccsess: false, 
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
        fetch('http://localhost:5000/signup', {
            method: 'POST',
            headers,
            body: JSON.stringify({ 
                name: this.state.login, 
                password: sha256(this.state.login).toString(), 
                about: this.state.about, 
                blog: this.state.blog, 
                img: this.state.img
            })
        })
        .then((res) => {alert(`Welcome. \n Login: ${this.state.login}`)})
        .catch((err) => {alert(err.message)}) 
    }

    render() {
        return (
            
        this.state.currentUser ? (
            <div>
            <Header text="Sing up"/>
            <section className="Form">
                <h1>You are already logged in! </h1>
                <button onClick={this.handleLogOut} id="LogOut">Log out</button>
            </section>
            </div>
            ) : (
            <div>
            <Header text="Sing up"/>
            <form onSubmit={this.handleSubmit} className="Form" id="signUp" style={{background: '#E8A87C'}}> 
                    <input type="text" 
                        placeholder="login" 
                        name="login"
                        value={this.state.login}
                        onChange={this.handleChange} required/>
                    <input type="text" 
                        placeholder="blog name" 
                        name="blog"
                        value={this.state.blog}
                        onChange={this.handleChange} required/>
                    <input type="password" 
                        name="password" 
                        value={this.state.password} 
                        onChange={this.handleChange}/>
        
                    <textarea value={this.state.about} 
                        name="about"
                        placeholder="some text about you"
                        onChange={this.handleChange}/>
    
                    <button onClick={this.handleSubmit}>Submit</button> 
            </form>
            </div>
        )
    )}
}

export default SignUp;