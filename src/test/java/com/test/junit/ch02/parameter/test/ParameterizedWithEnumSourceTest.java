package com.test.junit.ch02.parameter.test;

import com.test.junit.ch02.parameter.WordCounter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

public class ParameterizedWithEnumSourceTest {
    private WordCounter wordCounter = new WordCounter();


    enum Sentences {
        JUNIT_IN_ACTION("JUnit in Action"),
        SOME_PARAMETERS("Check some parameters"),
        THREE_PARAMETERS("Check three parameters");

        private final String sentence;

        Sentences(String sentence) {
            this.sentence = sentence;
        }

        public String value() {
            return sentence;
        }
    }

    @ParameterizedTest
    @EnumSource(Sentences.class)
    void testWordsInSentence(Sentences sentences) {
        // countWords -> 들어온 문장을 공백을 기준으로 쪼개 배열로 반환
        assertEquals(3, wordCounter.countWords(sentences.value())); // 총 세번 실행 됨
    }

    @ParameterizedTest
    @EnumSource(value = Sentences.class, names = {"JUNIT_IN_ACTION", "THREE_PARAMETERS"})
    void testSelectedWordsInSentence(Sentences sentences) {
        assertEquals(3, wordCounter.countWords(sentences.value())); // 총 두번 실행 됨
    }

    @ParameterizedTest
    @EnumSource(value = Sentences.class, mode = EXCLUDE, names = {"THREE_PARAMETERS"}) // THREE_PARAMETERS를 제외함
    void testExcludeWordsInSentence(Sentences sentences) {
        assertEquals(3, wordCounter.countWords(sentences.value())); // 총 한번 실행 됨
    }


}
