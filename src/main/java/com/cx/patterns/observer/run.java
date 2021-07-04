package com.cx.patterns.observer;

public class run {
    public static void main(String[] args) {
        ListObservable listObservable = new ListObservable();
        listObservable.attach(new IntObserver());
        listObservable.attach(new IntObserver());
        listObservable.attach(new IntObserver());
        listObservable.callObserver(1);
        listObservable.callObserver(2);
        listObservable.callObserver(3);
    }
}
