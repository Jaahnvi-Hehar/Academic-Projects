const express = require("express");
const app = express();
const Order = require("../Models/orders.models");

app.use(express.json());


const trackOrder = (req, res) => {
  const id=req.params.id
  
  Order.find({ orderId: id }, function (err, orders) {
    if (err) {
      return res.status(500).json({
        message: "Internal server error!",
        success: false,
      });
    }
    if (!orders || !orders.length) {
      return res.status(400).json({
        message: "No orders!",
        success: false,
      });
    }
    return res.status(200).json({
      message: "Orders Retrieved!",
      success: true,
      body: orders,
    });
  });
};


module.exports = {
  trackOrder
};
