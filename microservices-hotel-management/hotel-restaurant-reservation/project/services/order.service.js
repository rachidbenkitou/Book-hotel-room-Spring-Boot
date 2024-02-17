
const CustomError = require("../exceptions/customError");
const {
    findAllOrdersRepository,
    findOrderByIdRepository, addOrderRepository, updateOrderRepository, acceptOrderRepository, cancelOrderRepository,
    findOrdersByDateRepository
} = require("../repositories/order.repository");

const findAllOrdersService = async () => {
    return findAllOrdersRepository();
};
const findOrderByIdService = async (orderId) => {
    const foundOrder = await findOrderByIdRepository(orderId);
    if (!foundOrder) {
        throw new CustomError('Order not found', 404);
    }
    return foundOrder;
};
const addOrderService = async (newOrder) => {
    return addOrderRepository(newOrder);
};
const updateOrderService = async (orderId, updatedOrder) => {
    // Check if the order exists
    const existingOrder = await findOrderByIdRepository(orderId);
    if (!existingOrder) {
        throw new CustomError('Order not found', 404);
    }
    // If the order exists, proceed with the update
    return updateOrderRepository(orderId, updatedOrder);
};
const acceptOrderService = async (orderId) => {
    // Check if the order exists
    const existingOrder = await findOrderByIdRepository(orderId);
    if (!existingOrder) {
        throw new CustomError('Order not found', 404);
    }
    return acceptOrderRepository(orderId);
};
const cancelOrderService = async (orderId) => {
    // Check if the order exists
    const existingOrder = await findOrderByIdRepository(orderId);
    if (!existingOrder) {
        throw new CustomError('Order not found', 404);
    }
    return cancelOrderRepository(orderId);
};
const findOrdersByDateService = async (targetDate) => {
    const orders = await findOrdersByDateRepository(targetDate);
    if (!orders || orders.length === 0) {
        throw new CustomError('No orders found for the specified date', 404);
    }
    return orders;
};

module.exports = {
    findAllOrdersService,
    findOrderByIdService,
    addOrderService,
    updateOrderService,
    findOrdersByDateService,
    acceptOrderService,
    cancelOrderService,
};
