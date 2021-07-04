package com.cx.patterns.builder;


public class Car {
    String name;
    String gps;
    String glass;

    @Override
    public String toString() {
        return name+gps+glass;
    }

    public static class Builder{
        Car car = new Car();

        Builder setName(String name){
            car.name = name;
            return this;
        }

        Builder setGps(String gps){
            car.gps = gps;
            return this;
        }

        Builder setGlass(String glass){
            car.glass = glass;
            return this;
        }

        Car build(){
            return car;
        }
    }
}
