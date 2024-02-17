// productOrder.service.js
const { CustomError } = require('../exceptions/customError');
const { findProductOrderByOrderIdRepository } = require('../repositories/productOrder.repository');

const findProductOrderByOrderIdService = async (orderId) => {
        return findProductOrderByOrderIdRepository(orderId);
};

module.exports = {
    findProductOrderByOrderIdService,
};
