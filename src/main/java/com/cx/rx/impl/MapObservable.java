package com.cx.rx.impl;

import com.cx.rx.Emitter;
import com.cx.rx.Observable;
import com.cx.rx.Observer;

import java.util.function.Function;

public class MapObservable<T, R> extends Observable<R> {
    Observable<T> in;
    Function<T, R> mapper;

    public MapObservable(Observable<T> in, Function<T, R> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Override
    public void subscribeActual(Observer<R> observer) {
        this.in.subscribe(new MapObserver<T, R>(observer, mapper));
    }

    private static class MapObserver<T, R> implements Observer<T> {
        Observer<R> distObserver;
        Function<T, R> mapper;

        public MapObserver(Observer<R> distObserver, Function<T, R> mapper){
            this.distObserver = distObserver;
            this.mapper = mapper;
        }
        @Override
        public void onNext(T r) {
            this.distObserver.onNext(mapper.apply(r));
        }
    }
}
