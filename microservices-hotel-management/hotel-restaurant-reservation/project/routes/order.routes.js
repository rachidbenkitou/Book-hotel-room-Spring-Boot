const { Router } = require("express");
const {findAllOrdersController, addOrderController, findOrderByIdController, updateOrderController,
    findOrdersByDateController, acceptOrderController, cancelOrderController
} = require("../controllers/order.controller");
const orderRouter = Router();

orderRouter.get("/", findAllOrdersController);
orderRouter.post("/", addOrderController);

orderRouter.get("/:orderId", findOrderByIdController);
orderRouter.put("/:orderId", updateOrderController);

orderRouter.get("/date/:targetDate", findOrdersByDateController);

orderRouter.put("/accept/:orderId", acceptOrderController);

orderRouter.put("/cancel/:orderId", cancelOrderController);

module.exports = {
    orderRouter,
};
