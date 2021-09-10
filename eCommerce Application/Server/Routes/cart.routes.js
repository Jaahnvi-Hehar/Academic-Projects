const express = require("express");

const cartControllers = require("../Controllers/cart.controllers");
const router = express.Router();

router.get("/cart/:id", cartControllers.cart);
router.delete("/delete/cart/:userId/:productId", cartControllers.deleteByCartId);

module.exports = router;
