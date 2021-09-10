
// Reference Links
// https://stackoverflow.com/questions/24330014/bodyparser-is-deprecated-express-4

// Controller to use Stripe payment as backend
const express = require("express")
const StripePayment = express()
require("dotenv").config()
const stripe = require("stripe")(process.env.STRIPE_SECRET_TEST)
const bodyParser = require("body-parser")
const cors = require("cors")

StripePayment.use(express.urlencoded({
  extended: true
}));
StripePayment.use(express.json());

StripePayment.use(cors())

StripePayment.post("/payment", cors(), async (req, res) => {
	let { amount, id } = req.body
	try {
		const payment = await stripe.paymentIntents.create({
			amount,
			currency: "INR",
			description: "Spatula company",
			payment_method: id,
			confirm: true
		})
		console.log("Payment", payment)
		res.json({
			message: "Payment successful",
			success: true
		})
	} catch (error) {
		console.log("Error", error)
		res.json({
			message: "Payment failed",
			success: false
		})
	}
})

module.exports = StripePayment;
