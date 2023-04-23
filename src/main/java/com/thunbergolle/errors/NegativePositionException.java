package com.thunbergolle.errors;

public class NegativePositionException extends Error {
    public NegativePositionException() {
        super("Invalid position");
    }
}
