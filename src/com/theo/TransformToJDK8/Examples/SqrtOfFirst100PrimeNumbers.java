package com.theo.TransformToJDK8.Examples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by dj_di_000 on 7/6/2016.
 */
public class SqrtOfFirst100PrimeNumbers {


    public static void main(String args[]){
        findSqrtOfFirst100PrimeNumbers_JDK7();
        findSqrtOfFirst100PrimeNumbers_JDK8();
    }


    /*
    * Imperative Implementation JDK 7 and lower
    * */
    private static boolean isPrime_JDK7(int number){
        boolean dividable = false;

        for (int i = 2 ; i < number;i++)
            if(number % i == 0) {
                dividable = true;
                break;
            }

        return number > 1 && !dividable;
    }
    public static void findSqrtOfFirst100PrimeNumbers_JDK7(){
        List<Double> sqrtOfFirst100PrimeNumbers = new ArrayList<>();

        int number=2;
        while( sqrtOfFirst100PrimeNumbers.size()<100)
        {
            if(isPrime_JDK7(number))
              sqrtOfFirst100PrimeNumbers.add(Math.sqrt(number));

            number++;
        }
        System.out.println(sqrtOfFirst100PrimeNumbers.toString());
    }
    /*
    * END OF Imperative Implementation JDK 7 and lower
    * */


    /*
    * Declarative Implementation JDK 8
    * */
    private static boolean isPrime_JDK8(int number){
       return number>1 && IntStream.range(2,number) // Produces stream of integers from 2 to number-1 and if each integer is not divided by any number
                 .noneMatch(e -> number % e == 0);  // it returns true because of the noneMatch
        //alternative
        //         .allMatch(e -> number % e !=0);
    }
    public static void findSqrtOfFirst100PrimeNumbers_JDK8(){

        List<Double> sqrtOfFirst100PrimeNumbers=
                Stream.iterate(1,e -> e+1) //iterate for ever... but it is lazy==effective , 1.. infinite
                  .filter(SqrtOfFirst100PrimeNumbers::isPrime_JDK8) //Every generated number is filtered and if it is primed proceeds
                  .map(Math::sqrt)//Map : do something with this value!
                  .limit(100)   // Proceed here for 100 times only!
                  .collect(toList());//Return the values to a list of Doubles

        System.out.println(sqrtOfFirst100PrimeNumbers.toString());
    }
    /*
    * END OF Declarative Implementation JDK 8
    * */
}
