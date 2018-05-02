package game;

import java.util.*;

public class GamePlay {
	private static final int TEMPURA = 14;
	private static final int SASHIMI = 14;
	private static final int DUMPLING = 12;
	private static final int TWO_MAKI = 12;
	private static final int THREE_MAKI = 8;
	private static final int ONE_MAKI = 6;
	private static final int SALMON_NIGIRI = 10;
	private static final int SQUID_NIGIRI = 5;
	private static final int EGG_NIGIRI = 5;
	private static final int PUDDING = 10;
	private static final int WASABI = 6;
	private static final int CHOPSTICKS = 4;
	
	private static final int NUM_TWO_PLAYER_CARDS = 10;
	
	private static final ArrayList<String> deck = new ArrayList<String>();
	private static final ArrayList<Player> players = new ArrayList<Player>();
	
	
	public static void main(String[] args) {
		initializeConstants();
		//Eventually, need to add a console prompt asking the user how many players are playing.
		initializePlayers(2);
<<<<<<< HEAD

		/*ArrayList<String> testArrList = new ArrayList<String>();
		testArrList.add("Sashimi");
		testArrList.add("Tempura");
		testArrList.add("Wasabi");
		testArrList.add("Sashimi");
		testArrList.add("Salmon-Nigiri");
		testArrList.add("Tempura");
		testArrList.add("Sashimi");*/

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to SushiGoAI!");
		System.out.println("\n");
		
		for (int r = 0; r < 3; r++) {
			
			System.out.println("Round " + r);
			System.out.println("\n");
			for (int l = 0; l < players.size(); l++) {
				System.out.println("Player " + (l+1) + "'s score is: " + players.get(l).getTotalPoints());
			}
			System.out.println("\n");
		
			boolean roundHasEnded = false;
			
			while(true) {
	
				for (int i = 0; i < players.size(); i++) {
					
					Player currPlayer = players.get(i);
					
					if (currPlayer.getHand().size() == 0) {
						System.out.println("End of round!");
						roundHasEnded = true;
						break;
					}
					
					System.out.println("Player " + (i+1) + ": It's your turn!");
					System.out.println("\n");
					
					if (currPlayer.getSelectedCards().size() == 0) {
						System.out.println("You have not selected any cards!");
						System.out.println("\n");
					} else {
						System.out.println("Here are your selected cards: ");
						ArrayList<String> currentSelection= players.get(i).getSelectedCards();
						for (int j = 0; j < currentSelection.size(); j++) {
							System.out.println(currentSelection.get(j));
						}	
						System.out.println("\n");
					}
					System.out.println("Here is your current hand: ");
					for (String s : currPlayer.getHand()) {
						System.out.println(s);
					}
					System.out.println("\n");
					System.out.println("Choose a card to keep by typing its name here: ");
					String cardToKeep = scanner.nextLine().toLowerCase();
					
					currPlayer.updateSelectedCards(cardToKeep);
					currPlayer.getHand().remove(cardToKeep);
					System.out.println("\n");
					System.out.println("You have chosen to keep the following card: " + cardToKeep);
					System.out.println("\n");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//countScoreInHand(testArrList, null);
				}
				
				if (roundHasEnded) break;
				
				//Players all pass their selected cards to the player on their left (represented by incrementing 1 player up)
				ArrayList<String> lastPlayerHand = players.get(players.size() - 1).getHand();
				for (int p = 1; p < players.size(); p++) {
					Player currPlayer = players.get(p);
					Player previous = players.get(p-1);
					ArrayList<String> previousHand = previous.getHand();
					currPlayer.updateHand(previousHand);
				}
				players.get(0).updateHand(lastPlayerHand);
			}
			
			//Show each player's score at the end of the round:
			for (int k = 0; k < players.size(); k++) {
				System.out.println("Score for player " + k + ": ");
				countScoreInHand(players.get(k).getHand(), players.get(k));
			}
		}
=======
>>>>>>> branch 'master' of https://github.com/tle7/SushiGoAI.git
	}
	
	private static void initializeConstants() {
		for (int i = 0; i < TEMPURA; i++)
			deck.add("Tempura");
		for (int i = 0; i < SASHIMI; i++)
			deck.add("Sashimi");
		for (int i = 0; i < DUMPLING; i++)
			deck.add("Dumpling");
		for (int i =0; i < TWO_MAKI; i++)
			deck.add("Two-Maki");
		for (int i = 0; i < THREE_MAKI; i++)
			deck.add("Three-Maki");
		for (int i = 0; i < ONE_MAKI; i++)
			deck.add("One-Maki");
		for (int i = 0;i < SALMON_NIGIRI; i++)
			deck.add("Salmon-Nigiri");
		for (int i = 0; i < SQUID_NIGIRI; i++)
			deck.add("Squid-Nigiri");
		for (int i = 0; i < EGG_NIGIRI; i++)
			deck.add("Egg-Nigiri");
		for (int i = 0; i < PUDDING; i++)
			deck.add("Pudding");
		for (int i =0; i < WASABI; i++)
			deck.add("Wasabi");
		for (int i = 0; i < CHOPSTICKS; i++)
			deck.add("Chopsticks");
	}
	
