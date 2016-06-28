package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.constants.ParkingLotConstants;
import com.bootcamp.parkingLot.exception.CanNotParkException;
import com.bootcamp.parkingLot.observer.ParkingLotObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLot {
    private int availableSlots;
    private HashMap<Object, Object> carTicket;
    private List<ParkingLotObserver> observers;

    public ParkingLot(int slots) {
        this.availableSlots = slots;
        carTicket = new HashMap<Object, Object>();
        observers = new ArrayList();
    }

    public Object parkCar(Object car) throws CanNotParkException {
        if (slotAvailable()) {
            Object token = new Object();
            carTicket.put(token, car);
            availableSlots--;
            if (isFull()) notifyObserver();
            return token;
        } else {
            throw CanNotParkException.slotIsFull();
        }
    }

    private void notifyObserver() {
        for (ParkingLotObserver observer : observers) {
            observer.update(ParkingLotConstants.PARKING_FULL);
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

    public boolean isFull() {
        return availableSlots == 0;
    }

    public void addObserver(ParkingLotObserver parkingLotObserver) {
        this.observers.add(parkingLotObserver);
    }
}
