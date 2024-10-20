package com.test.junit.ch14.extension;

import com.test.junit.ch14.db.ConnectionManager;
import com.test.junit.ch14.db.PassengerDao;
import com.test.junit.ch14.db.PassengerDaoImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;


/**
 *  PassengerTest의 클래스의 생성자가 PassengerDao 타입의 파라미터를 받기 때문에, 해당 파라미터를 리졸브하는 ParameterResolve 생성
 *
 *  ParameterResolver 인터페이스를 구현
 *  - 해당 인터페이스는 테스트가 필요로하는 파라미터를 리졸브할 때 사용함
 *
 */
public class DataAccessObjectParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return  parameterContext.getParameter().getType().equals(PassengerDao.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new PassengerDaoImpl(ConnectionManager.getConnection());
    }
}
