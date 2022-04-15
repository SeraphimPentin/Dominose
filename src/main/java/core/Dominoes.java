package core;


public class Dominoes {
	private Domino []dominoes = new Domino[28];
	int index;
	
	public Dominoes() {
		int counter = 0;
		
		for(int leftVal = Domino.min; leftVal <= Domino.max; leftVal++) {
			for (int rightVal = leftVal; rightVal <= Domino.max; rightVal++) {
				dominoes[counter++] = new Domino(leftVal,rightVal);
			}
		}
		index = 0;
	}
	
	public Domino drawDomino() {
		if(index < dominoes.length)
			return dominoes[index++];
		else return null;
	}
}