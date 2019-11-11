import React from 'react';
import Header from './Header';
import Footer from './Footer';
import FeedbackForm from './FeedbackForm';

function About() {
      return (
        <div>
          <Header text="Contact Us"/>
          <div className="TextDiv">
              <h1>Blog platform</h1>
              <p>Our company provides a free platform free hosting, and helps to create mantain your own blog easely. 
              BLOG is best solution for people who want to own their blog and customize it. It’s perfect for setting up a serious website that you plan to work on long-term."</p>
          </div>
          <div className="TextDiv">
              <h1>Contact us in social media</h1>
              <p><a href="">Instagram</a><br/><br/><a href="">Telegram</a></p>
          </div>
          <div style={{background: '#E8A87C'}}>
            <FeedbackForm/>
          </div>
          <Footer />
        </div>
      );
  }
export default About;