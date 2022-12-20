package com.dragonjeet.tankstars.exception;

public class TankDeadException extends Exception {
    public TankDeadException(String message) {
        super(message);
        // This exception needs to be handled and a label will be displayed on the screen
    }
}

