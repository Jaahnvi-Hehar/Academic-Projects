const express = require("express");

const sendMail = require("../Controllers/email.controller");
const router = express.Router();
// const sendMail = require('./mail');
router.post('/email', (req, res) => {
    const { subject, email, text } = req.body;
    console.log('Data: ', req.body);
    
    sendMail.sendMail(email, subject, text, function(err, data) {
        if (err) {
            console.log('ERROR: ', err);
            return res.status(500).json({ message: err.message, success: false });
        }
        console.log('Email sent!!!');
        return res.status(200).json({ message: 'Email sent successfully', success: true });
    });
});


// router.post("/email", emailControllers.sendEmail);

module.exports = router;
