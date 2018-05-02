package game;

import java.util.*;

public class Player {
	
	private ArrayList<String> selectedCards;
	private ArrayList<String> cardsInHand;
	private int totalPoints;
	private int numPuddings;
	private int currRoundNumMaki;
	
	public Player() {
		selectedCards = new ArrayList<String>();
		cardsInHand = new ArrayList<String>();
		totalPoints = 0;
		numPuddings = 0;
		currRoundNumMaki = 0;
	}
	
	public void updateCards(String newCard) {
		selectedCards.add(newCard);
	}
	
	public ArrayList<String> getCards() {
		return selectedCards;
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
	
	public ArrayList<String> getCardsInHand() {
		return cardsInHand;
	}
	
	public void updateHand(ArrayList<String> updatedHand) {
		cardsInHand = updatedHand;
	}
	
	public void setNumMaki(int numMaki) {
		currRoundNumMaki = numMaki;
	}
	
	public void resetNumMaki() {
		currRoundNumMaki = 0;
	}
	
	public int getNumMaki() {
		return currRoundNumMaki;
	}
 }
