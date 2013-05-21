package com.algorithms;

import java.util.Iterator;

public class LinkedBag<T> implements Iterable<T> {

	private class Node {
		T self;
		Node next;
	}
	
	private Node first;
	public void add(T t){
		Node oldFirst = first;
		first = new Node();
		first.self = t;
		first.next = oldFirst;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayBagIterator();
	}
	
	private class ArrayBagIterator implements Iterator<T> {

		private Node current;
		@Override
		public boolean hasNext() {
			return current != null;
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
