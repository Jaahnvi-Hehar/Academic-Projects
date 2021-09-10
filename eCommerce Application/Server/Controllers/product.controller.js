const express = require("express");
const app = express();
const Products = require("../Models/products.models");

app.use(express.json());


const getProductpage = (req, res) => {
  const id=req.params.id
  
  Products.find({ _id: id }, function (err, products) {
    if (err) {
      return res.status(500).json({
        message: "Internal server error!",
        success: false,
      });
    }
    if (!products || !products.length) {
      return res.status(400).json({
        message: "No products!",
        success: false,
      });
    }
    return res.status(200).json({
      message: "products Retrieved!",
      success: true,
      body: products,
    });
  });
};


module.exports = {
  getProductpage
};
