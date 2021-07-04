package com.cx.rx;

public interface Emitter<T> {

	/**
	 * 
	 * @param t
	 */
	public void onNext(T t);

	 default void onComplete(){};

	/**
	 * 
	 * @param error
	 */
	default void onError(Throwable error){};
}