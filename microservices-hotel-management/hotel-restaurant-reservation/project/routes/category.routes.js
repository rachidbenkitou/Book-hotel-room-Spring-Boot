const {Router} = require("express");
const {
    findCategoryByIdController, addCategoryController, updateCategoryController,
    deleteCategoryByIdController, findAllCategoriesController
} = require("../controllers/category.controller");
const categoryRouter = Router();

categoryRouter.get("/", findAllCategoriesController);
categoryRouter.get("/:id", findCategoryByIdController);
categoryRouter.post("/", addCategoryController);
categoryRouter.put("/:id", updateCategoryController);
categoryRouter.delete("/:id", deleteCategoryByIdController);

module.exports = {
    categoryRouter: categoryRouter,
};
