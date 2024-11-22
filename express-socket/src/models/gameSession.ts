const mongoose = require("mongoose");

const gameSessionSchema =  mongoose.Schema({
    playerOneId: {Number, unique: true, required: true},
    playerOnePoint: {Number, unique: false, required: true},
    playerTwoId: {Number, unique: true, required: true},
    playerTwoPoint: {Number, unique: false, required: true}
});

module.exports = mongoose.model('gameSession', gameSessionSchema);