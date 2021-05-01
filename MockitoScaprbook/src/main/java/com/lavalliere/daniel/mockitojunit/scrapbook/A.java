package com.lavalliere.daniel.mockitojunit.scrapbook;

public class A {
    private B b;

    public A(B b) {
        this.b = b;
    }

    public int usesVoidMethod() {
        try {
            b.voidMethod();
        } catch (Exception e) {
            new RuntimeException(e);
        }
        return 1;
    }
}
