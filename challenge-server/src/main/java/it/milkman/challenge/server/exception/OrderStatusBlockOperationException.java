package it.milkman.challenge.server.exception;

public class OrderStatusBlockOperationException extends RuntimeException {
    public OrderStatusBlockOperationException(String message) {
        super(message);
    }

    public OrderStatusBlockOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
