const mongoose = require("mongoose");

const cardSchema =  mongoose.Schema({
    playerId: {Number, unique: true, required: true},
    name: String,
    attack: Number,
    defense: Number
});

module.exports = mongoose.model('card', cardSchema);