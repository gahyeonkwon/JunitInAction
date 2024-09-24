package com.test.junit.ch08.sample03;

import com.test.junit.ch08.sample2.ClassFactory.ConnectionFactory;
import com.test.junit.ch08.sample2.WebClient2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.easymock.EasyMock.*;

public class TestWebClientEasyMock {
    private ConnectionFactory factory;
    private InputStream stream;


    @BeforeEach
    public void setUp() {
        factory = createMock("factory", ConnectionFactory.class);
        stream = createMock("stream", InputStream.class);
    }

    @Test
    public void testGetContentOK() throws Exception{
        expect(factory.getData()).andReturn(stream);
        expect(stream.read()).andReturn(Integer.valueOf((byte) 'W'));
        expect(stream.read()).andReturn(Integer.valueOf((byte) 'o'));
        expect(stream.read()).andReturn(Integer.valueOf((byte) 'r'));
        expect(stream.read()).andReturn(Integer.valueOf((byte) 'k'));
        expect(stream.read()).andReturn(Integer.valueOf((byte) 'd'));
        expect(stream.read()).andReturn(Integer.valueOf((byte) '!'));
        expect(stream.read()).andReturn(-1);

        stream.close();

        replay(factory);
        replay(stream);

        WebClient2 client2 = new WebClient2();
        String workingContent = client2.getContent(factory);

        Assertions.assertEquals("Workd!", workingContent);

    }


    @Test
    public void testGetContentCannotCloseInputStream() throws Exception {
        expect(factory.getData()).andReturn(stream);
        expect(stream.read()).andReturn(-1);
        stream.close();
        expectLastCall().andThrow(new IOException("cannot close"));

        replay(factory);
        replay(stream);

        WebClient2 client = new WebClient2();
        String workingContent = client.getContent(factory);

        Assertions.assertNull(workingContent);
    }
}
