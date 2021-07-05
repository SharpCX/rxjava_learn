package com.cx.demos;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import javax.management.relation.RelationNotFoundException;
import java.util.Random;

/**
 * 多播
 */
public class MultiCast {
    public static void main(String[] args) {
//        sequence();
//        multicast();
//        multicastWithMap1();
        multicastWithMap2();
    }


    private static void sequence() {
        Observable<Integer> ob = Observable.just(1, 2, 3);
        ob.subscribe(e -> System.out.println("1--" + e));
        ob.subscribe(e -> System.out.println("2--" + e));
    }

    private static void multicast() {
        ConnectableObservable<Integer> ob = Observable.just(1, 2, 3).publish();
        ob.subscribe(e -> System.out.println("1--" + e));
        ob.subscribe(e -> System.out.println("2--" + e));
        ob.connect();
    }

    private static void multicastWithMap1() {
        Random rand = new Random();
        ConnectableObservable<Integer> ob = Observable.just(1, 2, 3).publish();
        Observable<Integer> obmap = ob.map(e -> rand.nextInt());
        obmap.subscribe(e -> System.out.println("1--" + e));
        obmap.subscribe(e -> System.out.println("2--" + e));
        ob.connect();
    }

    private static void multicastWithMap2() {
        Random rand = new Random();
        Observable<Integer> ob = Observable.just(1, 2, 3);
        ConnectableObservable<Integer> obmap = ob.map(e -> rand.nextInt()).publish();
        obmap.subscribe(e -> System.out.println("1--" + e));
        obmap.subscribe(e -> System.out.println("2--" + e));
        obmap.connect();
    }
}
