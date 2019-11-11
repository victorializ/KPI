const express = require('express');
const app = express();
const formidable = require('formidable');
const crypto = require('crypto');
const bodyParser = require('body-parser');
const cors = require('cors');
const multer = require('multer');
const fs = require('fs');
const jwt = require('jsonwebtoken');
const url = require('url');
const querystring = require('querystring');
const secret = "Hello";

const mongoURL = 'mongodb://localhost/lab5';
const mongoose = require('mongoose');

const User = require('./data_schemas/user');
const Post = require('./data_schemas/post');
const Feedback = require('./data_schemas/feedback');

const port = 5000; 
app.use(cors());

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

//const storage = multer.memoryStorage();
//const upload = multer({ storage });

mongoose.connect(mongoURL, { useNewUrlParser: true })
    .then(() => console.log("MongoDB successfully connected"))
    .catch(err => console.log(err));

app.get('/get_posts', (req, res) => {
    Post.find((err, posts) => {
        if (err) res.json({ message: err.message });
        res.json(posts);
    });
});
app.get('/get_user_info', (req, res) => {
    User.findOne((err, user) => {
        if (err) res.json({ message: err.message });
        res.json(user);
    });
}); 
app.get('/delete', (req, res) => {
    let params = querystring.parse(url.parse(req.url).query);    
    let postname = params['post'];
    Post.deleteOne({ title: postname }, function (err) {
        if (err) throw err;
        res.send('deleted');
    });
})
app.post('/feedback', (req, res) => {
    var form = new formidable.IncomingForm();
    form.parse(req, (err, fields, files) => {         
        let oldpath = files.file.path;
        let newpath = './images/' + files.file.name;
        fs.rename(oldpath, newpath, (err) => {
            if (err) throw err;
            console.log(fields);
        });
        let newFeedback = new Feedback({
            _id: new mongoose.Types.ObjectId(),
            name: fields.Name, 
            organization: fields.Organization,
            type: fields.Type, 
            text: fields.Text,
            file: newpath + files.file.name
        });
    
        newFeedback.save((err) => {
            if (err) res.json({ message: err.message });
            res.send('feedback inserted');
        })
    })
});
app.post('/post', (req, res) => {
    var form = new formidable.IncomingForm();
    form.parse(req, (err, fields, files) => {         
        let oldpath = files.file.path;
        let newpath = './images/' + files.file.name;
        fs.rename(oldpath, newpath, (err) => {
            if (err) throw err;
            console.log(fields);
        });
        
        let newPost = new Post({
            _id: new mongoose.Types.ObjectId(),
            title: fields.Title, 
            text: fields.Text,
            img: './style/images/' + files.file.name,
            date: new Date().toString()
        });
    
        newPost.save((err) => {
            if (err) res.json({ message: err.message });
            res.send('post inserted');
        })
    })
});
app.post("/signup", (req, res) => {
    const { name, password, about, blog, img } = req.body; 
    User.findOne({name: name}).exec(function(err, user) {
        if (user) {
            res.status(400).json({ message: "Login is already used" })
        }  
        else {
            let newUser = new User({
                _id: new mongoose.Types.ObjectId(),
                name: name,
                password: password, 
                about: about,
                blog: blog,
                img: img
            });
            newUser.save((err) => {
                if (err) res.json({message: err.message});
                    res.send('new user created');
                })
        } 
    });
}); 
app.post("/signin", (req, res) => {
    const { name, password} = req.body; 
    User.findOne({name: name}).exec(function(err, user) {
        if (user) {
            let token = jwt.sign({login: user.name}, secret);
            res.send(token);;
        }
        else {
            res.status(400).send("Invalid login or password!");
        }
    });
}); 

app.use(express.static(__dirname + '/client/public'));
app.listen(port, () => console.log(`Server running at ${port}`));






