// Error handling middleware
const errorHandler = (error, req, res, next) => {
    const statusCode = error.statusCode || 500;
    switch (statusCode) {
        case 400:
            res.status(statusCode).json({
                status: statusCode,
                message: error.message,
                timstamp: new Date(),
                // stackTrace: error.stack,
            });
            break;
        case 404:
            res.status(statusCode).json({
                status: statusCode,
                message: error.message,
                timstamp: new Date(),
                // stackTrace: error.stack,
            });
            break;
        case 409:
            res.status(statusCode).json({
                status: statusCode,
                message: error.message,
                timstamp: new Date(),
                // stackTrace: error.stack,
            });
            break;
        case 401:
            res.status(statusCode).json({
                status: statusCode,
                message: error.message,
                timstamp: new Date(),
                // stackTrace: error.stack,
            });
            break;

        default:
            res.status(statusCode).json({
                status: statusCode,
                message: error.message,
                timstamp: new Date(),
                stackTrace: error.stack,
            });
            break;
    }
};

module.exports = {
    errorHandler,
};
