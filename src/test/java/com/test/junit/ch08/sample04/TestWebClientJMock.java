package com.test.junit.ch08.sample04;

import com.test.junit.ch08.sample2.ClassFactory.ConnectionFactory;
import com.test.junit.ch08.sample2.WebClient2;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.jmock.lib.JavaReflectionImposteriser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestWebClientJMock {

    @RegisterExtension
    Mockery context = new JUnit5Mockery() {
        {
            // 인터페이스가 아니라 클래스에 대한 모의 객체를 생성해야 할 경우
            setImposteriser(JavaReflectionImposteriser.INSTANCE);
        }
    };

    @Test
    public void testGetContentOK() throws Exception {
        ConnectionFactory factory = context.mock(ConnectionFactory.class);
        InputStream mockStream = context.mock(InputStream.class);

        context.checking(new Expectations() {
            {
                //모의 객체가 수행하기를 원하는 동작을 기대
                oneOf(factory).getData();
                //반환하고자 하는 값을 설정한다
                will(returnValue(mockStream));

                atLeast(1).of(mockStream).read();

                // 모의객체가 반환하고자 하는 값을 연속적으로 선언
                will(onConsecutiveCalls(
                        returnValue(Integer.valueOf( (byte)'w')),
                        returnValue(Integer.valueOf( (byte)'o')),
                        returnValue(Integer.valueOf( (byte)'r')),
                        returnValue(Integer.valueOf( (byte)'k')),
                        returnValue(Integer.valueOf( (byte)'s')),
                        returnValue(Integer.valueOf( (byte)'!')),
                        returnValue(-1)));

                oneOf(mockStream).close();

            }
        });

        WebClient2 client2 = new WebClient2();
        String workingContent = client2.getContent(factory);

        assertEquals("Works!", workingContent);
    }

    @Test
    public void testGetContentCannotCloseInputStream() throws Exception {
        ConnectionFactory factory = context.mock(ConnectionFactory.class);
        InputStream mockStream = context.mock(InputStream.class);

        context.checking(new Expectations() {
            {
                oneOf(factory).getData();
                will(returnValue(mockStream));
                oneOf(mockStream).read();
                will(returnValue(-1));
                //close 메서드가 호출될거라는 기대 선언
                oneOf(mockStream).close();
                //예외 조건 테스트를 위한 throwException
                will(throwException(new IOException("cannot close")));
            }
        });

        WebClient2 client2 = new WebClient2();

        String workingContent = client2.getContent(factory);

        assertNull(workingContent);
    }
}
