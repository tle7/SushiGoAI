package game;

import java.util.*;

public class Player {
	
	private ArrayList<String> selectedCards;
	private ArrayList<String> cardsInHand;
	private int totalPoints;
	private int numPuddings;
	private int currRoundNumMaki;
	private int roundPoints;
	
	public Player() {
		selectedCards = new ArrayList<String>();
		cardsInHand = new ArrayList<String>();
		totalPoints = 0;
		numPuddings = 0;
		currRoundNumMaki = 0;
		roundPoints = 0;
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
	
	public void setRoundPoints(int points) {
		roundPoints = points;
	}
	
	public void updateRoundPoints(int pointsToAdd) {
		roundPoints += pointsToAdd;
	}
	
	public int getRoundPoints() {
		return roundPoints;
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
	
	public ArrayList<String> getHand() {
		return cardsInHand;
	}
	
	public void updateSelectedCards(String cardToAdd) {
		selectedCards.add(cardToAdd);
	}
	
	public ArrayList<String> getSelectedCards() {
		return selectedCards;
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
	
	public void resetSelectedCards() {
		selectedCards.clear();
	}
 }
