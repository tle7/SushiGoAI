package game;

import java.util.*;

public class Player {
	
	private ArrayList<String> selectedCards;
	private ArrayList<String> cardsInHand;
	private int totalPoints;
	private int numPuddings;
	private int currRoundNumMaki;
	private int roundPoints;
	private int numChopsticksSelected;
	private int numTempura;
	private int numWasabi;
	private int numDumplings;
	private int numSashimi;
	
	public Player() {
		selectedCards = new ArrayList<String>();
		cardsInHand = new ArrayList<String>();
		totalPoints = 0;
		numPuddings = 0;
		currRoundNumMaki = 0;
		roundPoints = 0;
		numChopsticksSelected = 0;
	}
	
	//copy constructor
	Player (Player copyPlayer) {
		totalPoints = copyPlayer.getTotalPoints();
		numPuddings = copyPlayer.getNumPuddings();
		currRoundNumMaki = copyPlayer.getNumMaki();
		roundPoints = copyPlayer.getRoundPoints();
		numChopsticksSelected = copyPlayer.getNumChopsticks();
		cardsInHand = new ArrayList<String>();
		selectedCards = new ArrayList<String>();
		ArrayList<String> copyHand = copyPlayer.getCardsInHand();
		ArrayList<String> copySelected = copyPlayer.getSelectedCards();
		for (int i = 0; i < copyHand.size(); i++) {
			cardsInHand.add(copyHand.get(i));
		}
		for (int i = 0; i < copySelected.size(); i++) {
			selectedCards.add(copySelected.get(i));
		}
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
	
	public void removeSelectedCards(String cardToRemove) {
		selectedCards.remove(cardToRemove);
	}
	
	public ArrayList<String> getSelectedCards() {
		return selectedCards;
	}
	
	public void setNumMaki(int numMaki) {
		currRoundNumMaki = numMaki;
	}
	
	public void resetRoundCards() {
		currRoundNumMaki = 0;
		numTempura = 0;
		numWasabi = 0;
		numChopsticksSelected = 0;
		numDumplings = 0;
		numSashimi = 0;
	}
	
	public int getNumMaki() {
		return currRoundNumMaki;
	}
	
	public void resetSelectedCards() {
		selectedCards.clear();
	}
	
	public void incrementNumMaki(int numMaki) {
		currRoundNumMaki += numMaki;
	}
	
	public void incrementNumChopsticks() {
		numChopsticksSelected++;
	}
	
	public void incrementNumTempura() {
		numTempura++;
	}
	
	public void incrementNumWasabi() {
		numWasabi++;
	}
	
	public void decrementNumWasabi() {
		numWasabi--;
	}
	
	public void incrementNumDumplings() {
		numDumplings++;
	}
	
	public void incrementNumSashimi() {
		numSashimi++;
	}
	
	public void incrementNumPudding() {
		numPuddings ++;
	}
	
	public int getNumWasabi() {
		return numWasabi;
	}
	
	public int getNumDumpling() {
		return numDumplings;
	}
	
	public int getNumTempura() {
		return numTempura;
	}
	
	public int getNumSashimi() {
		return numSashimi;
	}
	
	public int getNumChopsticks() {
		return numChopsticksSelected;
	}
	
	public void moveChopsticksToHand() {
		assert numChopsticksSelected > 0;
		selectedCards.remove("chopsticks");
		numChopsticksSelected--;
		cardsInHand.add("chopsticks");
	}
	
 }
