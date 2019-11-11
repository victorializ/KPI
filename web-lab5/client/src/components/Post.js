import React from "react";
import './style/Post.css';
import {getSortedTags} from './sortedTags.js';

//изменение поста не работает, создание нового поста тоже кста
class Post extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            title: props.title, 
            text: props.text, 
            img: props.img,
            tags: getSortedTags(props.text),
            deleted: false, 
            edited: false
        }
        this.handleClick = this.handleClick.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
    }
    handleSubmit(event) {
        if (this.state.Title !== "" && this.state.Text !== "") { 
            this.setState(prev => prev.edited=false);
            this.setState({tags: getSortedTags(this.state.text)});
        }
        else {
            event.preventDefault(); 
            alert('Not all fields are filled!');
        }
    }
    handleClick() { 
        this.setState(prev => prev.edited=true);
    }
    handleChange(event) {
        const {name, value} = event.target;
        this.setState({[name]: value})
    }
    handleDelete() {
        fetch(`http://localhost:5000/delete?post=${this.state.title}`)
            .then(response => {
        this.setState(prev => prev.deleted=true);
            })
    }
    render() {
        return (
            this.state.deleted ? (<div></div>) : (
                this.state.edited ? (
                    <form onSubmit={this.handleSubmit} className="userPost">
                        <input type="text"  
                            name="title"
                            value={this.state.title}
                            onChange={this.handleChange}/>
                        <textarea
                            value={this.state.text} 
                            name="text" 
                            onChange={this.handleChange}/>
                        <div><button>Save</button></div>
                    </form>
                ):(
                    <div className="userPost">
                        <img src = {require(`${this.state.img}`)} alt="post"/>
                        <h1>{this.state.title}</h1>
                        <p>{this.state.text}</p>
                        <p className="tags">{Object.keys(this.state.tags).map(element => <small key={element}>{`#${element} `}</small>)}</p>
                        <div>
                            <button onClick={this.handleClick}>Change</button> 
                            <button onClick={this.handleDelete}>Delete</button>
                        </div>
                    </div>
                )
            )
        )
    }

}
export default Post;