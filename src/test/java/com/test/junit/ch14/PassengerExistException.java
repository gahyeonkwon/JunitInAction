package com.test.junit.ch14;

import com.test.junit.ch14.domain.Passenger;


/**
 *  동일한 승객이 두 번 이상 중복되어 저장되지 않도록 하는 기능
 */
public class PassengerExistException extends Exception {
    private Passenger passenger;

    public PassengerExistException(Passenger passenger, String message) {
        super(message);
        this.passenger = passenger;
    }
}
