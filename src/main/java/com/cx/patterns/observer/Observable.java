package com.cx.patterns.observer;

public interface Observable<T> {
    void attach(Observer<T> observer);
    void detach(Observer<T> observer);
    void callObserver(T t);
}
