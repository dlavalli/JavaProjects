package com.lavalliere.daniel.mockitojunit.scrapbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ATest {

    @Mock
    B b;

    private A a;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        a = new A(b);
    }

    @Test
    public void uses_void_method_should_call_the_void_method() throws  Exception{
        // Nothing special to do since void method calls nothing and returns nothing
        doNothing().when(b).voidMethod();
        assertSame(1, a.usesVoidMethod());
        verify(b).voidMethod();
    }

    @Test
    public void test_consecutive_calls() throws  Exception{
        // Nothing special to do since void method calls nothing and returns nothing
        doNothing().doThrow(Exception.class).when(b).voidMethod();
        Assertions.assertThrows(RuntimeException.class, () -> {
            a.usesVoidMethod();
            verify(b).voidMethod();
            a.usesVoidMethod();
        });

    }

}
