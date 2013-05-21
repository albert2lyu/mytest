package com.algorithms;
/**
 * 这个堆栈的实现，加入泛型/迭代器，使用数组保存所有元素，并动态调整数组大小，以保持数组大小和实际的栈大小之比小于一个常数
 */
import java.util.Iterator;

public class ArrayStack<T> implements Iterable<T>{

	@SuppressWarnings("unchecked")
	private T[] a = (T[])new Object[1];
	private int N = 0;
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int length){
		T[] temp = (T[]) new Object[length];
		for(int i=0; i<N; i++){
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void push(T t){
		if(N == a.length)
			resize(2 * N);
		a[N++] = t;
	}
	
	public T pop(){
		T t = a[--N];
		a[N] = null;
		if(N > 0 && N == a.length/4)
			resize(a.length / 2);
		return t;
	}
	
	public T peek(){
		return a[N-1];
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayStackIterator();
	}
	
	private class ArrayStackIterator implements Iterator<T> {
		private int i = N;
		
		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public T next() {
			return a[--i];
		}

		@Override
		public void remove() {	
		}	
	}
}
