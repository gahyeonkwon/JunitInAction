package com.test.junit.ch08.sample05;


import com.test.junit.ch08.sample2.ClassFactory.ConnectionFactory;
import com.test.junit.ch08.sample2.WebClient2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestWebClientMockito {
    @Mock
    private ConnectionFactory connectionFactory;

    @Mock
    private InputStream inputStream;

    @Test
    public void testGetContent() throws  Exception {
        when(connectionFactory.getData()).thenReturn(inputStream);
        when(inputStream.read())
                .thenReturn((int)'W')
                .thenReturn((int)'o')
                .thenReturn((int)'r')
                .thenReturn((int)'k')
                .thenReturn((int)'s')
                .thenReturn((int)'!')
                .thenReturn(-1);

        WebClient2 client2 = new WebClient2();

        String workingContent = client2.getContent(connectionFactory);

        assertEquals("Works!", workingContent);
    }

    @Test
    public void testGetContentCannotCloseInputStream() throws  Exception {
        when(connectionFactory.getData()).thenReturn(inputStream);
        when(inputStream.read()).thenReturn(-1);
        doThrow(new IOException("cannot close")).when(inputStream).close();

        WebClient2 webClient2 = new WebClient2();

        String workingContent = webClient2.getContent(connectionFactory);

        assertNull(workingContent);
    }
}
