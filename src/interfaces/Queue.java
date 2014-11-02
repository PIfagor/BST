package interfaces;

/**
 * 
 * @author Wise
 * 
 * @param <Data>
 */
public interface Queue<Data> {
	public void put(Data d);

	public Data front();

	public Data get();

	public int size();

	public boolean isEmpty();
}
