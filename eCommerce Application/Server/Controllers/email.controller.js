// Email tempate : https://www.regpacks.com/blog/payment-acknowledgement-email-templates/
require('dotenv').config();
const { email, password } = require('../Config/config');
// console.log(`Your port is ${port}`); // 8626
const nodemailer = require('nodemailer');

// Authentication for Gmail
let transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: "helpdesk.anaha@gmail.com",
        pass: "Anaha2021"
    }
});

// let mailOptions = {
//     from: 'helpdesk.anaha@gmail.com',
//     to: 'gr957124@dal.ca',
//     subject: 'Testing Node js Nodemailer',
//     text: 'The email has been sent properly.'
// }

// const sendEmail = transporter.sendMail(mailOptions, function(err,data) {
//     if(err) {
//         console.log("Error occurs", err);
//     } else {
//         console.log('Email sent!!!');
//     }
// })
const sendMail = (email, subject, text, callback) => {
    let mailOptions = {
        from: 'helpdesk.anaha@gmail.com',
        to: email,
        subject: subject,
        text: text
    }
    transporter.sendMail(mailOptions, function(err, data) {
        if(err) {
            callback(err, null);
        }
        return callback(null, data);
    })
};



module.exports = {sendMail};