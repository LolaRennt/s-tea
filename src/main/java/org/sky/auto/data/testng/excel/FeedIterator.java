package org.sky.auto.data.testng.excel;

import java.util.Iterator;

import org.databene.benerator.Generator;
import org.databene.benerator.wrapper.ProductWrapper;

public class FeedIterator implements Iterator<Object[]> {
	private Generator<Object[]> generator;
	private ProductWrapper<Object[]> next;

	public FeedIterator(Generator<Object[]> generator) {
	    this.generator = generator;
	    this.next = new ProductWrapper<Object[]>();
	    fetchNext();
    }
	
	// interface -------------------------------------------------------------------------------------------------------

	public boolean hasNext() {
	    return (next != null);
    }

	public Object[] next() {
		if (next == null)
			throw new IllegalStateException("No data available in next(). Call hasNext() to check.");
		Object[] result = next.unwrap();
		fetchNext();
	    return result;
    }

	public void remove() {
	    throw new UnsupportedOperationException("remove() is not supported");
    }
	
	// helper method ---------------------------------------------------------------------------------------------------
	
	private void fetchNext() {
		this.next = generator.generate(next);
    }
}
