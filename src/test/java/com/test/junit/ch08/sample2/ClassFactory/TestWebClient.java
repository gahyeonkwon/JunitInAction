package com.test.junit.ch08.sample2.ClassFactory;


import com.test.junit.ch08.sample2.ClassFactory.MockConnectionFactory;
import com.test.junit.ch08.sample2.WebClient2;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class TestWebClient {

    /**
     *  url 이 주어졌을 때 웹 콘텐츠를 반환하는 하는 단위 테스트 작성
     */
    @Test
    public void testGetContentOK() throws Exception {
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
        mockConnectionFactory.setData(new ByteArrayInputStream("It works".getBytes()));

        WebClient2 webClient2 = new WebClient2();

        // 해당 단위 테스트는 connection 이나 stream 을 읽어오는 것에 관계 없이 가져온 리소스에 대해서만 검증할 수 있게 바뀌었다
        String workingContent = webClient2.getContent(mockConnectionFactory);
        assertEquals("It works", workingContent);
    }
}
