package com.cx.rx;

import com.cx.rx.impl.ListObservable;
import com.cx.rx.impl.MapObservable;

import java.util.List;
import java.util.function.Function;

public abstract class Observable<T> implements ObservableSource<T> {
	@Override
	public void subscribe(Observer<T> observer) {
		subscribeActual(observer);
	}

	public abstract void subscribeActual(com.cx.rx.Observer<T> observer);

	public static <T> Observable<T> fromList(List<T> list){
		return new ListObservable<>(list);
	}

	public <R> Observable<R> map(Function<T,R> mapper){
		return new MapObservable<T, R>(this, mapper);
	}

}