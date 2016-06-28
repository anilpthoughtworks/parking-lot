package com.bootcamp.parkingLot.exception;

public class CanNotParkException extends Exception {
    public CanNotParkException(String message) {
        super(message);
    }

    public static CanNotParkException slotIsFull(){
        return new CanNotParkException("Slot if full");
    }
}
