package com.test.junit.ch14.domain;

public class Passenger {

    private String identifier;
    private String name;

    public Passenger(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
