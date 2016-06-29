package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.constants.ParkingLotConstants;
import com.bootcamp.parkingLot.observer.ParkingLotObserver;

public class ParkingOwner implements ParkingLotObserver {

    public void parkingLotIsFull() {
        System.out.println(ParkingLotConstants.PARKING_FULL);
    }

    @Override
    public void parkingIsAvailable() {
        System.out.println(ParkingLotConstants.PARKING_AVAILABLE);
    }
}
