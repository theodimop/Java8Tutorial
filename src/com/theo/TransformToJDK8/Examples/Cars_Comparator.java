package com.theo.TransformToJDK8.Examples;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Created by Theo on 7/6/2016.
 * This exaple clarifies the functinal interfaces Comparator,Comparable and Predicate
 * Also shows how to select elements of an List with stream method
 */
public class Cars_Comparator {

    public static void main(String args[]) {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Ferrari", "Alitalia", 550));
        cars.add(new Car("Citroen", "C4", 122));
        cars.add(new Car("Mercedes", "C200", 163));
        cars.add(new Car("Mini", "Cooper S", 220));
        cars.add(new Car("Datsun", "fake", 850));

        //Sort by the Comperable Interface which has the compareTo() function
        Collections.sort(cars);
        System.out.println(cars.toString());

        //Sort by the  class Car which implements the functional interface Comparator which has the compare() function
        Collections.sort(cars,new Car());
        System.out.println(cars.toString());

        //Sort by model name, declaring it with an anonymoys class
        //JDK 7
        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getModel().compareTo(o2.getModel());
            }
        });
        //Sort by model name, with Lambda expression
        //JDK 8
        Collections.sort(cars,(o1,o2)-> o1.getModel().compareTo(o2.getModel()));
        System.out.println(cars.toString());


        //Select with strem
        //Java 8
        List<Car> carsOver150HP_AndModelNamesWithoutNumbers =
        cars.stream()
                .filter( c -> c.getHorsePower()>150)
                .filter(c -> !c.getModel().matches(".*\\d.*"))
                //Predicate without Lambda expression
                .filter(new Predicate<Car>() {
                    @Override
                    public boolean test(Car car) {
                        return !car.getBrand().equals("Datsun");
                    }
                })
                .collect(Collectors.toList());

        System.out.println(carsOver150HP_AndModelNamesWithoutNumbers.toString());


        //JDK 8 calculate total HP
        int totalHP=cars.stream()
                .map(Car::getHorsePower)
                .reduce(0,(a,b)-> a=a+b);

        System.out.println(totalHP);


        //Update HP of Alitalia
        //Java 8
        cars.forEach(car -> {
            if(car.getModel().equals("Alitalia"))
            {
                car.setHorsePower(car.getHorsePower()+100);
            }
        });

        System.out.println(cars.toString());

    }
}
class Car implements Comparator<Car>,Comparable<Car>{

    private String brand,model;
    public int horsePower;

    public Car(){/*This is an empty Constructor */}
    public Car(String brand, String model, int horsePower) {

        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }


    //This compare goes to Comparator
    //Sorting Alphabetically by model name
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getBrand().compareTo(o2.getBrand());
    }

    //This compere is from the Comperable
    //Sorting by HP
    @Override
    public int compareTo(Car o) {
        return -1*(this.getHorsePower()-o.getHorsePower());
    }

    //Override toString() for nice printing
    @Override
    public String toString(){
        return "["+this.brand+","+this.model+","+this.horsePower+"]";
    }


}