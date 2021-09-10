// Author : Jaahnvi Hehar
const mongoose = require("mongoose");
const SubscribersSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
  },
  email: {
    type: String,
    required: true,
    unique: true,
  },
});

const Subscribers = mongoose.model("Subscribers", SubscribersSchema);
module.exports = Subscribers;
