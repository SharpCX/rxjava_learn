package com.cx.rx;

import java.util.Arrays;

public class run {
    public static void main(String[] args) {
//        new IntListObservable().subscribe(new Observer<Integer>() {
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println(integer);
//            }
//        });
//
//        new MapObserver(new IntListObservable()).subscribe(System.out::println);

        //new ListObservable<String>(Arrays.asList("1", "2")).subscribe(System.out::println);


//        new ListObservable<String>(Arrays.asList("1","2")).map(a->a+"2").subscribe(System.out::println);

//        Observable.fromList(Arrays.asList("1", "2")).map(a->a+"2").subscribe(System.out::println);
        Observable.fromList(Arrays.asList("1", "2")).map(a->a+"2").subscribe(System.out::println);
    }
}
