const products = require("../Models/products.models");

const getProducts = async (req, res) => {
  await products
    .find()
    .then((data) => {
      console.log(data);
      res.status(200).json({ message: "Received", products: data });
    })
    .catch((e) => {
      console.error(e);
      res.status(404).json({ message: e.message });
    });

  res.status(404).json({ message: "Failed" });
};

module.exports = { getProducts };
