package com.dragonjeet.tankstars.exception;

public class TankOutOfScreenException extends Exception {
    public TankOutOfScreenException(String message) {
        super(message);
        // This exception needs to be handled and a label will be displayed on the screen
    }
}
