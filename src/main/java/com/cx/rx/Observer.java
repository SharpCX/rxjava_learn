package com.cx.rx;

public interface Observer<T> {

	 default void onComplete(){};

	/**
	 * 
	 * @param t
	 */
	void onNext(T t);

	/**
	 * 
	 * @param error
	 */
	 default void onError(Throwable error){};

	 default void onSubscribe(){};

}