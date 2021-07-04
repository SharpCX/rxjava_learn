package com.cx.demos;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Hello {
    public static void main(String[] args) {
//        Observable.just("hello").subscribe(System.out::println).dispose();

//        Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onNext(4);
//                emitter.onComplete();
//            }
//        }).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Throwable {
//                System.out.println(o);
//            }
//        });

//        Flowable.create(new FlowableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
//                        System.out.println("current requested: " + emitter.requested());
//                        System.out.println("emit" + 1);
//                        emitter.onNext(1);
//                        System.out.println("emit" + 2);
//                        emitter.onNext(2);
//                        System.out.println("emit" + 3);
//                        emitter.onNext(3);
//                        emitter.onComplete();
//                    }
//                }, BackpressureStrategy.ERROR)
//                .subscribe(new Subscriber<Integer>() {
//                    Subscription subscription;
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        System.out.println("onSubscribe");
//                        subscription = s;
//                        subscription.request(1);
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("onNext: " + integer);
//                        subscription.request(2);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        System.out.println("onError: "+ t);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("onComplete");
//                    }
//                });
//    }

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                System.out.println("First requested = " + emitter.requested());
                boolean flag;
                for (int i = 0; ; i++) {
                    flag = false;
                    while (emitter.requested() == 0) {
                        if (!flag) {
                            System.out.println("Oh no! I can't emit value!");
                            flag = true;
                        }
                    }
                    emitter.onNext(i);
                    System.out.println("emit " + i + " , requested = " + emitter.requested());
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("onSubscribe");
                        subscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }
}
