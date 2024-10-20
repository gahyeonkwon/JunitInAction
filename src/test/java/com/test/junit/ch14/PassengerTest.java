package com.test.junit.ch14;

import com.test.junit.ch14.db.PassengerDao;
import com.test.junit.ch14.domain.Passenger;
import com.test.junit.ch14.extension.DataAccessObjectParameterResolver;
import com.test.junit.ch14.extension.DatabaseOperationsExtension;
import com.test.junit.ch14.extension.ExecutionContextExtension;
import com.test.junit.ch14.extension.LogPassengerExistsExceptionExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *  ExecutionContextExtension - 테스트 실행조건
 *  DatabaseOperationsExtension - 테스트의 생애주기
 *  DataAccessObjectParameterResolver - 테스트 클래스 생성시 필요한 파라미터를 리졸브
 */
@ExtendWith({ExecutionContextExtension.class,
            DatabaseOperationsExtension.class,
            DataAccessObjectParameterResolver.class,
            LogPassengerExistsExceptionExtension.class})
public class PassengerTest {

    private final PassengerDao passengerDao;

    public PassengerTest(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Test
    void testPassenger() {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        assertEquals("Passenger{identifier='123-456-789', name='John Smith'}", passenger.toString());
    }

    @Test
    void testInsertPassenger() throws PassengerExistException {
        Passenger passenger = new Passenger("123-456-789", "John smith");
        passengerDao.insert(passenger);
        assertEquals("John smith", passengerDao.getById("123-456-789").getName());
    }

    @Test
    void  testUpdatePassenger() throws PassengerExistException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.update("123-456-789", "Michael Smith");
        assertEquals("Michael Smith", passengerDao.getById("123-456-789").getName());
        //passengerDao.delete(passenger);
    }


    @Test
    void testDeletePassenger() throws PassengerExistException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.delete(passenger);
        assertNull(passengerDao.getById("123-456-789"));
    }


    @Test
    void testInsertExistingPassenger() throws PassengerExistException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.insert(passenger);
        assertEquals("John Smith", passengerDao.getById("123-456-789").getName());
    }

}
