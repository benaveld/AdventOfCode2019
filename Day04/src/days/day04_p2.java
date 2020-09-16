package days;

public class day04_p2 {

	public static boolean validPassword(int input) {
		if(input >= Math.pow(10, 6) || input < Math.pow(10, 5)) {
			throw new IllegalArgumentException("The input needs to be 6 digest long.");
		}
		
		boolean doubleDiget = false;
		int consecetiveNumbers = 1;
		int lastDiget = getDiget(input, 5);
		for(int i = 4; i >= 0; i--) {
			int diget = getDiget(input, i);
			if(lastDiget > diget) {
				return false;
			}
			if(lastDiget != diget && consecetiveNumbers == 2) {
				doubleDiget = true;
			}
			if(lastDiget == diget) {
				consecetiveNumbers++;
			} else {
				consecetiveNumbers = 1;
			}
			lastDiget = diget;
		}
		
		if(consecetiveNumbers == 2) {
			doubleDiget = true;
		}
		
		return doubleDiget;
	}
	
	public static int getDiget(int digets, int index) {
		return Math.floorDiv(digets % (int) Math.pow(10, index+1), (int) Math.pow(10,index));
	}
	
	public static int getNrOfPossibleAnswersInRange(int min, int max) {
		int count = 0;
		for(int i = min; i <= max; i++) {
			count += validPassword(i) ? 1 : 0;
		}
		return count;
	}
	
	public static void main(String[] args) {
		int answer = getNrOfPossibleAnswersInRange(271973, 785961);
		System.out.format("Answer: %d\n", answer);
	}

}
