package com.test.junit.ch08.sample2.ClassFactory;

import com.test.junit.ch08.sample2.ClassFactory.ConnectionFactory;

import java.io.InputStream;


public class MockConnectionFactory implements ConnectionFactory {
    private InputStream inputStream;

    public void setData(InputStream stream) {
        this.inputStream = stream;
    }

    public InputStream getData() {
        return inputStream;
    }
}
