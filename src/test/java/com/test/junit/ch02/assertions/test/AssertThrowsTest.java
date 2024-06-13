package com.test.junit.ch02.assertions.test;


import com.test.junit.ch02.assertions.NoJobException;
import com.test.junit.ch02.assertions.SUT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *  assertThrows 예제
 *  assertThrows -> Junit4 에서 사용하던 ExpectedExcetpiopn rule 을 대체
 *               -> 예외 발생시 Throwable 객체를 반환한다.
 *
 * */

public class AssertThrowsTest {
    SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    @DisplayName("예외가 발생하는지 검증한다")
    void testExpectedException() {
        // Job을 할당하지 않고 run 수행
        assertThrows(NoJobException.class, systemUnderTest::run);
    }

    @Test
    @DisplayName("예외가 발생하고 예외에 대한 참조가 유지되는지 검증한다")
    void testCatchException() {
        Throwable throwable = assertThrows(NoJobException.class,
                () -> systemUnderTest.run(1000));

        System.out.println(throwable.getMessage());
        assertEquals("No jobs on the execution list!", throwable.getMessage()); // 예외에 대한 참조가 유지 됨
    }


}
