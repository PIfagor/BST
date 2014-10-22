package interfaces;
public interface ST <Key extends Comparable<Key>, Value> {
	/**
	 * @author Wise
	 * 
	 *	val не може бути null
	 *	метод get() повертає null якщо ключ відсутній
	 *	метод put() перезаписує старе значення новим
	 *
	 */
	public void put(Key key, Value val); 
	public Value get(Key key); 
	public void delete(Key key); 
	public boolean contains(Key key);
	public boolean isEmpty();
	public int size();
	//найменший ключ
	public Key min();
	//найбільший ключ
	public Key max();
	 //найбільший ключ менший або рівний key
	public Key floor(Key key);
	//найменший ключ більший або рівний key
	public Key ceiling(Key key);
	//кількість ключів менших за key
	public int rank(Key key);
	// key k
	public Key select(int k);
	public void deleteMin() ;
	public void deleteMax();
	//кількість ключів в [lo..hi]
	public int size(Key lo, Key hi);
	//повертає ітератор по ключам
	public Iterable<Key> keys();
	public Iterable<Key> keys(Key lo, Key hi);
}