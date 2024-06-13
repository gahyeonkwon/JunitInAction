package com.test.junit.ch02.parameter;

public class WordCounter {
    public int countWords(String sentence) {
        return sentence.split(" ").length;
    }
}