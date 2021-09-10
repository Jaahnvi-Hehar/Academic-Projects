const mongoose = require("mongoose");

var OrderSchema = new mongoose.Schema({
  userId: { type: String },
  orderId: { type: String },
  imageUrl: { type: String },
  productName: { type: String },
  productDesc: { type: String },
  price: { type: Number },
  orderStatus: { type: String },
  cancelledStatus: { type: Boolean },
});

module.exports = mongoose.model("Orders", OrderSchema);
