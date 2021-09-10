const express = require("express");

const reviewControllers = require("../Controllers/reviews.controllers");
const router = express.Router();

router.get("/review/:id", reviewControllers.reviews);
router.post("/reviews/add", reviewControllers.add);
module.exports = router;
