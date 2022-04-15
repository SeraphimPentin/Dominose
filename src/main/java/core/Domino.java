package core;

public class Domino {
	public static final int min = 0;
	public static final int max = 6;

	private int leftVal  = min;
	private int rightVal = min;
	private boolean isDouble;

	public Domino(int left, int right) {
		if(left >= min && left <= max) {
			leftVal = left;
		}
		if(right >= min && right <= max) {
			rightVal = right;
		}
		if(right == left) {
			isDouble = true;
		}
	}


	public void flip() {
		int temp = rightVal;
		rightVal = leftVal;
		leftVal  = temp;		
	}

	public int getLeftVal() {
		return leftVal;
	}

	public int getRightVal() {
		return rightVal;
	}

	public boolean isDouble() {
		return isDouble;
	}

	@Override
	public String toString()
	{
		String leftStr;
		String rightStr;

		if(leftVal >= 1  && leftVal <= max)
			leftStr = "[" +leftVal;
			else leftStr = "[ ";

		if(rightVal >= 1  && rightVal <= max)
			rightStr = rightVal + "]";
		else rightStr = " ]";

		return leftStr + "|"+ rightStr;
	}

	public String toValue() {
		String left = "";
		String right = "";
		left = left + leftVal;
		right = right + rightVal;
		return left + right;

	}
}
