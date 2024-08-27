package com.test.junit.ch08.sample2.methodFactory;


import com.test.junit.ch08.sample2.MockHttpURLConnection;
import com.test.junit.ch08.sample2.TestableWebClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;


/**
 *  메서드 팩터리 패턴을 이용한 mock 주입
 */
public class TestWebClientMock {

    @Test
    public void testGetContentOK() throws Exception {

        // 모의 객체 생성
        MockHttpURLConnection mockHttpURLConnection = new MockHttpURLConnection();
        mockHttpURLConnection.setExpectedInputStream( new ByteArrayInputStream("It works".getBytes()));

        // createdHttpURLConnection 메서드가 모의로 만든 MockHttpURLConnection 를 반환할 수 있도록 set
        TestableWebClient client = new TestableWebClient();
        client.setHttpURLConnection(mockHttpURLConnection);

        String result = "";

        try {

            result = client.getContent(new URL("http://localhost"));

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals("It works", result);
    }
}
