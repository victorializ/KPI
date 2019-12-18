const mongoURL = 'mongodb://localhost/graphQL';
const mongoose = require('mongoose');

exports.connect = () => {    
    mongoose.connect(mongoURL, { useNewUrlParser: true })
        .then(() => console.log("MongoDB successfully connected"))
        .catch(err => console.log(err));
};