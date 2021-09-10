// Author : Jaahnvi Hehar
const express = require("express");

const cardControllers = require("../Controllers/card.controllers");
const router = express.Router();

router.post("/get/card", cardControllers.getCard);
router.post("/add/card", cardControllers.addCard);
router.put("/update/card", cardControllers.updateCard);
router.post("/delete/card", cardControllers.deleteCard);

module.exports = router;
