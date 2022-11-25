package com.dragonjeet.tankstars.exception;

public class EmptySlotSelectedException extends Exception {
    public EmptySlotSelectedException(String message) {
        super(message);
        // This exception needs to be handled and a label will be displayed on the screen
    }
}
