package com.example.namesorter.domain;

/** Thrown when a line of text cannot be turned into a valid {@link Name}. */
public class InvalidNameException extends RuntimeException {

    public InvalidNameException(String message) {
        super(message);
    }
}
