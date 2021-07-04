package com.cx.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class ListObservable implements Observable<Integer>{
    final List<Observer<Integer>> observers = new ArrayList<>();

    @Override
    public void attach(Observer<Integer> observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer<Integer> observer) {
        observers.remove(observer);
    }

    @Override
    public void callObserver(Integer t) {
        for (Observer<Integer> observer : observers) {
            observer.update(t);
        }
    }
}
