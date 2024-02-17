const CustomError = require("../exceptions/customError");
const {
    findCategoriesRepository,
    categoryExistsByNameRepository,
    findCategoryByIdRepository,
    updateCategoryRepository, deleteCategoryByIdRepository
} = require("../repositories/category.repository");

const getAllCategoriesService = async () => {
    return findCategoriesRepository();
};

const findCategoryByIdService = async (categoryId) => {
    // Check if a category with the given ID exists
    const existingCategory = await findCategoryByIdService(categoryId);

    if (!existingCategory) {
        throw new CustomError("Category not found", 404);
    }

    // If the category exists, return it
    return existingCategory;
};

const addCategoryService = async (newCategory) => {
    // Check if a category with the same name already exists
    const categoryExists = await categoryExistsByNameRepository(newCategory.name);

    if (categoryExists) {
        throw new CustomError("Category already exists", 400); // You can choose an appropriate status code
    }

    // If the category doesn't exist, proceed with creating a new one
    return addCategoryService(newCategory);

};

const updateCategoryService = async (categoryId, updatedCategory) => {

    // Check if a category with the given ID exists
    const existingCategory = await findCategoryByIdRepository(categoryId);

    if (!existingCategory) {
        throw new CustomError("Category not found", 404); // You can choose an appropriate status code
    }

    // If the product exists, proceed with updating it
    return updateCategoryRepository(categoryId, updatedCategory);

};

const deleteCategoryByIdService = async (categoryId) => {
    // Check if a category with the given ID exists
    const existingCategory = await findCategoryByIdRepository(categoryId);

    if (!existingCategory) {
        throw new CustomError("Category not found", 404);
    }

    // If the category exists, proceed with deleting it
    await deleteCategoryByIdRepository(categoryId);
};
module.exports = {
    getAllCategoriesService,
    findCategoryByIdService,
    addCategoryService,
    updateCategoryService,
    deleteCategoryByIdService,
};