	private static void initializePlayers(int numPlayers) {
		/*For simple version, we will assume 2 players*/
		Player player1 = new Player();
		Player player2 = new Player();
		
		players.add(player1);
		players.add(player2);
		player1.updateHand(getCardHand(NUM_TWO_PLAYER_CARDS));
		player2.updateHand(getCardHand(NUM_TWO_PLAYER_CARDS));
		
	}
	
	private static ArrayList<String> getCardHand (int numCards) {
		Random rand = new Random();
		ArrayList<String> currHand = new ArrayList<String>();
		for (int i = 0; i < numCards; i++) {
			int deckInd = rand.nextInt(deck.size());
			String currCard = deck.remove(deckInd);
			currHand.add(currCard);
		}
		return currHand;
	}
	
	private static void countScoreInHand(ArrayList<String> currHand, Player currPlayer) {
		int currScore = 0;
		int numWasabi = 0;
		int numTempura = 0;
		int numSashimi = 0;
		int numDumplings = 0;
		int numMaki = 0;
		int numPudding = 0;
		for (String card: currHand) {
			if(card.equals("Tempura"))
				numTempura++;
			else if (card.equals("Wasabi"))
				numWasabi++;
			else if (card.equals("Dumpling"))
				numDumplings++;
			else if (card.equals("Sashimi"))
				numSashimi++;
			else if (card.equals("One-Maki"))
				numMaki++;
			else if (card.equals("Two-Maki"))
				numMaki += 2;
			else if (card.equals("Three-Maki"))
				numMaki += 3;
			else if (card.equals("Salmon-Nigiri")) {
				if (numWasabi > 0) {
					currScore += 6;
					numWasabi--;
				} else {
					currScore += 2;
				}
			}
			else if (card.equals("Squid-Nigiri")) {
				if (numWasabi > 0) {
					currScore += 9;
					numWasabi--;
				} else {
					currScore += 3;
				}
			}
			else if (card.equals("Egg-Nigiri")) {
				if (numWasabi > 0) {
					currScore += 3;
					numWasabi--;
				} else {
					currScore += 1;
				}
			}	
		}
		
		currScore += ((numTempura / 2) * 5);
		currScore += ((numSashimi / 3) * 10);
		currScore += calcDumplingScore(numDumplings);
		
<<<<<<< HEAD
		currPlayer.updateTotalPoints(currScore);
		
		//need to update number maki and number pudding and total score
		System.out.println(currScore);
=======
		//update number maki and number pudding and total score
		currPlayer.setNumMaki(numMaki);
		currPlayer.updateNumPuddings(numPudding);
		currPlayer.updateTotalPoints(currScore);
>>>>>>> branch 'master' of https://github.com/tle7/SushiGoAI.git
	}
	
	private static int calcDumplingScore (int numDumplings) {
		switch (numDumplings) {
		case 1:
			return 1;
		case 2:
			return 3;
		case 3:
			return 6;
		case 4: 
			return 10;
		case 5:
			return 15;
		default:
			return 0;
		}
	}
	
	//updates the maki score for two players
	private static void handleMakiScore() {
		Player firstPlace = players.get(0);
		Player secondPlace = players.get(1);
		int firstPlaceScore = firstPlace.getNumMaki();
		int secondPlaceScore = secondPlace.getNumMaki();
		
		if (secondPlaceScore > firstPlaceScore) {
			Player temp = firstPlace;
			firstPlace = secondPlace;
			secondPlace = temp;
		} 
		
		if (firstPlaceScore == secondPlaceScore) {
			firstPlace.updateTotalPoints(3);
			secondPlace.updateTotalPoints(3);
		} else {
			firstPlace.updateTotalPoints(6);
			secondPlace.updateTotalPoints(3);
		}
	}
	
	private static void handlePuddingScore() {
		Player firstPlace = players.get(0);
		Player secondPlace = players.get(1);
		int firstPlaceNumPudding = firstPlace.getNumPuddings();
		int secondPlaceNumPudding = secondPlace.getNumPuddings();
		
		if (firstPlaceNumPudding == secondPlaceNumPudding)
			return;
		
		if (firstPlaceNumPudding < secondPlaceNumPudding) {
			Player temp = firstPlace;
			firstPlace = secondPlace;
			secondPlace = temp;
		}
		
		firstPlace.updateTotalPoints(6);
		secondPlace.updateTotalPoints(-6);
	}
		
	private static void updateHandAndCards (Player player, String input) {
		input = input.toLowerCase();
		
		// update player's selected cards
		player.updateCards(input);
		
		// remove from hand
		ArrayList<String> cardsInHand = player.getCardsInHand();
		cardsInHand.remove(input);
		player.updateHand(cardsInHand);
	}
}

