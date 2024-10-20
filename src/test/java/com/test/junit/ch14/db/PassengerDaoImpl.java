package com.test.junit.ch14.db;

import com.test.junit.ch14.PassengerExistException;
import com.test.junit.ch14.domain.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDaoImpl implements PassengerDao  {
    private Connection con;


    // 생성자에서 Connection 초기화
    public PassengerDaoImpl(Connection connection) {
        this.con = connection;
    }

    @Override
    public void insert(Passenger passenger) throws PassengerExistException {

        if(null != getById(passenger.getIdentifier())) {
            throw new PassengerExistException(passenger, passenger.toString());
        }

        String sql = "INSERT INTO PASSENGERS (ID, NAME) VALUES (?, ?)";

        try(PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, passenger.getIdentifier());

            statement.setString(2, passenger.getName());
            statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public void update(String id, String name) {

        String sql = "UPDATE PASSENGERS SET NAME = ? WHERE ID = ?";

        try(PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(Passenger passenger) {
        String sql = "DELETE FROM PASSENGERS WHERE ID = ?";

        try(PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, passenger.getIdentifier());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Passenger getById(String id) {
        String sql = "SELECT * FROM PASSENGERS WHERE ID = ?";
        Passenger passenger = null;
        try(PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                passenger = new Passenger(resultSet.getString(1), resultSet.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return passenger;
    }

}