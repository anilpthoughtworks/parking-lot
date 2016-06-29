package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.constants.ParkingLotConstants;
import com.bootcamp.parkingLot.exception.CanNotParkException;
import com.bootcamp.parkingLot.observer.ParkingLotObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLot {
    private int availableSlots;
    private HashMap<Object, Object> tokenVehicleMap;
    private List<ParkingLotObserver> observers;

    public ParkingLot(int slots) {
        this.availableSlots = slots;
        tokenVehicleMap = new HashMap<Object, Object>();
        observers = new ArrayList();
    }

    public Object parkCar(Object car) throws CanNotParkException {
        if (isSlotAvailable()) {
            Object token = new Object();
            tokenVehicleMap.put(token, car);
            if (isFull())
                notifyObserver();
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

    public boolean isSlotAvailable() {
        return availableSlots > 0;
    }

    public Object unparkCar(Object token) throws CanNotParkException {
        if (tokenVehicleMap.containsKey(token)) {
            Object car = tokenVehicleMap.remove(token);
            availableSlots++;
            return car;
        } else
            throw CanNotParkException.invalidToken();
    }

    public boolean isFull() {
        return availableSlots == 0;
    }

    public void addObserver(ParkingLotObserver parkingLotObserver) {
        this.observers.add(parkingLotObserver);
    }
}
