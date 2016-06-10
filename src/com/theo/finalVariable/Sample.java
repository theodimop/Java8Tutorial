package com.theo.finalVariable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.*;
/**
 * Created by dj_di_000 on 7/6/2016.
 */
public class Sample  {

    public static void main(String args[]) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        IntStream.range(0,10)
                 .forEach(i->
                         executorService.submit(()->
                                System.out.println("Running task "+i)));

        /*
        for (int i=0;i<10;i++){
            int index= i;
            executorService.submit(()->
                    System.out.printf("Running task %d%n", index)
            );

        }*/

        System.out.println("Tasks started");
        executorService.shutdown();
    }
}
