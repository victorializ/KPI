var mongoose = require('mongoose');
 
var userSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    name: String,
    password: String, 
    about: String,
    blog: String,
    img: String
});
 
var User = mongoose.model('User', userSchema);
 
module.exports = User;