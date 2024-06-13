package com.test.junit.ch02.assertions.test;

import com.test.junit.ch02.assertions.Job;
import com.test.junit.ch02.assertions.SUT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;


/**
 *  시스템의 성능이 충분한지 테스트한다
 *  assertTimeout, assertTimeoutPreemptively
 * */
public class AssertTimeoutTest {
    SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    @DisplayName("작업을 마칠 때까지 기다리는 assertTimeout 메서드")
    void testTimeout() throws InterruptedException {
        systemUnderTest.addJob(new Job("Job 1"));
        assertTimeout(ofMillis(500), () -> systemUnderTest.run(200));  //  assertTimeout은 객체가 작업을 마칠때까지 기다렸다가 주어진 시간을 초과하면 테스트가 얼마나 늦었는지 알려줌
    }


    @Test
    @DisplayName("시간이 지나면 작업을 중지시키는 assertTimeoutPreemptively 메서드")
    void testTimeoutPreemptively() throws InterruptedException {
        systemUnderTest.addJob(new Job("Job 1"));
        assertTimeoutPreemptively(ofMillis(500), () -> systemUnderTest.run(200)); // assertTimeoutPreemptively는 주어진 시간이 지나면 객체를 중지시킴
    }
}
