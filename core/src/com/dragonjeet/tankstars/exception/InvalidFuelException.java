package com.dragonjeet.tankstars.exception;

public class InvalidFuelException extends Exception {
    public InvalidFuelException(String message) {
        super(message);
        // This exception needs to be handled and a label will be displayed on the screen
    }
}
