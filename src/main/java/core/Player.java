package core;

import java.util.ArrayList;

public class Player {
	private String name; //players name
	private int score; //players score
	private ArrayList<Domino> hand = new ArrayList<Domino>(28); //players hand of Dominoes
	
	public Player(String nameStr) {
		if(nameStr != null) {
			name = nameStr;
		}else {
			name = "Player";
		}
	}
	
	public Domino getDominoFromHand(int index) {
		return hand.get(index);
	}
	
	public Domino removeDominoFromHand(int index) {
		if(index >= 0 && index < hand.size()) {
			Domino tempDomino = getDominoFromHand(index);
			hand.remove(index);
			
			return tempDomino;
		}else {
			return null;
		}
	}
	
	public int dominoCount(){
		return hand.size();
	}

	public void dealDomino(Domino dom) {
		if(dom != null) {
			hand.add(dom);
		}
	}
	
	public void clearHand() {
		hand.clear();
	}
	
	public void clearScore() {
		score = 0;
	}
	
	public void addToScore(int plus) {
		score += plus;
	}

	public String getName() {return name;}
}