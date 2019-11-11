import React from 'react';
import './style/Form.css';

class FeedbackForm extends React.Component {
    constructor() {
        super();
        this.state = {
            Name: "",
            Organization: "",
            Type: "Partnership",
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
        if (this.state.Name !== "" && this.state.Organization !== "" && this.state.Type !== "" && this.state.Text !== "" && filename !== "") { 
            fetch('http://localhost:5000/feedback', {
                method: 'POST',
                body: data,
            }).then(
            alert(`The form has been submitted.
                            \n Name: ${this.state.Name} 
                            \n Organization: ${this.state.Organization}
                            \n Type: ${this.state.Type}
                            \n Text: ${this.state.Text}
                            \n File: ${filename}`))
                                .then(this.setState({Name: "",
                                Organization: "",
                                Type: "Partnership",
                                Text: ""}))
                                .then(document.getElementById("feedback").reset())
        }
        else {
            alert('The form has not been submitted! Not all fields are filled.');
        }
    } 
    render() {
        return (
            <form onSubmit={this.handleSubmit} className="Form" id="feedback"> 
            <h1>Сontact form</h1>
                <input type="text" 
                    placeholder="Name" 
                    name="Name"
                    value={this.state.Name}
                    onChange={this.handleChange} required/>

                <input type="text" 
                    placeholder="Organization" 
                    name="Organization" 
                    value={this.state.Organization} 
                    onChange={this.handleChange}/>

                <select value={this.state.Type}
                    onChange={this.handleChange}
                    name="Type">
                    <option value="Partnership">Partnership</option>
                    <option value="Press">Press</option>
                    <option value="Other">Other</option>
                </select>

                <textarea value={this.state.text} 
                    name="Text"
                    onChange={this.handleChange}/>

                <input type="file" name="file" ref={this.fileInput} />

                <button>Submit</button> 
            </form>
        )
    }
}
export default FeedbackForm;