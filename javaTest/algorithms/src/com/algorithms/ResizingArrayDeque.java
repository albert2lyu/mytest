package com.algorithms;

import java.util.Iterator;

public class ResizingArrayDeque<T> implements Iterable<T> {

	@SuppressWarnings("unchecked")
	private T[] a = (T[])new Object[1];
	private int N = 0;
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	public void pushLeft(T t){
		if(N == a.length)
			resize(2*N);
		for(int i=N-1; i>0; i--){
			a[i+1] = a[i];
		}
		a[0] = t;
		N++;
	}
	
	public void pushRight(T t){
		if(N == a.length)
			resize(2*N);
		a[N++] = t;
	}
	
	public T popLeft(){
		T t = a[0];
		for(int i=1; i<N-1; i++){
			a[i-1] = a[i];
		}
		a[--N] = null;
		if(N > 0 && N == a.length/4){
			resize(a.length/2);
		}
		return t;
	}
	
	public T popRight(){
		T t = a[--N];
		a[N] = null;
		if(N > 0 && N == a.length/4){
			resize(a.length/2);
		}
		return t;
	}
	
	@SuppressWarnings({ "unchecked" })
	private void resize(int length){
		T[] temp = (T[])new Object[length];
		for(int i=0; i<length; i++){
			temp[i] = a[i];
		}
		a = temp;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ResizingArrayDequeIterator();
	}
	
	private class ResizingArrayDequeIterator implements Iterator<T> {

		private int current = 0;
		@Override
		public boolean hasNext() {
			return current == N;
		}

		@Override
		public T next() {
			return a[current++];
		}

		@Override
		public void remove() {	
		}
	}

}
