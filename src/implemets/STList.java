package implemets;


import interfaces.ST;

import java.util.Iterator;

import lib.princeton.StdIn;
import lib.princeton.StdOut;

public class STList<Key extends Comparable<Key>,Value> implements ST<Key, Value>  {

	private Node first = null;
	@Override
	public void put(Key key, Value val){
		if (key == null)
			return;
		if (isEmpty()){
			first = new Node();
			first.key = key;
			first.value = val;
			first.next = null;
			return;
		}
		Node temp = first;
		while (temp!=null){
			if (temp.key.equals(key)){
				temp.value=val;
				return;
			}
			temp = temp.next;
		}
		Node oldFirst = first;
		first = new Node();
		first.key=key;
		first.value=val;
		first.next=oldFirst;
	}
	@Override
	public Value get(Key key){
		if (key == null) return null; // краще кидати Exception 
		Node temp = first;
		while (temp!=null){
			if (temp.key.equals(key)){
				return (Value)temp.value;
			}
			temp = temp.next;
		}
		return null;
	}
	@Override
	public boolean contains(Key key){
		return get(key)!=null;
	}
	@Override
	public boolean isEmpty(){
		return first==null;
	}
	@Override
	public void delete(Key key){
		if (key == null) return;
		put(key,null);
	}
	@Override
	public Iterable<Key> keys(){
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<Key>, Iterable<Key>{

		private Node current = first;
		@Override
		public boolean hasNext() {
			return current!=null;
		}
		@Override
		public Key next() {
			Key key = (Key) current.key;
			current = current.next;
			return key;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Iterator<Key> iterator() {
			return this;
		}
		
	}
	
	private class Node<Key,Value>{
		Key key;
		Value value;
		Node next;
	}
	
	public static void main(String[] args){
		ST<String, Integer> st = new STList<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++){
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Key min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key floor(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key ceiling(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int rank(Key key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Key select(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMax() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size(Key lo, Key hi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Key> keys(Key lo, Key hi) {
		// TODO Auto-generated method stub
		return null;
	}
}
