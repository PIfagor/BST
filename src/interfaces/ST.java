package interfaces;
public interface ST <Key extends Comparable<Key>, Value> {
	/**
	 * @author Wise
	 * 
	 *	val �� ���� ���� null
	 *	����� get() ������� null ���� ���� �������
	 *	����� put() ���������� ����� �������� �����
	 *
	 */
	public void put(Key key, Value val); 
	public Value get(Key key); 
	public void delete(Key key); 
	public boolean contains(Key key);
	public boolean isEmpty();
	public int size();
	//��������� ����
	public Key min();
	//��������� ����
	public Key max();
	 //��������� ���� ������ ��� ����� key
	public Key floor(Key key);
	//��������� ���� ������ ��� ����� key
	public Key ceiling(Key key);
	//������� ������ ������ �� key
	public int rank(Key key);
	// key k
	public Key select(int k);
	public void deleteMin() ;
	public void deleteMax();
	//������� ������ � [lo..hi]
	public int size(Key lo, Key hi);
	//������� �������� �� ������
	public Iterable<Key> keys();
	public Iterable<Key> keys(Key lo, Key hi);
}