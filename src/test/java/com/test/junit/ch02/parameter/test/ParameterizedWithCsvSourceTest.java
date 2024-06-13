package com.test.junit.ch02.parameter.test;

import com.test.junit.ch02.parameter.WordCounter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedWithCsvSourceTest {
    private WordCounter wordCounter = new WordCounter();

    @ParameterizedTest
    @CsvSource({"2, Unit testing", "3, Junit in Action", "4, write soilid Java Cod"}) // CSV 형식의 문자열에서 구문을 분석
    void testWordsInSentence(int expected, String sentence) { // ','을 기준으로 쪼갬 첫번째 값은 expected, 두번째 값은 sentence에 할당 됨
        // countWords -> 들어온 문장을 공백을 기준으로 쪼개 배열로 반환
        assertEquals(expected, wordCounter.countWords(sentence));
    }
}
