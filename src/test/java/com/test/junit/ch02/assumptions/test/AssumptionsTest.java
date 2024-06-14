package com.test.junit.ch02.assumptions.test;


import com.test.junit.ch02.assumptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * 가정문(assumption)을 활용하여 가정문이 참인 경우에만 테스트가 실행
 * */
public class AssumptionsTest {
    private static String EXPECTED_JAVA_VERSION = "8";

    private TestsEnvironment environment = new TestsEnvironment(
            new JavaSpecification(
                    System.getProperty("java.vm.specification.version")),
                    new OperationSystem(
                            System.getProperty("os.name"),
                            System.getProperty("os.arch"))
            );
    SUT systemUnderTest = new SUT();

    @BeforeEach
    void setUp() {
        //assumeTrue(environment.isWindows()); // 가정문을 사용 -> 가정문에 따라 테스트를 수행할지 여부를 정할 수 있다.
        assumeTrue(environment.isMac()); // 가정문을 사용 -> 가정문에 따라 테스트를 수행할지 여부를 정할 수 있다.
    }

    @Test
    void testNoJobToRun() {
        assumingThat(() -> environment.getJavaVersion().equals(EXPECTED_JAVA_VERSION),
                () -> assertTrue(systemUnderTest.hasJobToRun()));
    }

    @Test
    void testJobToRun() {
        assumeTrue(environment.isAmd64Architecture()); // 해당가정이 맞을 때만 하위 로직을 수행한다.

        systemUnderTest.run(new Job());
        assertTrue(systemUnderTest.hasJobToRun());
    }
}
