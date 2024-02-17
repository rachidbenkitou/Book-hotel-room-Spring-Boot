const CustomError = require("../exceptions/customError");
const {
    getAllCategoriesService, findCategoryByIdService, addCategoryService, updateCategoryService,
    deleteCategoryByIdService
} = require("../services/category.service");
const findAllCategoriesController = async (req, res, next) => {
    try {
        const categories = await getAllCategoriesService();
        res.status(200).json(categories);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            // Handle custom errors
            res.status(error.statusCode).json({error: error.message});
        } else {
            // Handle other unexpected errors
            next(error);
        }
    }
};

const findCategoryByIdController = async (req, res, next) => {
    try {
        const categoryId = parseInt(req.params.id); // Assuming the category ID is passed as a URL parameter

        // Call the findProductByIdService to get the product
        const category = await findCategoryByIdService(categoryId);

        res.status(200).json(category);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            // Handle other unexpected errors
            next(error);
        }
    }
};

const addCategoryController = async (req, res, next) => {
    try {
        const newCategory = req.body; // Assuming the new category data is sent in the request body

        const createdCategory = await addCategoryService(newCategory, next);

        res.status(201).json(createdCategory);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};
const updateCategoryController = async (req, res, next) => {
    try {
        const categoryId = parseInt(req.params.id);
        const updatedCategory = req.body;

        const updatedCategoryResult = await updateCategoryService(categoryId, updatedCategory);

        res.status(200).json(updatedCategoryResult);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }

};
const deleteCategoryByIdController = async (req, res, next) => {
    try {
        const categoryId = parseInt(req.params.id);
        await deleteCategoryByIdService(categoryId);

        res.json({message: "Category deleted successfully"});
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};


module.exports = {
    findAllCategoriesController,
    findCategoryByIdController,
    addCategoryController,
    updateCategoryController,
    deleteCategoryByIdController
};
