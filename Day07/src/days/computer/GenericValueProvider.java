package days.computer;

public class GenericValueProvider<E> implements IValueProvider<E> {
	private E values[];
	private int index = 0;

	public GenericValueProvider(E[] values) {
		this.values = values;
	}

	@Override
	public E getNextValue() {
		E value = values[index];
		index++;
		if(values.length <= index) {
			index = 0;
		}
		return value;
	}
}
