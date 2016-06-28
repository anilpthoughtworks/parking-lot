package com.bootcamp.parkingLot;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {

    private Object carA;
    private Object carB;
    private ParkingLot parkingLotOne, parkingLotTwo;

    @Before
    public void setup() {
        carA = new Object();
        carB = new Object();
        parkingLotOne = new ParkingLot(1);
        parkingLotTwo = new ParkingLot(2);
    }

    @Test
    public void shouldBeAbleToParkCarWhenSlotIsAvailable() throws CanNotParkException {
        Object token = parkingLotTwo.parkCar(carA);
        assertNotNull(token);
    }

    @Test(expected = CanNotParkException.class)
    public void shouldNotBeAbleParkCarWhenSlotIsUnavailable() throws CanNotParkException {
        parkingLotOne.parkCar(carA);
        parkingLotOne.parkCar(carB);
    }

    @Test
    public void shouldBeAbleToUnparkCar() throws Exception {
        Object token = parkingLotOne.parkCar(carA);
        boolean isUnparked = parkingLotOne.unparkCar(token);
        assertTrue(isUnparked);
    }

    @Test
    public void shouldNotUnparkWithInvalidToken() throws CanNotParkException {
        boolean shouldNotUnpark = parkingLotOne.unparkCar(new Object());
        assertFalse(shouldNotUnpark);
    }
}
