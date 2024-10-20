package com.test.junit.ch14.extension;

import com.test.junit.ch14.PassengerExistException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

public class LogPassengerExistsExceptionExtension implements TestExecutionExceptionHandler {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if(throwable instanceof PassengerExistException) {
            logger.severe("Passenger exists:" + throwable.getMessage());
            return;
        }
        throw throwable;
    }
}
