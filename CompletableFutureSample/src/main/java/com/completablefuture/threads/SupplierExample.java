package com.completablefuture.threads;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class SupplierExample {
    public SupplierExample(){

    }

    private static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Both examples implement exactly the sam thing, ie: supplying data

        // First implementation using - Anonymous Class implementation
        Supplier <Integer> orderFetcher1 = new Supplier< Integer >() {
            @Override
            public Integer get() {
                sleep (200);
                return new Random().nextInt(6);
            }
        };

        // Second implementation using - Lambda Implementation
        Supplier <Integer> orderFetcher2 = () -> {
            sleep (200);
            return new Random().nextInt(6);
        };

        //Replace orderFetcher2 with orderFetcher1 to run the Anonymous Class implementation
        CompletableFuture <Integer> cf1 = CompletableFuture.supplyAsync(orderFetcher2,executor);
        System.out.println(cf1.get());  // Not normal way of doing this, you would normally use a Consumer instead
        sleep(900);
        executor.shutdown();
    }


}
