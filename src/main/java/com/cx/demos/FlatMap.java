package com.cx.demos;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
        System.out.println("enter-"+s);
        Random rand = new Random();
        Thread.sleep(1000*rand.nextInt(5));
        return s;
    }

    public static void main(String[] args) {
        Executor pool = Executors.newFixedThreadPool(10);

//        System.out.println("flatMap");
//        Observable.just("a", "b", "c", "d", "e", "f")
//                .flatMap(e -> Observable.just(e).map(FlatMap::callRequest).subscribeOn(Schedulers.from(pool)))
//                .subscribe(e-> System.out.println(Thread.currentThread().getName()+"-"+e));
//
//        System.out.println("concatMap");
//        Observable.just("a", "b", "c", "d", "e", "f")
//                .concatMap(e -> Observable.just(e).map(FlatMap::callRequest).subscribeOn(Schedulers.from(pool)))
//                .subscribe(e-> System.out.println(Thread.currentThread().getName()+"-"+e));


        System.out.println("concatMapEager");
        Observable.just("a", "b", "c", "d", "e", "f")
                .concatMapEager(e -> Observable.just(e).map(FlatMap::callRequest).subscribeOn(Schedulers.from(pool)))
                .subscribe(e-> System.out.println(Thread.currentThread().getName()+"-"+e));

    }
}
