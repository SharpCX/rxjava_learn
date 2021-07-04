package com.cx.rx.impl;

import com.cx.rx.Observable;
import com.cx.rx.Observer;

public class StringListObservable extends Observable<String> {
    @Override
    public void subscribeActual(Observer<String> observer) {
        String[] list = new String[]{"1","2","3","4"};
        for (String integer : list) {
            observer.onNext(integer);
        }
        observer.onComplete();
    }
}
