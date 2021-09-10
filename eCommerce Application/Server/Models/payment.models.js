const mongoose = require('mongoose')

 // Payment Schema to handle the model for Payment database
const PaymentSchema = new mongoose.Schema({
    userId: {
        type: String,
        required: true
    },
    paymentType: {
        type: String,
        required: true
    },
    paymentAmount: {
        type: Number,
        required: true
    },
    currency: {
        type: String,
        required: true
    },
    paymentStatus: {
        type: String,
        required: true
    },
});

const Payment = mongoose.model("payment", PaymentSchema);
module.exports = Payment;