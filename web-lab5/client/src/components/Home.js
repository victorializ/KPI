import React from "react";
import Header from './Header';
import Footer from './Footer';
import './style/TextDiv.css';

function Home() {
      return (
        <div>
          <Header text="BLOG"/>
          <div className="TextDiv">
              <h1>Blog platform</h1>
              <p>Our company provides a free platform free hosting, and helps to create mantain your own blog easely. 
              BLOG is best solution for people who want to own their blog and customize it. It’s perfect for setting up a serious website that you plan to work on long-term."</p>
          </div>
          <div class="TextDiv">
              <h1>Blog platform</h1>
              <p>Our company provides a free platform free hosting, and helps to create mantain your own blog easely. 
              BLOG is best solution for people who want to own their blog and customize it. It’s perfect for setting up a serious website that you plan to work on long-term."</p>
          </div>
          <div class="TextDiv">
              <h1>Blog platform</h1>
              <p>Our company provides a free platform free hosting, and helps to create mantain your own blog easely. 
              BLOG is best solution for people who want to own their blog and customize it. It’s perfect for setting up a serious website that you plan to work on long-term."</p>
          </div>
          <Footer />
        </div>
      );
  }
export default Home;