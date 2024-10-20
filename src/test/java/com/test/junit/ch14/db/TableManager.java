package com.test.junit.ch14.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableManager {


    // 테이블 생성
    public static void createTable(Connection connection) {
        String sql =
                "CREATE TABLE IF NOT EXISTS PASSENGERS (ID VARCHAR(50), " + "NAME VARCHAR(50));";
        executeStatement(connection, sql);
    }

    //테이블 삭제
    public static  void  dropTable(Connection connection) {
        String sql = "DROP TABLE IF EXISTS PASSENGERS;";
        executeStatement(connection, sql);
    }

    // sql 실행
    private static  void executeStatement(Connection connection, String sql) {
         try(PreparedStatement statement = connection.prepareStatement(sql)) {
             statement.executeUpdate();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }
}
