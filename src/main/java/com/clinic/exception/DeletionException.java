package com.clinic.exception;

/**
 * Exception thrown when there is an issue with deletion.
 */
public class DeletionException extends RuntimeException {

    /**
     * Constructs a new DeletionException with the specified detail message.
     *
     * @param message the detail message.
     */
    public DeletionException(String message) {
        super(message);
    }
}
