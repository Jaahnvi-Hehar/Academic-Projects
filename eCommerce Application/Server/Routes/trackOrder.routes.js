const express = require("express");

const trackController = require("../Controllers/track.controller");
const router = express.Router();

router.get("/orders/track/:id", trackController.trackOrder);

module.exports = router;
