package implementes;

import java.util.EmptyStackException;
import java.util.Iterator;

import interfaces.Queue;

/**
 * 
 * @author Wise
 * 
 * @param <Data>
 */

public class ArrayQueue<Data> implements Queue<Data>, java.lang.Iterable<Data> {

	public ArrayQueue(int size) {
		if (size > 0) {
			totalSize = size;
			data = (Data[]) new Object[totalSize];
		} else
			throw new EmptyStackException();
	};

	public ArrayQueue() {
		totalSize = defaultSize;
		data = (Data[]) new Object[totalSize];
	}
	
	public void enqueue (Data d) 
	{
		put (d);
	}
	
	@Override
	public void put(Data d) {
		if (needEnlarge()) {
			enlarge();
		}
		data[++currentSize] = d;

	}

	@Override
	public Data front() {
		if (!isEmpty()) {
			if (needCompress()) {
				compress();
			}
			return data[bottom + 1];
		}
		throw new EmptyStackException();
	}

	@Override
	public Data get() {

		if (!isEmpty()) {
			if (needCompress()) {
				compress();
			}
			return data[++bottom];
		}
		throw new EmptyStackException();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return currentSize - bottom;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return currentSize == bottom;
	}

	private Data[] data;
	private int defaultSize = 21;
	private int totalSize = 0;
	private int bottom = -1;
	private int currentSize = -1;
	private boolean elraged = false;

	private void enlarge() {
		totalSize *= 2;
		if (bottom < 0)
			bottom++;
		currentSize -= bottom;
		Data[] temp = (Data[]) new Object[totalSize];
		;
		for (int i = 0; i <= currentSize; i++) {
			temp[i] = data[bottom + i];
		}
		bottom = -1;
		elraged = true;
		data = temp;

	};

	private boolean needEnlarge() {
		if (totalSize * 3 / 4 == currentSize)
			return true;
		return false;
	}

	private void compress() {
		totalSize *= 0.75;
		// if(bottom<0) bottom++;
		currentSize -= bottom;
		Data[] temp = (Data[]) new Object[totalSize];
		;
		for (int i = 0; i <= currentSize; i++) {
			temp[i] = data[i + bottom + 1];
		}
		bottom = -1;
		elraged = false;
		data = temp;
	}

	private boolean needCompress() {
		if (totalSize / 4 == (currentSize - bottom) && elraged)
			return true;

		return false;
	}

	private class IterateArray<Data> implements java.util.Iterator<Data> {

		private int bot = bottom;
		private int current = currentSize;

		@Override
		public boolean hasNext() {
			return current > bot;
		}

		@Override
		public Data next() {
			return (Data) data[++bot];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public java.util.Iterator<Data> iterator() {

		return new IterateArray();
	}

}
