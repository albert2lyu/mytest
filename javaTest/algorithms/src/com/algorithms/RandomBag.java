package com.algorithms;

import java.util.Iterator;
import java.util.Random;

public class RandomBag<T> implements Iterable<T> {

	@SuppressWarnings("unchecked")
	private T[] a = (T[])new Object[1];
	private Random ran = new Random();
	private int N = 0;
	
	RandomBag(){
		
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	public void add(T t){
		if(N == a.length)
			resize(2*N);
		a[N++] = t;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int length){
		T[] temp = (T[]) new Object[length];
		for(int i=0; i<N; i++){
			temp[i] = a[i];
		}
		a = temp;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new RandomBagIterator();
	}
	
	private class RandomBagIterator implements Iterator<T> {
		
		private int current = 0;
		public RandomBagIterator() {
			int r;
			T temp;
			for(int i=0; i<N; i++){
				r = i + ran.nextInt(N-i); //随机i到N-1之间的数，和i处的替换
				temp = a[i];
				a[i] = a[r];
				a[r] = temp;
			}
		}
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
