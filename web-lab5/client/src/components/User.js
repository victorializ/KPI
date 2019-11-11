import React from "react";
import { Switch, Route, Link } from "react-router-dom";
import Footer from './Footer';
import Post from './Post.js';
import './style/User.css';
import CreatePostForm from './CreatePostForm'; 

class User extends React.Component {
  constructor() {
    super();
    this.state = {
      userName: '',
      img: '', 
      about: ''
    }
  }
  componentDidMount(){
    fetch('http://localhost:5000/get_user_info')
            .then(response => response.json())
            .then((jsonData) => {
                this.setState({
                    blogName: jsonData.blog,
                    blogImg: jsonData.img,
                    userName: jsonData.name,
                    about: jsonData.about
                })
            })
  }
  render(){
      return (
        <div className='User'> 
          <div className = "UserInfo">
            <img src = {require('./style/images/unicorn.jpeg')} alt="user"/>
            <p>{this.state.userName}</p>
            <p>{this.state.about}</p>
          </div>
          <UserMenu />  
          <section>
            <Switch>
              <Route path="/createpost" component={CreatePostForm} />
              <Route path="/posts" component={Posts} />
            </Switch>
          </section>
          <Footer />
        </div>
      );
    }
  }

class Posts extends React.Component {
  constructor() {
    super(); 
    this.state = {
      postsArr: []
    }
  }
  componentDidMount() {
    fetch('http://localhost:5000/get_posts')
      .then(response => response.json())
      .then((jsonData) => {this.setState({postsArr: jsonData })})
      .catch((error) => { console.error(error)});
  }

  render(){
    return (
      <div>
        {this.state.postsArr.map(value => <Post title={value.title} text={value.text} img={value.img} key={value.title}/>)}
      </div>
    );
  }
}

function UserMenu(props) {
  return (
      <ul>
          <li><Link to="/blog">View Blog</Link></li>
          <li><Link to="/createpost">Create New Post</Link></li>
          <li><Link to="/posts">Posts</Link></li>
      </ul>      
  );
}

export default User;