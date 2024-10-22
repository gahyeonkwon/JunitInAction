package com.test.junit.ch14.extension;

import com.test.junit.ch14.db.TableManager;
import com.test.junit.ch14.db.ConnectionManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.Savepoint;


/**
 *   1. 전체 테스트 묶음을 실행하기 전에 데이터베이스를 초기화하고 데이터베이스 커넥션을 얻는다
 *   2. 테스트 묶음이 종료되었을 때 데이터베이스 커넥션을 반납한다
 *   3. 테스트를 실행하기 전에 데이터베이스가 알려진 상태인지 확인해서 개발자가 테스트를 정확하게 실행할 수 있는지 확인 가능하게 한다
 *
 *
 *      생애 주기 인터페이스(BeforeAll, Callback, AfterAllCallback, AfterEachCallback 구현)
 */
public class DatabaseOperationsExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    private Connection connection;
    private Savepoint savepoint;


    /**
     *  전체 테스트 묶음이 실행된 다음 한 번 실행
     *  데이터베이스 커넥션을 반납
     * @param extensionContext
     * @throws Exception
     */
    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        ConnectionManager.closeConnection();
    }


    /**
     *  각 테스트가 수행된 다음 실행. 테스트가 실행되기 전으로 데이터베이스의 상태를 롤백
     * @param extensionContext
     * @throws Exception
     */
    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        connection.releaseSavepoint(savepoint);
        connection.rollback();
    }


    /**
     *  전체 테스트 묶음이 실행되기 전에 한 번 실행
     *  데이터 베이스 커넥션을 얻고 기존 테이블을 드롭 후 새로 생성
     * @param extensionContext
     * @throws Exception
     */
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        connection = ConnectionManager.openConnection();
        TableManager.dropTable(connection);
        TableManager.createTable(connection);
    }

    /**
     * 각 테스트가 실행되기 전에 실행
     * 자동 커밋 모드를 비활성하여 테스트 때문에 변경된 데이터가 커밋되는 것을 막고, 테스트 실행 전의 데이터베이스 상태를 저장함
     * 테스트 실행 전 데이터 베이스 상태를 저장하기 때문에 개발자는 테스트가 수행된 다음 데이터베이스의 상태를 롤백할 수 있음
     * @param extensionContext
     * @throws Exception
     */
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savePoint");
    }


}
