package days.computer;

import java.util.concurrent.LinkedBlockingQueue;

public class FeedbackLoopProvider extends LinkedBlockingQueue<Integer>
		implements IValueProvider<Integer>, IValueWriter<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8505654743147846601L;

	public FeedbackLoopProvider(int... prefix) {
		super();
		for (int nr : prefix) {
			add(nr);
		}
	}

	@Override
	public Integer getNextValue() {
		try {
			return take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(Integer value) {
//		System.out.println(value);
		add(value);
	}
}
