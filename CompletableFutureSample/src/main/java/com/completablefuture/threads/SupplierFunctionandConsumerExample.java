package com.completablefuture.threads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

//Timer
//Change number of threads

//OrderFetcher
//PaymentAcceptor
//InventoryChecker

public class SupplierFunctionandConsumerExample {
    private static int count=0;

    public SupplierFunctionandConsumerExample(){
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Instant start = Instant.now();
        System.out.println("Start time:" + start);

        Supplier<Integer> orderFetcher = () -> {
            sleep(200);
            int random = ThreadLocalRandom.current().nextInt(6);
            System.out.println(random);
            return  random;
        };

        Function<Integer, Boolean > inventoryChecker = orderNumber -> {
            sleep(200);
            if (orderNumber % 2 == 0)
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
        };

        // An abstract inner class implementation
        Consumer <Boolean> paymentAcceptor1= new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isinventoryPresent) {
                if (isinventoryPresent) {
                    System.out.println("payment accepted");
                } else {
                    System.out.println("payment not accepted");
                }
                Instant end = Instant.now();
                System.out.println("End time:" + end);
            }
        };


        // A lambda function implementation
        Consumer<Boolean> paymentAcceptor2 = isinventoryPresent -> {
            if (isinventoryPresent) {
                System.out.println("payment accepted");
            } else {
                System.out.println("payment not accepted");
            }
            Instant end = Instant.now();
            System.out.println("End time:" + end);
        };


        //Replace paymentAcceptor1 with paymentAcceptor2, both would give identical output
        for(int i =0; i<=9; i++) {
            CompletableFuture.supplyAsync(orderFetcher, executor)
                    .thenApply(inventoryChecker)
                    .thenAccept(paymentAcceptor1);
        }

        //System.out.println(cf1.join());
        sleep(1000);
        executor.shutdown();


    }

    private static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ignored) {
        }
    }

}