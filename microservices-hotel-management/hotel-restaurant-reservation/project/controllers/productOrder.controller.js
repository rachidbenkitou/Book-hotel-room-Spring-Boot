const {CustomError} = require('../exceptions/customError');
const {findProductOrderByOrderIdService} = require('../services/productOrder.service');

const findProductOrderByOrderIdController = async (req, res, next) => {
    try {
        const {orderId} = req.params;

        const productOrders = await findProductOrderByOrderIdService(parseInt(orderId, 10));
        res.status(200).json(productOrders);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

module.exports = {
    findProductOrderByOrderIdController,
};
