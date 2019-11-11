var mongoose = require('mongoose');
 
var postSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    img: String,
    title: String, 
    text: String,
    date: String, 
    user: { 
        type: mongoose.Schema.Types.ObjectId, 
        ref: 'User'
    }
});
 
var Post = mongoose.model('Post', postSchema);
 
module.exports = Post;