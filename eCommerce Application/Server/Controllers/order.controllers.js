const express = require("express");
const app = express();
const Order = require("../Models/orders.models");

app.use(express.json());

const addOrder = (req, res) => {
  const orders = req.body;
  //logic for adding order details to Orders Document
  Order.insertMany(orders, function (err) {
    if (err) {
      return res.status(400).json({
        message: "Error !",
        success: false,
      });
    } else {
      return res.status(200).json({
        message: "Order details inserted!",
        success: true,
      });
    }
  });
};

const orderList = (req, res) => {
  userId = req.params.id;
  //logic for listing orders based on userId
  Order.find({ userId: userId }, function (err, orders) {
    console.log(orders);
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

const cancel = (req, res) => {
  const id = req.params.id;
  console.log(id);
  //logic for cancelling orders based on orderId
  Order.updateOne(
    { orderId: id },
    {
      $set: {
        orderStatus: "cancelled",
        cancelledStatus: true,
      },
    },
    function (err) {
      if (err) {
        return res.status(400).json({
          message: "Error updating record!",
          success: false,
        });
      } else {
        return res.status(200).json({
          message: "Order cancelled!",
          success: true,
        });
      }
    }
  );
};

module.exports = {
  addOrder,
  orderList,
  cancel,
};
