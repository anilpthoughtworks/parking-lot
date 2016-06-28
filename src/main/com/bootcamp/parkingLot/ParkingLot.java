package com.bootcamp.parkingLot;

import java.util.HashMap;

public class ParkingLot {
    private int availableSlots;
    private HashMap<Object, Object> carTicket;

    public ParkingLot(int slots) {
        this.availableSlots = slots;
        carTicket = new HashMap<Object, Object>();
    }

    public Object parkCar(Object car) throws CanNotParkException {
        if (slotAvailable()) {
            Object token = new Object();
            carTicket.put(token, car);
            availableSlots--;
            return token;
        } else {
            throw CanNotParkException.slotIsFull();
        }
    }

    private boolean slotAvailable() {
        return availableSlots > 0;
    }

    public boolean unparkCar(Object token) {
        if (carTicket.containsKey(token)) {
            carTicket.remove(token);
            availableSlots++;
            return true;
        } else
            return false;
    }
}
