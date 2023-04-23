package com.thunbergolle.errors;

public class InvalidSquareSizeException extends IllegalArgumentException {
    public InvalidSquareSizeException(Integer squareSize) {
        super("Invalid square size. The size must be greater than 0. Got: " + squareSize);
    }
}
