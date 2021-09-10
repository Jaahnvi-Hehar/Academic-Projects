const express = require("express");
const app = express();
const Wishlist = require("../Models/wishlist.models");
const Cart = require("../Models/cart.models");

app.use(express.json());

const wishlist = (req, res) => {
  userId = req.params.id;
  //Filtering wishlist products by userId
  Wishlist.find({ userId: userId }, function (err, products) {
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

const deleteById = (req, res) => {
  userId = req.params.userId;
  productId = req.params.productId;
  //logic for deleting products from wishlist based on userId and productId
  Wishlist.findOneAndRemove(
    { userId: userId, productId: productId },
    (err, data) => {
      if (err) {
        console.log(err);
      } else {
        return res.status(200).json({
          message: "Product deleted!",
          success: true,
          body: data,
        });
      }
    }
  );
};

const add = (req, res) => {
  const products = req.body;
  console.log(products);
  //logic for adding products to wishlist
  Wishlist.insertMany(products, function (err) {
    if (err) {
      return res.status(400).json({
        message: err.message,
        success: false,
      });
    } else {
      return res.status(200).json({
        message: "Products inserted!",
        success: true,
      });
    }
  });
};

const addToCart = (req, res) => {
  const products = req.body;
  console.log(products);
  //logic for adding products to cart from wishlist
  Cart.insertMany(products, function (err) {
    if (err) {
      return res.status(400).json({
        message: "Error !",
        success: false,
      });
    } else {
      return res.status(200).json({
        message: "Products inserted!",
        success: true,
        body: products,
      });
    }
  });
};

module.exports = {
  wishlist,
  deleteById,
  add,
  addToCart,
};
