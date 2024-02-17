
const CustomError = require("../exceptions/customError");
const {findAllOrdersService, findOrderByIdService, addOrderService, updateOrderService, findOrdersByDateService,
    acceptOrderService, cancelOrderService
} = require("../services/order.service");


const findAllOrdersController = async (req, res, next) => {
    try {
        const orders = await findAllOrdersService();
        res.status(200).json(orders);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

const findOrderByIdController = async (req, res, next) => {
    try {
        const orderId = parseInt(req.params.orderId, 10);
        const order = await findOrderByIdService(orderId);
        res.status(200).json(order);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

const addOrderController = async (req, res, next) => {
    try {
        const newOrder = req.body;
        const result = await addOrderService(newOrder);
        res.status(201).json(result);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

const updateOrderController = async (req, res, next) => {
    try {
        const orderId = parseInt(req.params.orderId, 10);
        const updatedOrder = req.body; // Assuming the updated order data is in the request body
        const result = await updateOrderService(orderId, updatedOrder);
        res.json(result);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

const findOrdersByDateController = async (req, res, next) => {
    try {
        const targetDate = req.params.targetDate; // Assuming the target date is in the request parameters
        const orders = await findOrdersByDateService(targetDate);
        res.json(orders);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

const acceptOrderController = async (req, res, next) => {
    try {
        const orderId = parseInt(req.params.orderId, 10);
        const result = await acceptOrderService(orderId);
        res.json(result);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

const cancelOrderController = async (req, res, next) => {
    try {
        const orderId = parseInt(req.params.orderId, 10);
        const result = await cancelOrderService(orderId);
        res.json(result);
    } catch (error) {
        if (!(error instanceof CustomError)) {
            res.status(error.statusCode).json({error: error.message});
        } else {
            next(error);
        }
    }
};

module.exports = {
    findAllOrdersController,
    findOrderByIdController,
    addOrderController,
    updateOrderController,
    findOrdersByDateController,
    acceptOrderController,
    cancelOrderController,
};
