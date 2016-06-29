package com.bootcamp.parkingLot;

import com.bootcamp.parkingLot.exception.CanNotParkException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingAttendantTest {

    private ParkingLot parkingLotTwo;
    private ParkingLot parkingLotOne;
    private Object carA;
    private Object tokenForCarA;

    @Before
    public void setUp() throws Exception {
        parkingLotOne = mock(ParkingLot.class);
        parkingLotTwo = mock(ParkingLot.class);
        carA = new Object();
        tokenForCarA = new Object();
    }

    @Test
    public void shouldDirectMeToParkCar() throws CanNotParkException {
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLotOne, parkingLotTwo);
        when(parkingLotOne.isSlotAvailable()).thenReturn(true);
        when(parkingLotOne.parkCar(carA)).thenReturn(tokenForCarA);
        when(parkingLotTwo.isSlotAvailable()).thenReturn(false);

        assertEquals(parkingAttendant.parkMyCar(carA), tokenForCarA);
    }

    @Test(expected = CanNotParkException.class )
    public void shouldNotGetParkingLotIfParkingFull() throws CanNotParkException {
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLotOne, parkingLotTwo);
        when(parkingLotOne.isSlotAvailable()).thenReturn(false);
        when(parkingLotTwo.isSlotAvailable()).thenReturn(false);

        parkingAttendant.parkMyCar(carA);
    }


}
