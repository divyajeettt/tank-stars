package com.dragonjeet.tankstars.exception;

public class InvalidHealthException extends Exception {
    public InvalidHealthException(String message) {
        super(message);
        // This exception needs to be handled and a label will be displayed on the screen
    }
}
