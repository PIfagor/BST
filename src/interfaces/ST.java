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
	public Value get(Key key) throws Exception; 
	public void delete(Key key) throws Exception; 
	public boolean contains(Key key) throws Exception;
	public boolean isEmpty();
	public int size();
	//��������� ����
	public Key min();
	//��������� ����
	public Key max();
	 //��������� ���� ������ ��� ����� key
	public Key floor(Key key) throws Exception;
	//��������� ���� ������ ��� ����� key
	public Key ceiling(Key key) throws Exception;
	//������� ������ ������ �� key
	public int rank(Key key);
	// key k
	public Key select(int k);
	public void deleteMin() throws Exception ;
	public void deleteMax() throws Exception;
	//������� ������ � [lo..hi]
	public int size(Key lo, Key hi) throws Exception;
	//������� �������� �� ������
	public Iterable<Key> keys();
	public Iterable<Key> keys(Key lo, Key hi) throws Exception;
}