const {PrismaClient} = require('@prisma/client');
const prisma = new PrismaClient();
const addImageRepository = async (newImage) => {
    return prisma.image.create({
        data: newImage,
    });
};
const deleteImageRepository = async (imageId) => {
    return prisma.image.delete({
        where: {id: imageId},
    });
};

const findImagesByProductIdRepository = async (productId) => {
    return prisma.image.findMany({
        where: {productId},
    });
};

const imageExistsRepository = async (imageId) => {
    return prisma.image.findUnique({
        where: {id: imageId},
    });
};

module.exports = {
    addImageRepository,
    deleteImageRepository,
    findImagesByProductIdRepository,
    imageExistsRepository
};
