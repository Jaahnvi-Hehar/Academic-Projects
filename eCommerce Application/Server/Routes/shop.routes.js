const express = require("express");

const shopControllers = require("../Controllers/shop.controllers");
const router = express.Router();

router.get("/products", shopControllers.getProducts);

module.exports = router;
