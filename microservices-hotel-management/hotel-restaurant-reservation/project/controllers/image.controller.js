const {addImageService, deleteImageService, findImagesByProductIdService} = require("../services/image.service");
const CustomError = require("../exceptions/customError");
const addImageController = async (req, res, next) => {
    try {
        const newImage = req.body;
        const addedImage = await addImageService(newImage);

        res.status(201).json(addedImage);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

const deleteImageController = async (req, res, next) => {
    try {
        const imageId = parseInt(req.params.id);

        await deleteImageService(imageId);

        res.status(200).json({message: "Image deleted successfully"});
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

const findImagesByProductIdController = async (req, res, next) => {
    try {
        const productId = parseInt(req.params.productId);
        const images = await findImagesByProductIdService(productId);

        res.status(200).json(images);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

module.exports = {
    addImageController,
    deleteImageController,
    findImagesByProductIdController,
};
