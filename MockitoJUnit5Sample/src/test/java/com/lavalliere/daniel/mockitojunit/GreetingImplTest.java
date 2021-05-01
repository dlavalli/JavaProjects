package com.lavalliere.daniel.mockitojunit;

/*
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GreetingImplTest {

    // Need to be mocked since greeting depends on it
    @Mock
    private GreetingService service;

    // To Inject the mock object here
    @InjectMocks
    private GreetingImpl greeting;

    @Test
    public void greetShouldReturnAValidOutput() {
        when(service.greet("JUnit")).thenReturn("Hello JUnit");
        String result = greeting.greet("JUnit");
        assertNotNull(result);
        assertEquals("Hello JUnit", result);
    }

    @Test
    public void greetShouldThrowAndException_For_NameIsNull() {
        doThrow(IllegalArgumentException.class).when(service).greet(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            greeting.greet(null);
        });
    }

    @Test
    public void greetShouldThrowAndException_For_NameIsBlank() {
        doThrow(IllegalArgumentException.class).when(service).greet("");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            greeting.greet("");
        });
    }
}