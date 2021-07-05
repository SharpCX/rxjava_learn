package com.cx.demos;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscription;

/**
 * 如何理解single和trampoline
 */
public class SingleTrampoline {
    //Schedulers.single() is new in RxJava 2. This scheduler is backed by a single thread executing tasks sequentially in the order requested.
    //Schedulers.trampoline() executes tasks in a FIFO (First In, First Out) manner by one of the participating worker threads. It’s often used when implementing recursion to avoid growing the call stack.

    public static void main(String[] args) {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())// 注掉这一行即可区分
                .observeOn(Schedulers.trampoline())
                .map(e-> {System.out.println("map:::"+e+Thread.currentThread().getName());return e;})
                .blockingSubscribe(new FlowableSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("subscribe"+Thread.currentThread().getName()+integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
