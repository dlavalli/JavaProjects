package com.lavalliere.daniel.mockitojunit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreetingImplTest extends Object {

    private Greeting greeting;

    @Before
    public void setup() {
        greeting = new GreetingImpl();
    }

    @After
    public void cleanup() {
        greeting = null;
    }

    @Test
    public void greetShouldReturnAValidOutput() {
        String result = greeting.greet("JUnit");
        assertNotNull(result);
        assertEquals("Hello JUnit", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowAndException_For_NameIsNull() {
        greeting.greet(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowAndException_For_NameIsBlank() {
        greeting.greet("");
    }
}