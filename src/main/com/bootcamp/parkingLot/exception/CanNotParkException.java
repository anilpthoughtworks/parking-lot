package com.bootcamp.parkingLot.exception;

import com.bootcamp.parkingLot.constants.ParkingLotConstants;

public class CanNotParkException extends Exception {
    public CanNotParkException(String message) {
        super(message);
    }

    public static CanNotParkException slotIsFull(){
        return new CanNotParkException(ParkingLotConstants.PARKING_FULL);
    }
}
