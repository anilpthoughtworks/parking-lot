package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.CanNotParkException;

public class ParkingAttendant {
    private ParkingLot[] allottedParkingLots;

    public ParkingAttendant(ParkingLot... parkingLots) {
        this.allottedParkingLots = parkingLots;
    }

    public Object parkMyVehicle(Object vehicle) throws CanNotParkException {
        for (ParkingLot parkingLot : allottedParkingLots)
            if (parkingLot.isSlotAvailable()) {
                return parkingLot.park(vehicle);
            }
        throw CanNotParkException.slotIsFull();
    }


    public Object unparkMyVehicle(Object parkingToken) throws CanNotParkException {
        for (ParkingLot parkingLot : allottedParkingLots)
            if (parkingLot.containsToken(parkingToken)) {
                return parkingLot.unpark(parkingToken);
            }
        throw CanNotParkException.invalidToken();
    }
}
