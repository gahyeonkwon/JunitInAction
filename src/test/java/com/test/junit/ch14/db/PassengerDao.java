package com.test.junit.ch14.db;


import com.test.junit.ch14.PassengerExistException;
import com.test.junit.ch14.domain.Passenger;

/**
 *  DAO : 영속성 계츠으이 세부 사항을 노출하지 않은 채 데이터베이스에 대한 인터페이스를 제공하고, 애플리케이션 호출을 특정 데이터베이스 작업으로 연결하는 역할을 함
 */
public interface PassengerDao {
    public void insert (Passenger passenger) throws PassengerExistException ;
    public void update(String id, String name);
    public void delete(Passenger passenger);
    public Passenger getById(String id);
}
