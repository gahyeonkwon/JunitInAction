package com.test.junit.ch02.parameter.test;

import com.test.junit.ch02.parameter.WordCounter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * 하나의 테스트를 다양한 파라미터를 가지고 여러 번 실행하게 해준다. -> 다양한 입력을 두고 테스트를 실행할 수 있다.
 *
 * */
public class ParameterizedWithValueSourceTest {
    private WordCounter wordCounter = new WordCounter();
    @ParameterizedTest
    @ValueSource(strings = {"Check three parameters", "Junit in Action"}) // @ValueSource를 사용하여 테스트 메서드의 파라미터로 전달할 값을 특정한다. 문자열의 수만큼 실행 된다.
    void testWordsInSentence(String sentence) {
        assertEquals(3, wordCounter.countWords(sentence)); // 총 두번 실행 됨
    }
}


