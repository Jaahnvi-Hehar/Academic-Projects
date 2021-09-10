const express = require("express");

const orderControllers = require("../Controllers/order.controllers");
const router = express.Router();

router.get("/orders/:id", orderControllers.orderList);
router.post("/orders/add", orderControllers.addOrder);
router.put("/order/:id", orderControllers.cancel);

module.exports = router;
