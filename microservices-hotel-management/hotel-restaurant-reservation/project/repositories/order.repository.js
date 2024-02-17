const {PrismaClient} = require('@prisma/client');
const prisma = new PrismaClient();

const findAllOrdersRepository = async () => {
    return prisma.order.findMany({});
};
const findOrderByIdRepository = async (orderId) => {
    return prisma.order.findUnique({
        where: {id: orderId},
    });
};
const addOrderRepository = async (newOrder) => {
    return prisma.order.create({
        data: newOrder,
    });
};
const updateOrderRepository = async (orderId, updatedOrder) => {
    return prisma.order.update({
        where: {id: orderId},
        data: updatedOrder,
    });
};
const findOrdersByDateRepository = async (targetDate) => {
    return prisma.order.findMany({
        where: {date: targetDate},
    });
};
const acceptOrderRepository = async (orderId) => {
    return prisma.order.update({
        where: { id: orderId },
        data: { status: 'Accepted' },
    });
};
const cancelOrderRepository = async (orderId) => {
    return prisma.order.update({
        where: { id: orderId },
        data: { status: 'Cancelled' },
    });
};
module.exports = {
    findAllOrdersRepository,
    findOrderByIdRepository,
    addOrderRepository,
    updateOrderRepository,
    findOrdersByDateRepository,
    acceptOrderRepository,
    cancelOrderRepository
};
