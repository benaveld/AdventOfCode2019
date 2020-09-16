package days.computer;

import java.util.LinkedList;

public class GenericWriter<E> extends LinkedList<E> implements IValueWriter<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 472459967720833984L;

	@Override
	public void write(E value) {
		add(value);
	}
}
