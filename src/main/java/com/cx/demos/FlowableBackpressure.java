package com.cx.demos;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class FlowableBackpressure {
    public static void main(String[] args) throws InterruptedException {
        flowableOnBackpressure();
    }

    private static void flowableRaw() throws InterruptedException {
        Flowable.create(emitter -> {
            for (int i = 0; i < 1000; i++) {
                emitter.onNext(i);
                System.out.println("request:" + emitter.requested());
            }
            emitter.onComplete();
        }, BackpressureStrategy.ERROR)
                .doOnNext(e -> System.out.println("const:" + e))
                .subscribe(e -> {
                    System.out.println(e);
                    Thread.sleep(1000);
                });
        Thread.sleep(100000l);
    }

    private static void flowable() throws InterruptedException {
        Flowable.range(1, 1000).doOnNext(e -> System.out.println("const:" + e)).observeOn(Schedulers.io()).subscribe(e -> {
            Thread.sleep(100);
            System.out.println("get" + e);
        });
        Thread.sleep(100000l);
    }

    private static void flowableError() throws InterruptedException {
        Flowable.create(emitter -> {
            for (int i = 0; i < 1000; i++) {
                emitter.onNext(i);
                System.out.println("request:" + emitter.requested());
            }
            emitter.onComplete();
        }, BackpressureStrategy.ERROR)
                .doOnNext(e -> System.out.println("const:" + e))
                .subscribe(e -> {
                    System.out.println(e);
                    Thread.sleep(1000);
                });
        Thread.sleep(100000l);
    }

    private static void flowableError2() throws InterruptedException {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(final FlowableEmitter<Integer> emitter) throws Exception {
                        for (int i = 0; i < 1000; i++) {
                            emitter.onNext(1);
                        }

                        emitter.onComplete();

                    }
                }, BackpressureStrategy.BUFFER)
                .doOnNext(e-> System.out.println(e))
                .subscribe(new Subscriber<Integer>() {
                    Subscription mSubscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(2);   //request 2
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("get"+integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private static void flowableOnBackpressure() throws InterruptedException {
        Flowable.interval(1, TimeUnit.MILLISECONDS)
//                .onBackpressureDrop()
                .onBackpressureLatest() // 比drop多保留一个
                .doOnNext(e-> System.out.println(e))
                .blockingSubscribe(new Subscriber<Long>() {
                    Subscription mSubscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(10);   //request 2
                    }

                    @Override
                    public void onNext(Long integer) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"get==="+integer);
                        if((integer+1)%10==0){
                            mSubscription.request(10);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}
