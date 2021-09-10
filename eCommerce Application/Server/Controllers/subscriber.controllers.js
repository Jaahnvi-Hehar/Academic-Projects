// Author : Jaahnvi Hehar
const express = require("express");
const app = express();
const Subscriber = require("../Models/subscribers.model");

app.use(express.json());

const addSubscriber = async (req, res) => {
  const name = req.body.name;
  const email = req.body.email;

  console.log(name, email);
  const subscriber = new Subscriber({
    name: name,
    email: email,
  });

  subscriber.save((error, result) => {
    if (error) {
      return res.status(500).json({ success: false });
    } else {
      return res.status(200).json({
        success: true,
      });
    }
  });
};

module.exports = {
  addSubscriber,
};
