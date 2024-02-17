const {PrismaClient} = require('@prisma/client');

const prisma = new PrismaClient();
const findProductsRepository = async () => {
    return prisma.product.findMany({
        include: {
            category: true, // Assuming there is a "category" relation in your Product model
        },
    });
};

const addProductRepository = async (newProduct) => {
    return prisma.product.create({
        data: newProduct,
    });
};

const findProductByNameRepository = async (productName) => {
    return prisma.product.findFirst({
        where: {
            name: productName,
        },
    });
};

const productExistsByNameRepository = async (productName) => {
    const existingProduct = await findProductByNameRepository(productName);
    return !!existingProduct; // Returns true if the product with the given name exists, false otherwise
};


const findProductByIdRepository = async (productId) => {
    return prisma.product.findUnique({
        where: { id: productId },
    });
};

const findProductsByCategoryIdRepository = async (categoryId) => {
    return prisma.product.findMany({
        where: {
            categoryId: categoryId,
        },
    });
};
const updateProductRepository = async (productId, updatedProduct) => {
    return prisma.product.update({
        where: { id: productId },
        data: updatedProduct,
    });
};


const deleteProductByIdRepository = async (productId) => {
    return prisma.product.delete({
        where: { id: productId },
    });
};
module.exports = {
    findProductsRepository,
    addProductRepository,
    findProductByNameRepository,
    productExistsByNameRepository,
    findProductByIdRepository,
    updateProductRepository,
    deleteProductByIdRepository,
    findProductsByCategoryIdRepository
};
