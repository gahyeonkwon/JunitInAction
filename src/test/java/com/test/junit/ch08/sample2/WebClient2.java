package com.test.junit.ch08.sample2;


import com.test.junit.ch08.sample2.ClassFactory.ConnectionFactory;

import java.io.InputStream;

/**
 *  ConnectionFactory를 사용하여 리팩터링한 결과
 */
public class WebClient2 {

    public String getContent(ConnectionFactory connectionFactory) {

        String workingContent;

        StringBuffer content = new StringBuffer();

        try(InputStream is = connectionFactory.getData()) {
            int count;
            while(-1 != (count = is.read())) {
                content.append(new String(Character.toChars(count)));
            }

            workingContent = content.toString();

        } catch (Exception e) {
            workingContent = null;
        }

        return workingContent;
    }
}
