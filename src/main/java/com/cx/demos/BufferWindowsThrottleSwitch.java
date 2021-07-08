package com.cx.demos;


import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class BufferWindowsThrottleSwitch {
    /**
     * buffer的可以直接用window替换
     */


    public static void main(String[] args) {
        throttleWithTimeout();
    }

    /**
     * 可以直接用window替换
     */
    private static void buffer() {
        Observable.range(1, 100).buffer(8, 8).subscribe(System.out::println);
    }

    /**
     * 可以直接用window替换
     */
    private static void timeBuffer() {
        Observable.interval(300, TimeUnit.MILLISECONDS).take(100).buffer(1, TimeUnit.SECONDS, 10)
                .flatMapSingle(e -> Observable.fromArray(e).reduce("", (total, next) -> total + next))
                .blockingSubscribe(System.out::println);
    }

    /**
     * 可以直接用window替换
     */
    private static void bufferByObserver() {
        Observable<Long> inOb = Observable.interval(1, TimeUnit.SECONDS);
        Observable.interval(300, TimeUnit.MILLISECONDS).buffer(inOb).blockingSubscribe(System.out::println);
    }


    /**
     * 可以直接用window替换
     */
    private static void throttle() {
        Observable<Long> inOb = Observable.interval(1, TimeUnit.SECONDS);
        Observable.interval(300, TimeUnit.MILLISECONDS).throttleLast(1, TimeUnit.SECONDS).blockingSubscribe(System.out::println);
        Observable.interval(300, TimeUnit.MILLISECONDS).throttleFirst(1, TimeUnit.SECONDS).blockingSubscribe(System.out::println);
    }

    public static void throttleWithTimeout(){
        Observable.interval(300, TimeUnit.MILLISECONDS).throttleWithTimeout(600, TimeUnit.MILLISECONDS).take(10).blockingSubscribe(System.out::println);
    }
}
