const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schema = new Schema({
    userId: String,
    productId: String
});

module.exports = mongoose.model('Order', schema);