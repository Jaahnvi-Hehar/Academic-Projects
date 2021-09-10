const express = require("express");
const app = express();
const Review = require("../Models/reviews.models");

app.use(express.json());

const reviews = (req, res) => {
  productId = req.params.id;
  console.log(productId)
  Review.find({ productId: productId }, function (err, products) {
    console.log(products);
    if (err) {
      return res.status(500).json({
        message: "Internal server error!",
        success: false,
      });
    } else {
      if (!products || !products.length) {
        return res.status(400).json({
          message: "No products!",
          success: false,
        });
      }
      return res.status(200).json({
        message: "Products Retrieved!",
        success: true,
        body: products,
      });
    }
  });
};

const add = (req, res) => {
  const review = req.body;
  console.log(review);
  Review.insertMany(review, function (err) {
    if (err) {
      return res.status(400).json({
        message: err.message,
        success: false,
      });
    } else {
      return res.status(200).json({
        message: "Comments inserted!",
        success: true,
      });
    }
  });
};

module.exports = {
  reviews,
  add,
};
