package com.cx.demos;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.observers.DisposableObserver;

import java.util.concurrent.atomic.AtomicInteger;

public class ComposeOpr {
    public static void main(String[] args) {
        Observable compOb = Observable.just("1", "2").compose(joinStringFlowNoDefer("/"));
        compOb.subscribe(e -> System.out.println(e));
        compOb.subscribe(e -> System.out.println(e));
    }

    private static ObservableTransformer<String, String> joinString(String separator) {
        return upstream -> upstream
                .collect(StringBuilder::new, (pre, now) -> pre.append(separator).append(now))
                .map(StringBuilder::toString)
                .toObservable();
    }

    private static FlowableTransformer<String, String> joinStringFlow(String separator) {
        return upstream -> upstream
                .collect(StringBuilder::new, (pre, now) -> pre.append(separator).append(now))
                .map(StringBuilder::toString)
                .toFlowable();
    }

    private static ObservableTransformer<String, String> joinStringFlowDefer(String separator) {
        return upstream -> {
            return Observable.defer(() -> {
                        final AtomicInteger integer = new AtomicInteger();
                        return upstream
                                .collect(StringBuilder::new, (pre, now) -> pre.append(separator).append(now).append(integer.incrementAndGet()))
                                .map(StringBuilder::toString)
                                .toObservable();
                    }
            );
        };
    }

    private static ObservableTransformer<String, String> joinStringFlowNoDefer(String separator) {
//        final AtomicInteger integer = new AtomicInteger();
        final StringBuilder sb = new StringBuilder();
        return upstream -> upstream
                .collect(StringBuilder::new, (pre, now) -> pre.append(separator).append(now).append(sb.append(now)))
                .map(StringBuilder::toString)
                .toObservable();

    }

    private static <T> ObservableOperator<T,T> doOnEmpty(Action action){
        return new ObservableOperator<T, T>() {

            @Override
            public @NonNull Observer<? super T> apply(@NonNull Observer<? super T> observer) throws Throwable {
                return new DisposableObserver<T>() {
                    boolean empty = true;
                    @Override
                    public void onNext(@NonNull T t) {
                        empty = false;
                        observer.onNext(t);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        observer.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        try {
                            action.run();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                        observer.onComplete();
                    }
                };
            }
        };
    }


}
