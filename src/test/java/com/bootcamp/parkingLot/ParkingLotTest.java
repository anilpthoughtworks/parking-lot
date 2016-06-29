package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.constants.ParkingLotConstants;
import com.bootcamp.parkingLot.exception.CanNotParkException;
import com.bootcamp.parkingLot.observer.ParkingLotObserver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
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
        Object token = parkingLotTwo.park(carA);
        assertNotNull(token);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test(expected = CanNotParkException.class)
    public void shouldNotBeAbleParkCarWhenSlotIsUnavailable() throws CanNotParkException {
        parkingLotOne.park(carA);
        parkingLotOne.park(carB);
        expectedEx.expect(CanNotParkException.class);
        expectedEx.expectMessage(ParkingLotConstants.PARKING_FULL);
    }

    @Test
    public void shouldBeAbleToUnparkCar() throws Exception {
        Object token = parkingLotOne.park(carA);
        Object car = parkingLotOne.unpark(token);
        assertEquals(car, carA);
    }

    @Test
    public void shouldNotUnparkWithInvalidToken() throws CanNotParkException {
        Object tokenForCarA = parkingLotOne.park(carA);
        Object car = parkingLotOne.unpark(tokenForCarA);
        assertNotNull(car);
    }

    @Test
    public void shouldInformWhenParkingLotIfFull() throws CanNotParkException {
        ParkingLotObserver parkingOwnerMock = mock(ParkingLotObserver.class);
        parkingLotTwo.addObserver(parkingOwnerMock);
        parkingLotTwo.park(carA);

        verify(parkingOwnerMock, times(0)).parkingLotIsFull();

        parkingLotTwo.park(carB);
        verify(parkingOwnerMock, times(1)).parkingLotIsFull();
    }

    @Test(expected = CanNotParkException.class)
    public void shouldNotBeAbleToUnparkCarWithInvalidToken() throws Exception {
        Object invalidToken = new Object();

        parkingLotOne.unpark(invalidToken);
    }

    @Test
    public void shouldNotifyObserverWhenParkingIsAvailable() throws CanNotParkException {
        ParkingOwner parkingOwner = mock(ParkingOwner.class);
        parkingLotOne.addObserver(parkingOwner);

        Object tokenForCarA = parkingLotOne.park(carA);
        parkingLotOne.unpark(tokenForCarA);

        verify(parkingOwner, times(1)).parkingIsAvailable();
    }

    @Test
    public void shouldNotifyObserverOnlyOnceWhenParkingIsAvailable() throws CanNotParkException {
        ParkingOwner parkingOwner = mock(ParkingOwner.class);
        parkingLotTwo.addObserver(parkingOwner);

        Object tokenForCarA = parkingLotTwo.park(carA);
        Object tokenForCarB = parkingLotTwo.park(carB);
        parkingLotTwo.unpark(tokenForCarA);
        parkingLotTwo.unpark(tokenForCarB);

        verify(parkingOwner, times(1)).parkingIsAvailable();
    }
}
