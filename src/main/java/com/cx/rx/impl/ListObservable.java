package com.cx.rx.impl;

import com.cx.rx.Emitter;
import com.cx.rx.Observable;
import com.cx.rx.ObservableOnSubscribe;
import com.cx.rx.Observer;

import java.util.List;

public class ListObservable<T> extends Observable<T> {
    List<T> list;
    ObservableOnSubscribe observableOnSubscribe;

    public ListObservable(List<T> list){
        observableOnSubscribe = new ObservableOnSubscribe();
        this.list = list;
    }

    @Override
    public void subscribeActual(Observer<T> observer) {
        for (T t : list) {
            observer.onNext(t);
        }
        observer.onComplete();
    }
}
