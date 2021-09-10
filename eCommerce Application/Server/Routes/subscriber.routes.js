// Author : Jaahnvi Hehar
const express = require("express");

const subscriberControllers = require("../Controllers/subscriber.controllers");
const router = express.Router();

router.post("/add/subscriber", subscriberControllers.addSubscriber);

module.exports = router;
