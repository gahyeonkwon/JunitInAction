package com.test.junit.ch02.DIResolver.test;


import com.test.junit.ch02.DIResolver.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestReporter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * RepetitionInfoParameterResolver -> @RepeatedTest, @BeforeEach, @AfterEach 애노테이션이 달린 메서드의 파라미터가 RepetitionInfo 타입일 때 RepetitionInfo 인스턴스를 리졸브하는 역할을 함
 *
 *
 * */
public class RepeatedTestsTest {
    private static Set<Integer> integerSet = new HashSet<>();
    private static List<Integer> integerList = new ArrayList<>();



    /**
     * {displayName} : @RepeatedTest 애노테이션이 붙은 메서드의 디스플레이 네임
     * {currentRepetition} : 현재 반복 인덱스
     * {totalRepetitions} : 총 반복 횟수
     * */
    @RepeatedTest(value = 5, name = "{displayName} - repetition {currentRepetition}/{totalRepetitions}")
    @DisplayName("Test add operation")
    void addNumber() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.add(1,1), "1 + 1 should equal 2");
    }


    @RepeatedTest(value = 5, name= "the list contains {currentRepetition} elements(s), the set contains 1 element" )
    void testAddingToCollections(TestReporter testReporter, RepetitionInfo repetitionInfo) {
        integerSet.add(1);
        integerList.add(repetitionInfo.getCurrentRepetition());

        testReporter.publishEntry("Repetition number", String.valueOf(repetitionInfo.getCurrentRepetition()));

        assertEquals(1, integerSet.size());
        assertEquals(repetitionInfo.getCurrentRepetition(), integerList.size());
    }
}
