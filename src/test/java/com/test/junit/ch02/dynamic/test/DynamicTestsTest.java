package com.test.junit.ch02.dynamic.test;

import com.test.junit.ch02.dynamic.PositiveNumberPredicate;
import org.junit.jupiter.api.*;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicTestsTest {

    private final PositiveNumberPredicate predicate = new PositiveNumberPredicate();

    @BeforeAll
    static void setUpClass() {
        System.out.println("@BeforeAll method");
    }

    @AfterAll
    static void tearDownClass() {
        System.out.println("@AfterAll method");
    }

    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach method");
    }


    /**
     *
     * DynamicTest 인터페이스는 디스플레이 네임과 Excutable로 이루어져 있고 런타임에 생성되는 테스트다.
     * Excutable은 자바8에서 등장한 함수형 인터페이스이므로 동적 테스트는 람다식이나 메서드 참조 방식으로 구현할 수 있다.
     *
     * */
    @TestFactory
    Iterator<DynamicTest> positiveNumberPredicateTestCase() {

        // predicate.check -> 입력값이 0 이상인지 반환
         return asList(
                 dynamicTest("negative number", () -> assertFalse(predicate.check(-1))),
                 dynamicTest("zero", () -> assertFalse(predicate.check(0))),
                 dynamicTest("positive number", () -> assertTrue(predicate.check(1)))
         ).iterator();
    }

}
