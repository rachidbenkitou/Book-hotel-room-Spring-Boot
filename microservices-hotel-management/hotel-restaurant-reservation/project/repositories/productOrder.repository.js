const {PrismaClient} = require('@prisma/client');
const prisma = new PrismaClient();

const findProductOrderByOrderIdRepository = async (orderId) => {
    return prisma.productOrder.findMany({
        where: {
            orderId: orderId,
        },
    });
};

module.exports = {
    findProductOrderByOrderIdRepository,
};
