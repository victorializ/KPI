import React from "react";
import { Link } from "react-router-dom";
import './style/Header.css'

function Header(props) {
    return (
        <header className='Header'>
            <ul>
            <h2><Link to="/">BLOG</Link></h2>
                <li><Link to="/signin">Sign In</Link></li>
                <li><Link to="/signup">Sign Up</Link></li>
                <li><Link to="/about" title="About">About</Link></li>
                <li><Link to='/blog'>Blogs</Link></li>
            </ul>
            <h1>{props.text}</h1>        
        </header>
    )
}

export default Header;