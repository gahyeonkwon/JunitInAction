package com.test.junit.ch02.assertions.test;


import com.test.junit.ch02.assertions.SUT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertAllTest {

    @Test
    @DisplayName("기본적으로 테스트 대상 시스템은 검증하지 않는다")
    void testSystemNotVerified() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템"); // verfied default -> false

        assertAll("테스트 대상 시스템을 검증하지 않았는지 확인",
                () -> assertEquals("테스트 대상 시스템",
                        systemUnderTest.getSystemName()),
                () -> assertFalse(systemUnderTest.isVerified()));

    }


    @Test
    @DisplayName("테스트 대상 시스템을 검증한다")
    void testSystemUnderVerification() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        systemUnderTest.verify();

        assertAll("테스트 대상 시스템을 검증했는지 확인",
                () -> assertEquals("테스트 대상 시스템",
                        systemUnderTest.getSystemName()),
                () -> assertTrue(systemUnderTest.isVerified()));

    }

    @Test
    @DisplayName("테스트 대상 시스템을 검증한다 -> 단언문에서 람다식을 파라미터로 사용하기")
    void testSystemUnderVerification_2() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        systemUnderTest.verify();
        assertTrue(systemUnderTest.isVerified(), () -> "테스트 대상 시스템을 검증했는지 확인"); // 람다식을 파라미터로 사용할 경우 지연 전달 덕분에 성능이 향상 됨
    }

    @Test
    @DisplayName("테스트 대상 시스템을 검증하지 않았다")
    void testSystemNotUnderVerification() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        //systemUnderTest.verify();
        assertFalse(systemUnderTest.isVerified(), () -> "테스트 대상 시스템을 검증하지 않았는지 확인");
    }

    @Test
    @DisplayName("현재 테스트 대상 시스템은 작업이 없다")
    void testNoJob() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");
        assertNull(systemUnderTest.getCurrentJob(), () -> "테스트 대상 시스템은 현재 작업이 없는지 확인");
    }
}
