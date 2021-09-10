const mongoose = require("mongoose");

const WishlistSchema = new mongoose.Schema({
  userId: { type: String },
  productId: { type: String },
  imageUrl: { type: String },
  productName: { type: String },
  productCategory: { type: String },
  price: { type: Number },
});

module.exports = mongoose.model("Wishlist", WishlistSchema);
