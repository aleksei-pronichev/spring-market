package ru.pronichev.exceptions;

public class ProductServiceException extends RuntimeException {
    public ProductServiceException(Throwable cause) {
        super(cause);
    }
}