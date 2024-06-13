package com.test.junit.ch02.DIResolver.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * TestInfoParameterResolver를 사용하는 예제 -> 테스트 클래스 생성자나 테스트 메서드에서 [TestInfo] 객체를 파라미터로 사용할 수 있음.
 * TestInfo -> @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll 애노테이션이 달린 메서드에서 TestInfo 객체를 파라미터로 사용할 수 있음.
 *          -> TestInfo객체는 현재의 컨테이너 또는 테스트에 대응함, 생성자나 메서드에서 테스트에 관한 정보를 제공하는 데 사용한다.
 * */
public class TestInfoTest {

    TestInfoTest(TestInfo testInfo){

        // displayName이 TestInfoTest인지 검증함 -> 클래스의 이름 확인
        assertEquals("TestInfoTest", testInfo.getDisplayName()); // displayName의 기본 값은 메서드명임.
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("display name of the method") || displayName.equals("testGetNameOfTheMethod(TestInfo)"));
    }

    @Test
    void testGetNameOfTheMethod(TestInfo testInfo) {
        assertEquals("testGetNameOfTheMethod(TestInfo)", testInfo.getDisplayName());
    }

    @Test
    @DisplayName("display name of the method")
    void testGetNameOfTheMethodWithDisplayNameAnnotation(TestInfo testInfo) {
        assertEquals("display name of the method", testInfo.getDisplayName());
    }

}
