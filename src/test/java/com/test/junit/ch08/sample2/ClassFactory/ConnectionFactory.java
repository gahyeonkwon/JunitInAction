package com.test.junit.ch08.sample2.ClassFactory;

import org.xmlunit.builder.Input;

import java.io.InputStream;


/**
 *  ConnectionFactory를 구현한 클래스의 연결 종류에 상관없이 적절한 InputStream 을 반환
 */
public interface ConnectionFactory {
    InputStream getData() throws Exception;
}
