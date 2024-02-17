const {addImageRepository, findImagesByProductIdRepository, imageExistsRepository, deleteImageRepository} = require("../repositories/image.repository");
const CustomError = require("../exceptions/customError");
const addImageService = async (newImage) => {
    return addImageRepository(newImage);
};

const deleteImageService = async (imageId) => {
    // Check if the image exists before deleting
    const existingImage = await imageExistsRepository(imageId);

    if (!existingImage) {
        throw new CustomError("Image not found", 404);
    }

    // Image exists, proceed with deleting
    const deletedImage = await deleteImageRepository(imageId);

    return deletedImage;
};
const findImagesByProductIdService = async (productId) => {
    return findImagesByProductIdRepository(productId);
};

module.exports = {
    addImageService,
    deleteImageService,
    findImagesByProductIdService,
};
