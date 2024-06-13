package com.test.junit.ch02.assumptions;

public class TestsEnvironment {
    private JavaSpecification javaSpecification;
    private OperationSystem operationSystem;

    public TestsEnvironment(JavaSpecification javaSpecification, OperationSystem operationSystem) {
        System.out.println(" operationSystem => " + operationSystem.getName());
        System.out.println(" operationSystem => " + operationSystem.getArchitecture());

        this.javaSpecification = javaSpecification;
        this.operationSystem = operationSystem;
    }

    public boolean isWindows() {
        return operationSystem.getName().contains("Windows");
    }

    public boolean isMac() {
        return operationSystem.getName().contains("Mac");
    }

    public boolean isAmd64Architecture() {
        return operationSystem.getArchitecture().equals("amd64");
    }

    public String getJavaVersion() {
        return javaSpecification.getVersion();
    }

}