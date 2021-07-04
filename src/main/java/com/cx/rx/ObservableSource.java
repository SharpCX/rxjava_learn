package com.cx.rx;

public interface ObservableSource<T> {

	/**
	 * 
	 * @param observer
	 */
	abstract void subscribe(com.cx.rx.Observer<T> observer);

}