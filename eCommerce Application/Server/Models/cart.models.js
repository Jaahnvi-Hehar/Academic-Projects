const mongoose = require("mongoose");

const CartSchema = new mongoose.Schema({
  userId: { type: String },
  productId: { type: String },
  imageUrl: { type: String },
  productName: { type: String },
  productCategory: { type: String },
  price: { type: Number },
  quantity: { type: Number },
});

module.exports = mongoose.model("Cart", CartSchema);
