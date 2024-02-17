const {PrismaClient} = require('@prisma/client');

const prisma = new PrismaClient();
const findCategoriesRepository = async () => {
    return prisma.category.findMany({});
};

const addCategoryRepository = async (newCategory) => {
    return prisma.category.create({
        data: newCategory,
    });
};

const findCategoryByNameRepository = async (categoryName) => {
    return prisma.category.findFirst({
        where: {
            name: categoryName,
        },
    });
};

const categoryExistsByNameRepository = async (categoryName) => {
    const existingCategory = await findCategoryByNameRepository(categoryName);
    return !!existingCategory; // Returns true if the category with the given name exists, false otherwise
};


const findCategoryByIdRepository = async (categoryId) => {
    return prisma.category.findUnique({
        where: {id: categoryId},
    });
};
const updateCategoryRepository = async (categoryId, updatedCategory) => {
    return prisma.category.update({
        where: {id: categoryId},
        data: updatedCategory,
    });
};


const deleteCategoryByIdRepository = async (categoryId) => {
    return prisma.category.delete({
        where: {id: categoryId},
    });
};
module.exports = {
    findCategoriesRepository,
    addCategoryRepository,
    findCategoryByNameRepository,
    categoryExistsByNameRepository,
    findCategoryByIdRepository,
    updateCategoryRepository,
    deleteCategoryByIdRepository
};
