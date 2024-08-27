package com.test.junit.ch08.sample2;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


    /**
     *  WebClient 에서 HttpUrlConnection 을 가져오는 부분을 따로 분리함
     */
    public class WebClient1 {
        public String getContent(URL url) {
            StringBuffer content = new StringBuffer();

            HttpURLConnection connection = null;

            try {
                connection = createHttpURLConnetion(url);

                InputStream is = connection.getInputStream();

                int count;
                while (-1 != (count = is.read())) {
                    content.append(new String(Character.toChars(count)));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return content.toString();
        }

        protected HttpURLConnection createHttpURLConnetion(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }
