const express = require("express");

const paymentControllers = require("../Controllers/payment.controller");
const router = express.Router();

router.post("/payment", paymentControllers.postPaymentData);

module.exports = router;
