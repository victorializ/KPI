const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schema = new Schema({
    name: String,
    email: String
});

module.exports = mongoose.model('User', schema);