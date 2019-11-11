import React from 'react';
import Footer from './Footer';
import './style/Blog.css';
import {getSortedTags, getAllTags} from './sortedTags.js';

class BlogView extends React.Component {
    constructor() {
        super();
        this.state = {
            blogName: '', 
            blogImg: '', 
            userName: '', 
            about: '', 
            posts: [],
            allTags: {}, 
            filter: ''
        }
    }
    componentDidMount() {
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
            
                fetch('http://localhost:5000/get_posts')
                    .then(response => response.json())
                    .then((jsonData) => {
                        jsonData.map(post => this.setState(prevState => prevState.posts.push(
                            {
                            img: post.img, 
                            title: post.title, 
                            text: post.text, 
                            date: post.date, 
                            tags: getSortedTags(post.text)
                            }
                        )))
                        this.setState(prevState => {
                            let tagsArr = prevState.posts.map(post => post.tags);
                            prevState.allTags = getAllTags(tagsArr);
                        })
                    })
        this.handleClick = this.handleClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }
    handleClick(event){
        this.setState({filter: event.target.id});
        console.log(event.target.id);
    }
    handleChange(event) {
        const {name, value} = event.target;
        console.log(name, value);
        this.setState({[name]: value})
    }
    render(){
        var tags = this.state.allTags;
        return (
            <div className='BlogView'>
                <header><h1>{this.state.blogName}</h1></header>
                <div class = "UserInfo">
                    <img src = {require('./style/images/unicorn.jpeg')} alt="user"/>
                    <p>{this.state.userName}</p>
                    <p>{this.state.about}</p>
                </div>
                <section>
                <input name="filter" class="filter" value={this.state.filter} placeholder="Filter by tags" type="search" onChange={this.handleChange}/>
                    {
                        this.state.posts.map(post => 
                        (Object.keys(post.tags).includes(this.state.filter) || this.state.filter ==='') ? 
                        <PostView img={post.img} title={post.title} text={post.text} date={post.date} tags={post.tags}/> : <div></div>
                        )
                    }
                </section>
                <aside class="tagCloud">
                    {Object.keys(tags).map(elem => <h1 onClick={this.handleClick} id = {elem} style={{fontSize: 20 *  tags[elem]}}>{elem}</h1>)}
                </aside>
                <Footer />
            </div>
        )
    }
}
function PostView(props) {
    return (
        <div className = "PostView">
            <img src = {require(`${props.img}`)} alt="post"/>
            <h1>{props.title}</h1>
            <p>{props.text}</p>
            <p>Date: {props.date}</p>
            <p class="tags">{Object.keys(props.tags).map(element => <small key={element}>{`#${element} `}</small>)}</p>
       </div>
    )        
}
export default BlogView;

