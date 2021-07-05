package com.cx.demos;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.schedulers.Timed;
import javafx.util.Pair;

import java.util.*;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FlatMap {
    private static String callRequest(String s) throws InterruptedException {
        System.out.println("enter-" + s);
        Random rand = new Random();
        Thread.sleep(1000 * rand.nextInt(5));
        return s;
    }

    public static void main(String[] args) {
        Executor pool = Executors.newFixedThreadPool(10);

//        Observable.just(2, 3, 4, 5)
//                .flatMap(i -> Observable.interval(i, TimeUnit.SECONDS)
//                .map(i2 -> i + "s interval:" + ((i2*i)+i) + " seconds elapsed in thread"+Thread.currentThread().getName()))
//                .blockingSubscribe(System.out::println);


        Observable<Long> ob1 = Observable.interval(2, TimeUnit.SECONDS).doOnSubscribe(e-> System.out.println("sub 1"));
        Observable<Long> ob2 = Observable.interval(1, TimeUnit.SECONDS).doOnSubscribe(e-> System.out.println("sub 2"));

        Observable.merge(ob1, ob2).blockingSubscribe(e-> System.out.println(e));


//        Observable.concat(ob1, ob2).blockingSubscribe(e-> System.out.println(e));

//        System.out.println("flatMap");
//        Observable.just("a", "b", "c", "d", "e", "f")
//                .flatMap(e -> Observable.just(e).map(FlatMap::callRequest).subscribeOn(Schedulers.from(pool)))
//                .subscribe(e-> System.out.println(Thread.currentThread().getName()+"-"+e));

//        System.out.println("concatMap");
//        Observable.just("a", "b", "c", "d", "e", "f")
//                .concatMap(e -> Observable.just(e).map(FlatMap::callRequest).subscribeOn(Schedulers.from(pool)))
//                .subscribe(e-> System.out.println(Thread.currentThread().getName()+"-"+e));


//        System.out.println("concatMapEager");
//        Observable.just("a", "b", "c", "d", "e", "f")
//                .concatMapEager(e -> Observable.just(e).map(FlatMap::callRequest).subscribeOn(Schedulers.from(pool)))
//                .subscribe(e-> System.out.println(Thread.currentThread().getName()+"-"+e));

    }
}
