package com.cx.demos;

import io.reactivex.rxjava3.core.Observable;

public class Map {
    public static void main(String[] args) {
        Observable.just("hello").map(e->e+" world").subscribe(System.out::println).dispose();
    }
}
