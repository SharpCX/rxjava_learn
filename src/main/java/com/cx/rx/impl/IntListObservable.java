package com.cx.rx.impl;

import com.cx.rx.Observable;
import com.cx.rx.Observer;

public class IntListObservable extends Observable<Integer> {
    @Override
    public void subscribeActual(Observer<Integer> observer) {
        Integer[] list = new Integer[]{1,2,3,4};
        for (Integer integer : list) {
            observer.onNext(integer);
        }
        observer.onComplete();
    }
}
