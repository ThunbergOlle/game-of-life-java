package com.thunbergolle.errors;

public class InvalidGridSizeException extends IllegalArgumentException {
    public InvalidGridSizeException(Integer gridSize) {
        super("Invalid grid size. The size must be greater than 0. Got: " + gridSize);
    }
}
