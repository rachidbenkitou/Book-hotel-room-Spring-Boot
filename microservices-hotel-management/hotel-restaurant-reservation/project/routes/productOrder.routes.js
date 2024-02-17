const {Router} = require('express');
const {findProductOrderByOrderIdController} = require('../controllers/productOrder.controller');

const productOrderRouter = Router();

productOrderRouter.get('/order/:orderId', findProductOrderByOrderIdController);

module.exports = {
    productOrderRouter,
};
