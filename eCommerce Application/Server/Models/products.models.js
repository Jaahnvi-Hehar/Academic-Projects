const mongoose = require("mongoose");

var ProductSchema = new mongoose.Schema({
  productTitle: { type: String },
  cost: { type: Number },
  cat: { type: String },
  cardImageURL: { type: String },
  description: { type: String },
});

module.exports = mongoose.model("Product", ProductSchema);
