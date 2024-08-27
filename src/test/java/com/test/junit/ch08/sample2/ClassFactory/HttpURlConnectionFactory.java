package com.test.junit.ch08.sample2.ClassFactory;

import com.test.junit.ch08.sample2.ClassFactory.ConnectionFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  http protocol 에 대한 ConnectionFactory 인터페이스 구현 모습
 */

public class HttpURlConnectionFactory implements ConnectionFactory {
    private URL url;

    public HttpURlConnectionFactory(URL url) {
        this.url = url;
    }

    public InputStream getData() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
        return connection.getInputStream();
    }

}
