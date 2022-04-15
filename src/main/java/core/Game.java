package core;

import java.util.ArrayList;


public class Game {
	public Dominoes dominoes;
	public ArrayList<Domino> playedDominoes;
	public Player player;
	
	public Game() {
		dominoes = new Dominoes();
		playedDominoes = new ArrayList<Domino>();
		player = new Player("Player");
	}

	public void initGame() {
		playedDominoes.clear();
		player.clearHand();
		player.clearScore();
	}

	public void deal() {
		while(true) {
			player.dealDomino(dominoes.drawDomino());
			
			if(player.dominoCount() == 27)
				return;
		}
	}	

	public boolean doBegMove(Domino dom) {
		if(playedDominoes.size() == 0) {
			playedDominoes.add(dom);
			return true;
		}
		
		Domino firstDomino = playedDominoes.get(0);
		
		if(dom.getRightVal() == firstDomino.getLeftVal()) {
			playedDominoes.add(0, dom);
			return true;
		}
		
		if(dom.getLeftVal() == firstDomino.getLeftVal()) {
			dom.flip();
			playedDominoes.add(0, dom);
			return true;
		}else {
			return false;
		}
	}

	public boolean doEndMove(Domino dom) {	
		Domino lastDomino = playedDominoes.get(playedDominoes.size()-1);
		
		if(dom.getLeftVal() == lastDomino.getRightVal()) {
			playedDominoes.add(dom);
			return true;
		}
		
		if(dom.getRightVal() == lastDomino.getRightVal()) {
			dom.flip();
			playedDominoes.add(dom);
			return true;
		}else {
			return false;
		}
	}

	public void doFirstMove() {
		deal();
		doBegMove(dominoes.drawDomino());
	}
}
