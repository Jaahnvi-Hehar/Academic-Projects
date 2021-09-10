// Author : Jaahnvi Hehar
const mongoose = require("mongoose");
const CardsSchema = new mongoose.Schema({
  user_id: {
    type: String,
    required: true,
  },
  cardholder_name: {
    type: String,
    required: true,
  },
  card_number: {
    type: String,
    required: true,
  },
  expiration_date: {
    type: String,
    required: true,
  },
});

const Cards = mongoose.model("Cards", CardsSchema);
module.exports = Cards;
