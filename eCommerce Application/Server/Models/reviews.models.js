const mongoose = require("mongoose");

const ReviewSchema = new mongoose.Schema({
  userName: { type: String },
  productId: { type: String },
  review: { type: String },
  date: { type: String },
});

module.exports = mongoose.model("Review", ReviewSchema);
