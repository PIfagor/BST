package implementes;


import interfaces.ST;

import java.util.ArrayList;
import java.util.Iterator;

import lib.princeton.StdIn;
import lib.princeton.StdOut;

public class STList<Key extends Comparable<Key>,Value> implements ST<Key, Value>  {

	private Node first = null;
	private int count = 0;
	@Override
	public void put(Key key, Value val){
		if (key == null)
			return;
		if (isEmpty()){
			first = new Node();
			first.key = key;
			first.value = val;
			first.next = null;
			++count;
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
		++count;
	}
	@Override
	public Value get(Key key) throws Exception{
		if (key == null)
			throw new Exception("Key must be NOT NULL"); 
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
	public boolean contains(Key key) throws Exception{
		return get(key)!=null;
	}
	@Override
	public boolean isEmpty(){
		return first==null;
	}
	@Override
	public void delete(Key key){
		if (key == null) return;
		
		Node temp = first;
		while (temp.next!=null){
			if (temp.next.key.equals(key)){
				temp.next = temp.next.next;
				count --;
				return;
			}
			temp = temp.next;
		}
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


	@Override
	public int size() {
		return count;
	}

	@Override
	public Key min() {
		Node temp = first;
		Key min = (Key) temp.key;  
		while (temp.next!=null){
			if (((Comparable<Key>) temp.key).compareTo((Key) temp.next.key)>0){
				min=(Key) temp.next.key;
			}
			temp = temp.next;
		}
		return min;
	}

	@Override
	public Key max() {
		Node temp = first;
		Key max = (Key) temp.key;  
		while (temp.next!=null){
			if (((Comparable<Key>) temp.key).compareTo((Key) temp.next.key)<0){
				max=(Key) temp.next.key;
			}
			temp = temp.next;
		}
		return max;
	}

	@Override
	public Key floor(Key key) throws Exception {
		if (contains(key))
		 return key;
		Node temp = first;
		Node flour = first.next;
		if (flour == null)
			return (Key) first.key;
		
		while (temp!=null)
		{
			if(key.compareTo((Key) temp.key)>0)
				if (((Comparable<Key>) flour.key).compareTo((Key) temp.key)>0)
					flour = temp;
				temp = temp.next;
		}
		return (Key) flour.key;
				
	}

	@Override
	public Key ceiling(Key key) throws Exception {
		if (contains(key))
			 return key;
			Node temp = first;
			Node flour = first.next;
			if (flour == null)
				return (Key) first.key;
			
			while (temp!=null)
			{
				if(key.compareTo((Key) temp.key)<0)
					if (((Comparable<Key>) flour.key).compareTo((Key) temp.key)>0)
						flour = temp;
					temp = temp.next;
			}
			return (Key) flour.key;
	}

	@Override
	public int rank(Key key) {
		int result = 0;
		Node temp = first;
		while (temp!=null)
		{
			if(key.compareTo((Key) temp.key)==0)
				return  result;
				++result;
				temp = temp.next;
		}
		return 0;
	}

	@Override
	public Key select(int k) {
		if (k<0 && k>count) return null;
		
		Node temp = first;
		for (int i = 0; i < count - k-1; i++)
			temp = temp.next;
		
		return (Key) temp.key;
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
	public int size(Key lo, Key hi) throws Exception {
		return rank(ceiling(lo))-rank(floor(hi));
	}

	@Override
	public Iterable<Key> keys(Key lo, Key hi) throws Exception {
		Iterable<Key> keys = new ArrayList<>();
			if (lo.compareTo(hi)== 0) 
				((ArrayList<Key>) keys).add((Key) lo);
			else {
				Node temp = first;
				int end = rank(ceiling(lo));
				int str = rank(floor(hi));
				for (int i = 0; i < str-1; i++) {
					temp = temp.next;
				}
				for (int i = str; i <=end; i++) {
					((ArrayList<Key>) keys).add((Key) temp.next.key);
					temp = temp.next;
				}
			}
		return keys;
	}
	
	public static void main(String[] args) throws Exception{
		ST<String, Integer> st = new STList<String, Integer>();
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
		
//		System.out.println("size: " + st.size("b","d"));
//		
//		ArrayList<String> al = (ArrayList) st.keys("b","d");
//		for (String string : al) {
//			System.out.println(string);
//		}
		
		st.delete("c");
		st.delete("b");
		st.delete("e");
		
		
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
}
