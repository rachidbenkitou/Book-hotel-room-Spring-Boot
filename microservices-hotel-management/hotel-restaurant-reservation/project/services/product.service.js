const CustomError = require("../exceptions/customError");
const {
    addProductRepository,
    findProductsRepository,
    productExistsByNameRepository, findProductByIdRepository, updateProductRepository, deleteProductByIdRepository,
    findProductsByCategoryIdRepository
} = require("../repositories/prodcut.repository");

const getAllProductsService = async () => {
    return findProductsRepository();
};

const findProductByIdService = async (productId) => {
    // Check if a product with the given ID exists
    const existingProduct = await findProductByIdRepository(productId);

    if (!existingProduct) {
        throw new CustomError("Product not found", 404);
    }

    // If the product exists, return it
    return existingProduct;
};

const findProductsByCategoryIdService = async (categoryId) => {
        return findProductsByCategoryIdRepository(categoryId);
};

const addProductService = async (newProduct) => {
    // Check if a product with the same name already exists
    const productExists = await productExistsByNameRepository(newProduct.name);

    if (productExists) {
        throw new CustomError("Product already exists", 400); // You can choose an appropriate status code
    }

    // If the product doesn't exist, proceed with creating a new one
    return addProductRepository(newProduct);

};

const updateProductService = async (productId, updatedProduct) => {

    // Check if a product with the given ID exists
    const existingProduct = await findProductByIdRepository(productId);

    if (!existingProduct) {
        throw new CustomError("Product not found", 404); // You can choose an appropriate status code
    }

    // If the product exists, proceed with updating it
    return updateProductRepository(productId, updatedProduct);

};

const deleteProductByIdService = async (productId) => {
    // Check if a product with the given ID exists
    const existingProduct = await findProductByIdRepository(productId);

    if (!existingProduct) {
        throw new CustomError("Product not found", 404);
    }

    // If the product exists, proceed with deleting it
    await deleteProductByIdRepository(productId);
};
module.exports = {
    getAllProductsService,
    findProductByIdService,
    addProductService,
    updateProductService,
    deleteProductByIdService,
    findProductsByCategoryIdService
};
