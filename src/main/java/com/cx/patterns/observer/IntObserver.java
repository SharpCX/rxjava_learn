package com.cx.patterns.observer;

public class IntObserver implements Observer<Integer>{
    @Override
    public void update(Integer integer) {
        System.out.println(integer);
    }
}
