package game;

import java.util.*;

public class Player {
	
	private ArrayList<Integer> selectedCards;
	private ArrayList<Integer> cardsInHand;
	private int totalPoints;
	private int numPuddings;
	private int currRoundNumMaki;
	private int roundPoints;
	private int numChopsticksSelected;
	
	private static final int CHOPSTICKS_CARD = 11;
	
	public Player() {
		selectedCards = new ArrayList<Integer>();
		cardsInHand = new ArrayList<Integer>();
		totalPoints = 0;
		numPuddings = 0;
		currRoundNumMaki = 0;
		roundPoints = 0;
		numChopsticksSelected = 0;
	}
	
//	private ArrayList<Integer> copyArrList(ArrayList<Integer>)
	
	//copy constructor
	Player (Player copyPlayer) {
		totalPoints = copyPlayer.getTotalPoints();
		numPuddings = copyPlayer.getNumPuddings();
		currRoundNumMaki = copyPlayer.getNumMaki();
		roundPoints = copyPlayer.getRoundPoints();
		numChopsticksSelected = copyPlayer.getNumChopsticks();
		cardsInHand = new ArrayList<Integer>();
		selectedCards = new ArrayList<Integer>();
		ArrayList<Integer> copyHand = copyPlayer.getCardsInHand();
		ArrayList<Integer> copySelected = copyPlayer.getSelectedCards();
		for (int i = 0; i < copyHand.size(); i++) {
			cardsInHand.add(copyHand.get(i));
		}
		for (int i = 0; i < copySelected.size(); i++) {
			selectedCards.add(copySelected.get(i));
		}
	}
	
	public void updateCards(int newCard) {
		selectedCards.add(newCard);
	}
	
	public ArrayList<Integer> getCards() {
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
	
	public ArrayList<Integer> getCardsInHand() {
		return cardsInHand;
	}
	
	public void updateHand(ArrayList<Integer> updatedHand) {
		cardsInHand = updatedHand;
	}
	
	public ArrayList<Integer> getHand() {
		return cardsInHand;
	}
	
	public void updateSelectedCards(int cardToAdd) {
		selectedCards.add(cardToAdd);
	}
	
	public void removeSelectedCards(int cardToRemove) {
		selectedCards.remove(Integer.valueOf(cardToRemove));
	}
	
	public ArrayList<Integer> getSelectedCards() {
		return selectedCards;
	}
	
	public void updateNumMaki(int numMaki) {
		currRoundNumMaki += numMaki;
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
	
	public void incrementNumChopsticks() {
		numChopsticksSelected++;
	}
	
	public int getNumChopsticks() {
		return numChopsticksSelected;
	}
	
	public void moveChopsticksToHand() {
		assert numChopsticksSelected > 0;
		selectedCards.remove(Integer.valueOf(CHOPSTICKS_CARD));
		numChopsticksSelected--;
		cardsInHand.add(CHOPSTICKS_CARD);
	}
	
	public void resetNumChopsticks() {
		numChopsticksSelected = 0;
	}
	
	public void removeHandCard(int cardToRemove) {
		cardsInHand.remove(Integer.valueOf(cardToRemove));
	}
 }
