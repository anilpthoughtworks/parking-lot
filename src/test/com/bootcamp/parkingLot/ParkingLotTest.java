package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.constants.ParkingLotConstants;
import com.bootcamp.parkingLot.exception.CanNotParkException;
import com.bootcamp.parkingLot.observer.ParkingLotObserver;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

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

    @Test
    public void shouldInformWhenParkingLotIfFull() throws CanNotParkException {
        ParkingLotObserver parkingOwnerMock = mock(ParkingOwner.class);
        ParkingLotObserver securityOfficerMock = mock(SecurityOfficer.class);
        parkingLotTwo.addObserver(parkingOwnerMock);
        parkingLotTwo.addObserver(securityOfficerMock);

        parkingLotTwo.parkCar(carA);
        parkingLotTwo.parkCar(carB);

        verify(parkingOwnerMock, times(1)).update(ParkingLotConstants.PARKING_FULL);
        verify(securityOfficerMock, times(1)).update(ParkingLotConstants.PARKING_FULL);
    }

}
