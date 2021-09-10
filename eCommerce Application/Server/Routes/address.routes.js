// Author : Jaahnvi Hehar
const express = require("express");

const addressControllers = require("../Controllers/address.controllers");
const router = express.Router();

router.post("/get/address", addressControllers.getAddress);
router.post("/add/address", addressControllers.addAddress);
router.put("/update/address", addressControllers.updateAddress);
router.post("/delete/address", addressControllers.deleteAddress);

module.exports = router;
