package ru.pronichev.exception;

public class PictureServiceException extends RuntimeException {
    public PictureServiceException(String message) {
        super(message);
    }

    public PictureServiceException(Throwable cause) {
        super(cause);
    }
}