const express = require("express");
const dotenv = require("dotenv");
const {productRouter} = require("./project/routes/product.routes");
const {errorHandler} = require("./project/middlewares/error.middleware");
const {imageRouter} = require("./project/routes/image.routes");
const {orderRouter} = require("./project/routes/order.routes");
const {productOrderRouter} = require("./project/routes/productOrder.routes");
const {categoryRouter} = require("./project/routes/category.routes");
const cors = require("cors");


dotenv.config(); // Load environment variables from a .env file if present
const app = express();
const port = 3000;

// express.json() is a built-in middleware provided by Express to parse incoming requests with JSON payloads.
// Request with JSON Payload: When a client sends a request with a JSON payload (e.g., in the request body),
// the express.json() middleware intercepts the request.
// Parsing JSON Data: The middleware parses the JSON data in the request body and converts it into a JavaScript object.
/*
Recap:
The app.use(express.json()); middleware in Express.js is responsible for parsing incoming JSON data from the request
 body and converting it into a JavaScript object. This is especially useful when your client sends data in JSON format,
  commonly used in POST and PUT requests.

By using this middleware, you can conveniently work with the parsed JSON data in your route handlers using the req.body object.
 It simplifies the process of handling JSON data in your Express application, making it easier to extract and manipulate
  the information sent by clients in their requests.
* */
app.use(express.json());

// Use the cors middleware
app.use(cors());

// Use routes
app.use("/api/v1/products", productRouter);
app.use("/api/v1/images", imageRouter);
app.use("/api/v1/orders", orderRouter);
app.use("/api/v1/productOrders", productOrderRouter);
app.use("/api/v1/categories", categoryRouter);

// Error Handling
app.use(errorHandler);

app.listen(port, () => console.log(`App listening on port ${port}`));
