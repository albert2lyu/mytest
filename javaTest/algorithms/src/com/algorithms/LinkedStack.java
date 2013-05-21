package com.algorithms;

import java.util.Iterator;

public class LinkedStack<T> implements Iterable<T> {

	private class Node{
		T self;
		Node next;
	}
	
	private Node first;
	private int N = 0;
	
	public boolean isEmpty(){
		return N == 0;//或first == null
	}
	
	public int size(){
		return N;
	}
	
	public void push(T t){
		Node oldFirst = first;
		first = new Node();
		first.self = t;
		first.next = oldFirst;
		N++;
	}
	
	public T pop(){
		T t = first.self;
		first = first.next;
		N--;
		return t;
	}
	
	public T peek(){
		return first.self;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedStackIterator();
	}
	
	private class LinkedStackIterator implements Iterator<T> {

		private Node current = first;
		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public T next() {
			T t = current.self;
			current = current.next;
			return t;
		}

		@Override
		public void remove() {
		}		
	}

}
