package com.cx.demos;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 Your summary, and the linked question are both correct, I think the terminology may be confusing you. I propose you think of hot and cold observables as active and passive observables (respectively).

 That is, an active (hot) observable will be emitting items whether someone has subscribed or not. The canonical example, again, button click events happen whether someone is listening to them or not. This distinction is important because, if, for example, I click a button and then subscribe to button clicks (in that order), I will not see the button click that has already happened.

 A passive (cold) observable will wait until a subscriber exists before emitting items. Imagine a button where you cannot click on it until someone is listening to the events—this would ensure that you always see each and every click event.
 */
public class HotVsCold {
    public static void main(String[] args) throws InterruptedException {
//        cold();
//        hot();
        ConnectableObservable<Long> ob = Observable.interval(1000, TimeUnit.MILLISECONDS).publish();
        ob.connect();
        Thread.sleep(2000);
        ob.subscribe(e-> System.out.println("1:"+e));
        ob.subscribe(e-> System.out.println("2:"+e));
        Thread.sleep(10000);
    }

    private static void cold(){
        List<Integer> data = new ArrayList<>(Arrays.asList(1,2,3,4));
        Observable<Integer> ob = Observable.fromIterable(data);
        ob.subscribe(System.out::println);
        data.add(5);
        ob.subscribe(System.out::println);
    }

    private static void hot(){
        ReplaySubject<Integer> sub = ReplaySubject.create();
        sub.subscribe(System.out::println);
        sub.onNext(1);
        sub.onNext(2);
        sub.onNext(3);
        sub.onNext(4);
        sub.onComplete();
    }
}
