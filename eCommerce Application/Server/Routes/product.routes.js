const express = require("express");

const product = require("../Controllers/product.controller");
const router = express.Router();

router.get("/product/:id", product.getProductpage);

module.exports = router;

