const {Router} = require("express");
const {
    findAllProducts,
    addProductController,
    updateProductController,
    deleteProductByIdController, findProductByIdController, findProductsByCategoryIdController
} = require("../controllers/product.controller");
const productRouter = Router();

productRouter.get("/", findAllProducts);
productRouter.get("/:id", findProductByIdController);
productRouter.get("/category/:categoryId", findProductsByCategoryIdController);
productRouter.post("/", addProductController);
productRouter.put("/:id", updateProductController);
productRouter.delete("/:id", deleteProductByIdController);

module.exports = {
    productRouter: productRouter,
};
