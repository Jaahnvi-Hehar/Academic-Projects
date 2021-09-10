const express = require("express");

const wishlistControllers = require("../Controllers/wishlist.controllers");
const router = express.Router();

router.get("/wishlist/:id", wishlistControllers.wishlist);
router.delete("/delete/:userId/:productId", wishlistControllers.deleteById);
router.post("/add", wishlistControllers.add);
router.post("/addToCart", wishlistControllers.addToCart);

module.exports = router;
