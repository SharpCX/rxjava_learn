package com.cx.rx.impl;

import com.cx.rx.Emitter;
import com.cx.rx.Observable;
import com.cx.rx.ObservableOnSubscribe;
import com.cx.rx.Observer;

public class CreateObservable<T> extends Observable<T> {
    ObservableOnSubscribe observableOnSubscribe;

    public CreateObservable(ObservableOnSubscribe observableOnSubscribe){
        this.observableOnSubscribe = observableOnSubscribe;
    }

    @Override
    public void subscribeActual(Observer<T> observer) {
        observableOnSubscribe.subscribe((Emitter<T>) observer::onNext);
    }
}
