package com.completablefuture.threads;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

//Timer
//Change number of threads

//OrderFetcher
//PaymentAcceptor
//InventoryChecker

public class SupplierandFunctionExample {

    public SupplierandFunctionExample(){
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Supplier<Integer> orderFetcher = () -> {
            sleep(200);
            return new Random().nextInt(6);
        };

        Function <Integer, Boolean> inventoryChecker1= new Function <Integer,Boolean> () {
            @Override
            public Boolean apply(Integer orderNumber) {
                sleep(200);
                if(orderNumber%2 ==0)
                    return  Boolean.TRUE;
                else
                    return  Boolean.FALSE;

            }
        };

        Function <Integer, Boolean> inventoryChecker2 = orderNumber -> {
            sleep(200);
            if(orderNumber%2 ==0)
                return  Boolean.TRUE;
            else
                return  Boolean.FALSE;
        };

        //Replace inventoryChecker1 with inventoryChecker2, both would give identical output, either true or false depending on random number
        CompletableFuture cf1 = CompletableFuture.supplyAsync(orderFetcher,executor)
                                    .thenApply(inventoryChecker1);

        //Inline lambda implementation
        /*CompletableFuture cf2 = CompletableFuture.supplyAsync(orderFetcher,executor).thenApply((orderNumber) -> {
            sleep(200);
            if(orderNumber%2 ==0)
                return  Boolean.TRUE;
            else
                return  Boolean.FALSE;
        };*/
        System.out.println(cf1.get());
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
