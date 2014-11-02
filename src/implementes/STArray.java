package implementes;



import interfaces.ST;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
	private int  countDel = 0;
	private int ERRORAMOUNT ;
	public STArray() throws ClassNotFoundException{
		Class<?> c = Class.forName("implementes.Node");
		map = (Node[]) Array.newInstance(c, 1);
		
	
	}
	private void removeNulls()
	{
		for (int i = 0; i < countDel; i++) {
			for (int j = 0; j < n; j++) {
				if(map[j].val == null)
				{
					for (int j2 = j; j2 < n; j2++) {
						map[j2] = map[j2+1];
					}
					n--;
					break;
				}
			}
		}
		countDel = 0;
	};
	
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
			if (n==map.length) resize(2*map.length+1);
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
	public Value get(Key key) throws Exception{
		if (key == null)
			throw new Exception("Key muts be NOT NULL"); 
		if (isEmpty()) return null;
		if (countDel>ERRORAMOUNT) 
			removeNulls();
		int i = rank(key);
		if (i < n && map[i].key.compareTo(key) == 0) return map[i].val;
		else return null;
	}
	@Override
	public boolean isEmpty() {
		return n==0;
	}
	@Override
	public boolean contains(Key key) throws Exception{
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
		ERRORAMOUNT = (int)map.length/2;
		ERRORAMOUNT--;
		
	}
	
	@Override
	public int size() {
		if (countDel>ERRORAMOUNT) 
			removeNulls();
		return n;
	}

	@Override
	public void delete(Key key) throws Exception {
		if(contains(key))
		{
			put(key,null);
			countDel++;
		}
		else 
			throw new Exception("No such key");
	}

	@Override
	public Key min() {
		if (countDel>ERRORAMOUNT) 
			removeNulls();
		return map[0].key;
	}

	@Override
	public Key max() {
		if (countDel>ERRORAMOUNT) 
			removeNulls();
		return map[n-1].key;
	}

	@Override
	public Key floor(Key key) {
		if (countDel>ERRORAMOUNT) 
			removeNulls();
		int t  = n-1;
		while (t>1&&key.compareTo(map[t].key)<=0)
			t--;
		return map[t].key;
	}

	@Override
	public Key ceiling(Key key) {
		if (countDel>ERRORAMOUNT) 
			removeNulls();
		int t  = 0;
		while (t<n&&key.compareTo(map[t].key)>=0)
			t++;
		return map[t-1].key;
	}

	@Override
	public Key select(int k) {
		if (countDel>ERRORAMOUNT) 
			removeNulls();
		if (k>0 && k<n) return map[k].key;
		return null;
	}

	@Override
	public void deleteMin() throws Exception {
		delete(min());
	}

	@Override
	public void deleteMax() throws Exception {
		delete(max());
		
	}

	@Override
	public int size(Key lo, Key hi) {

		return rank(floor(hi))-rank(ceiling(lo));
	}

	@Override
	public Iterable<Key> keys(Key lo, Key hi) {
		
		Iterable<Key> keys = new ArrayList<>();
		int str = rank(ceiling(lo));
		int end = rank(floor(hi));
		for (int i = str; i <= end; i++) {
			((ArrayList<Key>) keys).add(map[i].key);
		}
		
		return keys;
	}
	
	public static void main(String[] args) throws Exception{
		
		STArray<String, Integer> st = new STArray<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++){
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
		
		System.out.println("MIN: "+st.min());
		System.out.println("MAX: "+st.max());
		
		System.out.println("FLOUR: "+st.floor("b"));
		System.out.println("CEILING: "+st.ceiling("b"));
		
		System.out.println("size: " + st.size("c","f"));
		
		ArrayList<String> al = (ArrayList) st.keys("c","f");
		for (String string : al) {
			System.out.println(string);
		}
		
//		st.delete("c");
//		st.delete("b");
//		st.delete("e");
//		
//		
//		for (String s : st.keys())
//			StdOut.println(s + " " + st.get(s));
		
	}
	
}
