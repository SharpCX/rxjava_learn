package com.cx.patterns.builder;

public class run {
    public static void main(String[] args) {
        System.out.println(new Car.Builder().setGlass("1").setGps("2").setName("3").build().toString());
    }
}
