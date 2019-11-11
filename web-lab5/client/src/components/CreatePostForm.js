import React from 'react';
import './style/Form.css';

class CreatePostForm extends React.Component {
    constructor() {
        super();
        this.state = {
            Title: "",
            Text: ""
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.fileInput = React.createRef();
    }
    handleChange(event) {
        const {name, value, type} = event.target;
        type !== "file" && this.setState({[name]: value})
    }
    handleSubmit(event){
        event.preventDefault();
        let filename = this.fileInput.current.files[0].name;
        const data = new FormData(event.target);
        if (this.state.Name !== "" && this.state.Text !== "" && filename !== "") { 
            fetch('http://localhost:5000/post', {
                method: 'POST',
                body: data
            }).then(
            alert(`The form has been submitted.
                            \n Title: ${this.state.Title} 
                            \n Text: ${this.state.Text}
                            \n File: ${filename}`))
                                .then(this.setState({Title: "",
                                Text: ""}))
                                .then(document.getElementById("postcreate").reset())
        }
        else {
            alert('The form has not been submitted! Not all fields are filled.');
        }
    } 
    render() {
        return (
            <form onSubmit={this.handleSubmit} className="Form" id="postcreate"> 
            <h1>Сreate post!</h1>
                <input type="text" 
                    placeholder="Title" 
                    name="Title"
                    value={this.state.Title}
                    onChange={this.handleChange} required/>
                <textarea value={this.state.text} 
                    name="Text" rows="20" placeholder="Write your post!"
                    onChange={this.handleChange}/>
                <input type="file" name="file" ref={this.fileInput} />
                <button>Submit</button> 
            </form>
        )
    }
}
export default CreatePostForm;
