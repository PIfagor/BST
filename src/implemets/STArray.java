package implemets;


import interfaces.ST;

import java.lang.reflect.Array;
import java.util.Iterator;

import lib.princeton.StdIn;
import lib.princeton.StdOut;

class Node<Key,Value>{
	Key key;
	Value val;
}

public class STArray<Key extends Comparable<Key>,Value> implements ST<Key, Value> {

	private Node<Key,Value>[] map;
	private int n;
	
	public STArray() throws ClassNotFoundException{
		Class<?> c = Class.forName("ua.com.oka.lection8.Node");
		map = (Node[]) Array.newInstance(c, 1);
	}
	
	@Override
	public void put(Key key, Value val){
		if (key == null) return;
		int i = rank(key);
		if (isEmpty()){
			Node t = new Node();
			t.key = key;
			t.val = val;
			map[n++]= t;
			return;
		}
		if (i<n && map[i].key.compareTo(key)==0)
			map[i].val=val;
		else{
			if (n==map.length) resize(2*map.length);
			for (int j=n;j>i;j--){
				map[j]=map[j-1];
			}
			map[i]=new Node();
			map[i].key=key;
			map[i].val=val;
			n++;
		}
	}
	
	@Override
	public Value get(Key key){
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < n && map[i].key.compareTo(key) == 0) return map[i].val;
		else return null;
	}
	@Override
	public boolean isEmpty() {
		return n==0;
	}
	@Override
	public boolean contains(Key key){
		return get(key)!=null;
	}
	@Override
	public Iterable<Key> keys(){
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<Key>, Iterable<Key>{
		private int i=0;
		@Override
		public boolean hasNext() {
			return i<n;
		}

		@Override
		public Key next() {
			return map[i++].key;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public Iterator<Key> iterator() {
			return this;
		}
	}
	
	@Override
	public int rank(Key key){
		int lo = 0, hi = n-1;
		while (lo <= hi){
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(map[mid].key);
			if (cmp < 0) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else if (cmp == 0) return mid;
		}
		return lo;
	}
	
	private void resize(int capacity){
		Node<Key,Value>[] copy =  (Node[])Array.newInstance(Node.class, capacity);
			//(Node<Key,Value>[])new Object[capacity];
		for (int i=0;i<n;i++)
			copy[i]=map[i];
		map = copy;
	}
	
	public static void main(String[] args) throws ClassNotFoundException{
		
		STArray<String, Integer> st = new STArray<String, Integer>();
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
	public void delete(Key key) {
		// TODO Auto-generated method stub
		
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
