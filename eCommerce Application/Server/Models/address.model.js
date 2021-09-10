// Author : Jaahnvi Hehar
const mongoose = require("mongoose");
const AddressSchema = new mongoose.Schema({
  user_id: {
    type: String,
    required: true,
  },
  address: {
    type: String,
    required: true,
  },
  city: {
    type: String,
    required: true,
  },
  zip_code: {
    type: String,
    required: true,
  },
});

const Address = mongoose.model("Addresses", AddressSchema);
module.exports = Address;
