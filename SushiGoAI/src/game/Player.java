package game;

import java.util.*;

public class Player {
	
	private ArrayList<String> cards;
	private ArrayList<String> hand;
	private int totalPoints;
	private int numPuddings;
	
	public Player() {
		cards = new ArrayList<String>();
		hand = new ArrayList<String>();
		totalPoints = 0;
		numPuddings = 0;
	}
	
	public void updateCards(String newCard) {
		cards.add(newCard);
	}
	
	public ArrayList<String> getCards() {
		return cards;
	}
	
	public void updateTotalPoints(int pointsToAdd) {
		totalPoints += pointsToAdd;
	}
	
	public int getTotalPoints() {
		return totalPoints;
	}
	
	public void updateNumPuddings(int puddingsToAdd) {
		numPuddings += puddingsToAdd;
	}
	
	public int getNumPuddings() {
		return numPuddings;
	}
	
	public void updateHand(ArrayList<String> updatedHand) {
		hand = updatedHand;
	}
 }
