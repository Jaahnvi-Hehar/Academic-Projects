// Author : Jaahnvi Hehar
const express = require("express");

const userControllers = require("../Controllers/user.controllers");
const router = express.Router();

router.post("/signin", userControllers.signin);
router.post("/add/profile", userControllers.addProfile);
router.put("/signin/reset/password", userControllers.resetPassword);
router.post("/get/profile", userControllers.getProfile);
router.put("/update/profile", userControllers.updateProfile);
router.put("/update/password", userControllers.updatePassword);
router.post("/delete/profile", userControllers.deleteProfile);

module.exports = router;
