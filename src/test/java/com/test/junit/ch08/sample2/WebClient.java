package com.test.junit.ch08.sample2;

import com.test.junit.ch02.DIResolver.Calculator;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {

    /**
     *  테스트 대상 리팩터링전 메서드
     * @param url
     * @return
     */
    public String getContent(URL url) {
        StringBuffer content = new StringBuffer();

        try {

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);

            InputStream is = connection.getInputStream();
            int count;
            while(-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }

        } catch (IOException e) {
            return null;
        }
        return content.toString();

    }
}
