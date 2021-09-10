// Author : Jaahnvi Hehar
const express = require("express");
const app = express();
const User = require("../Models/user.models");

const bcrypt = require("bcrypt");
const saltRounds = 10;

app.use(express.json());

const signin = async (req, res) => {
  const email = req.body.email;
  const password = req.body.password;

  User.find({})
    .where("email")
    .equals(email)
    .exec((err, result) => {
      if (err) {
        return res.status(500).json({ success: false });
      } else {
        if (result.length > 0) {
          bcrypt.compare(password, result[0].password, (error, response) => {
            if (response) {
              return res.status(200).json({
                success: true,
                name: result[0].name,
                id: result[0]._id,
              });
            } else {
              return res.status(500).json({ success: false });
            }
          });
        } else {
          return res.status(204).json({ success: false });
        }
      }
    });
};

const addProfile = async (req, res) => {
  const name = req.body.name;
  const email = req.body.email;
  const password = req.body.password;

  await bcrypt.hash(password, saltRounds, (err, hash) => {
    if (err) {
      return res.status(500).json({
        success: false,
      });
    }
    const users = new User({
      name: name,
      email: email,
      password: hash,
    });
    users.save((error, result) => {
      if (error) {
        return res.status(500).json({ success: false });
      } else {
        return res.status(200).json({
          success: true,
          name: result.name,
          id: result._id,
        });
      }
    });
  });
};

const resetPassword = async (req, res) => {
  const email = req.body.email;
  const password = req.body.password;

  bcrypt.hash(password, saltRounds, (err, hash) => {
    if (err) {
      return res.status(500).json({
        success: false,
      });
    }
    const update = {
      password: hash,
    };

    User.findOneAndUpdate({ email: email }, update, {}, (err, result) => {
      if (err) {
        return res.status(500).json({
          success: false,
        });
      } else {
        if (!result) {
          return res.status(204).json({
            success: false,
          });
        }
        return res.status(200).json({
          success: true,
        });
      }
    });
  });
};

const getProfile = async (req, res) => {
  const user_id = req.body.user_id;

  User.find({})
    .where("_id")
    .equals(user_id)
    .exec((err, result) => {
      if (err) {
        return res.status(500).json({ success: false });
      } else {
        if (result.length > 0) {
          return res.status(200).json({
            success: true,
            name: result[0].name,
            email: result[0].email,
          });
        } else {
          return res.status(204).json({ success: false });
        }
      }
    });
};

const updateProfile = async (req, res) => {
  const user_id = req.body.user_id;
  const name = req.body.name;
  const email = req.body.email;

  const update = {
    name: name,
    email: email,
  };

  User.findByIdAndUpdate(user_id, update, {}, (err, result) => {
    if (err) {
      return res.status(500).json({
        success: false,
      });
    } else {
      if (!result) {
        return res.status(204).json({
          success: false,
        });
      }
      return res.status(200).json({
        success: true,
      });
    }
  });
};

const updatePassword = async (req, res) => {
  const user_id = req.body.user_id;
  const password = req.body.password;

  bcrypt.hash(password, saltRounds, (err, hash) => {
    if (err) {
      return res.status(500).json({
        success: false,
      });
    }
    const update = {
      password: hash,
    };

    User.findByIdAndUpdate(user_id, update, {}, (err, result) => {
      if (err) {
        return res.status(500).json({
          success: false,
        });
      } else {
        if (!result) {
          return res.status(204).json({
            success: false,
          });
        }
        return res.status(200).json({
          success: true,
        });
      }
    });
  });
};

const deleteProfile = async (req, res) => {
  const user_id = req.body.user_id;

  User.findByIdAndDelete(user_id, (err, result) => {
    if (err) {
      return res.status(500).json({
        success: false,
      });
    } else {
      if (!result) {
        return res.status(204).json({
          success: false,
        });
      }
      return res.status(200).json({
        success: true,
      });
    }
  });
};

module.exports = {
  signin,
  addProfile,
  resetPassword,
  getProfile,
  updateProfile,
  updatePassword,
  deleteProfile,
};
