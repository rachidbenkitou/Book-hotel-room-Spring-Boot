const {Router} = require("express");
const {addImageController, deleteImageController, findImagesByProductIdController} = require("../controllers/image.controller");
const imageRouter = Router();

// Define the route for adding an image
imageRouter.post("/", addImageController);

// Define the route for deleting an image by ID
imageRouter.delete("/:id", deleteImageController);

// Define the route for finding images by product ID
imageRouter.get("/product/:productId", findImagesByProductIdController);

module.exports = {
    imageRouter,
};
