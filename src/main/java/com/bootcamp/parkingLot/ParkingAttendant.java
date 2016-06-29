package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.CanNotParkException;

public class ParkingAttendant {
    private ParkingLot[] allottedParkingLots;

    public ParkingAttendant(ParkingLot... parkingLots) {
        this.allottedParkingLots = parkingLots;
    }

    public Object parkMyCar(Object vehicle) throws CanNotParkException {
        for (ParkingLot parkingLot : allottedParkingLots)
            if (parkingLot.isSlotAvailable()) {
                return parkingLot.parkCar(vehicle);
            }
        throw CanNotParkException.slotIsFull();
    }


}
