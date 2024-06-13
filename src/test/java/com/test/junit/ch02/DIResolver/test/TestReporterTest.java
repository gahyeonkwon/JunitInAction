package com.test.junit.ch02.DIResolver.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.HashMap;
import java.util.Map;


/**
 * TestReporterParameterResolver를 사용하는 예제 -> TestReporter은 한 개의 추상 메서드 publishEntry와 publishEntry 메서드를 오버로딩한 디폴트 메서드 여러개를 가짐
 * TestReporter 타입의 파라미터 -> @BeforeEach, @AfterEach, @Test 애노테이션이 달린 테스트 메서드에 주입할 수 있음
 *                          -> 현재 실행되는 테스트에 추가적인 정보를 제공할 때 사용함
 * */
public class TestReporterTest {
    @Test
    void testReportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("Single value");
    }

    @Test
    void testReportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("Key", "Value");
    }

    @Test
    void testReportMultipleKeyValuePairs(TestReporter testReporter) {
        Map<String, String> values = new HashMap<>();
        values.put("user", "John");
        values.put("password", "secret");
        testReporter.publishEntry(values);
    }
}
