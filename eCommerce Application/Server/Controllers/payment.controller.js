
const PaymentModel = require("../Models/payment.models");

// Payment controller to save the payment data in mongo database 
const postPaymentData = async (req, res) => {
const userId = req.body.userId;
const paymentType = req.body.paymentType;
const paymentAmount = req.body.paymentAmount;
const paymentStatus = req.body.paymentStatus;
const currency = req.body.currency;
const payment = new PaymentModel({
    userId: userId,
    paymentType: paymentType,
    paymentAmount: paymentAmount,
    currency: currency,
    paymentStatus: paymentStatus,
  });
  try {
    await payment.save();
    res.status(200).json({ message: "Posted payment data.", success: true });
    console.log("Inserted Data. Payment was successful");
  } catch (err) {
    console.error(err);
    res.status(404).json({ message: err.message, success: false  });
  }
};
module.exports = { postPaymentData };
