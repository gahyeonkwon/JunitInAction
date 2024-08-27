package com.test.junit.ch08.sample2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  createHttpConnection 메서드 재정의를 위한 클래스
 */
public class TestableWebClient extends WebClient1{
    private HttpURLConnection connection;

    public void setHttpURLConnection(HttpURLConnection connection) {
        this.connection = connection;
    }

    public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
        return  this.connection;
    }

}
