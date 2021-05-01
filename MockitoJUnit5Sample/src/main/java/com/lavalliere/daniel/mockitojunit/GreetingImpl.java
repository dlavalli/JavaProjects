package com.lavalliere.daniel.mockitojunit;

public class GreetingImpl implements Greeting {

    private GreetingService service;

    @Override
    public String greet(String name) {
        return service.greet(name);
    }

    public static void main(String... args) {

    }
}
