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

	//	private static final int NUM_TWO_PLAYER_CARDS = 1;

	private static final int NUM_TWO_PLAYER_CARDS = 4;
	private static Scanner scanner;

	private static final ArrayList<String> deck = new ArrayList<String>();
	private static ArrayList<Player> players = new ArrayList<Player>();
	//	public static final String[] cardNamesArr = new String[] {"tempura", "sashimi", "dumpling", "two-maki", "three-maki", "one-maki", "salmon-nigiri", "squid-nigiri",
	//			"egg-nigiri", "pudding", "wasabi", "chopsticks"};
	//	public static final Set<String> cardNamesSet = new HashSet<>(Arrays.asList(cardNamesArr));

//	private class ActionScore {
//		private String action;
//		private int score;
//		public ActionScore(String a, int i) {
//			action = a;
//			score = i;
//		}
//	}
	//	public static final String[] cardNamesArr = new String[] {"tempura", "sashimi", "dumpling", "two-maki", "three-maki", "one-maki", "salmon-nigiri", "squid-nigiri",
	//			"egg-nigiri", "pudding", "wasabi", "chopsticks"};
	//	public static final Set<String> cardNamesSet = new HashSet<>(Arrays.asList(cardNamesArr));

		public static void main(String[] args) {
		initializeConstants();
		//Eventually, need to add a console prompt asking the user how many players are playing.
		initializePlayers(2);

		scanner = new Scanner(System.in);

		System.out.println("Welcome to SushiGoAI!");
		System.out.println("\n");

		for (int r = 0; r < 3; r++) {
			System.out.println("Round " + (r+1));
			System.out.println("\n");
			for (int l = 0; l < players.size(); l++) {
				System.out.println("Player " + (l+1) + "'s score is: " + players.get(l).getTotalPoints());
			}
			System.out.println("\n");

			boolean roundHasEnded = false;

			if (r != 0) {
				for (int p = 0; p < players.size(); p++) {
					players.get(p).updateHand(getCardHand(NUM_TWO_PLAYER_CARDS));
				}
			}

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


					int numCardSelections = 1;
					if (currPlayer.getNumChopsticks() > 0 && currPlayer.getCardsInHand().size() > 1)
						numCardSelections = 2;
					if (i == 0) {
						//AI player
						ArrayList<Player> copyPlayers = new ArrayList<Player>();
						for (int playerId = 0; playerId < players.size(); playerId++) {
							copyPlayers.add(new Player(players.get(playerId)));
						}
						ScoreAction optimalScoreAction = Vmaxmin(copyPlayers, 2, 0);
						ArrayList<String> currPlayerHand = currPlayer.getCardsInHand();
						for (String optActionCard: optimalScoreAction.actions) {
							currPlayer.updateCards(optActionCard);
							currPlayerHand.remove(optActionCard);
						}
						currPlayer.updateHand(currPlayerHand);
						//						boolean haveChopsticks = currPlayer.getNumChopsticks() > 0;
//						for (int playerCard = 0; playerCard < numCardSelections; playerCard++) {
//							String currCard = handlePlayerCardSelection(currPlayer, (playerCard == 1) ? true : false);
//							if (currCard != null)
//								handleHandUpdates(currPlayer, currCard, (playerCard == 1) ? true : false);
//						}
					} else {
//						ArrayList<String> hand = currPlayer.getHand();
//						Random rand = new Random();
//						for (int cpuCard = 0; cpuCard < numCardSelections; cpuCard++) {
//							int randIndex = rand.nextInt(hand.size());
//							//						cardToKeep = hand.get(randIndex);
//							String cpuKeptCard = hand.get(randIndex);
//							handleHandUpdates(currPlayer, cpuKeptCard, (cpuCard == 1) ? true : false);
//							//							keptCardsList.add(hand.get(randIndex));
//							System.out.println("Choose a card to keep: " + cpuKeptCard);
//							try {
//								Thread.sleep(2000);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//						}
//						ArrayList<Player> copyPlayers = new ArrayList<Player>();
//						for (int playerId = 0; playerId < players.size(); playerId++) {
//							copyPlayers.add(new Player(players.get(playerId)));
//						}
//						ScoreAction optimalScoreAction = Vmaxmin(copyPlayers, 2, 0);
//						ArrayList<String> currPlayerHand = currPlayer.getCardsInHand();
//						for (String optActionCard: optimalScoreAction.actions) {
//							currPlayer.updateCards(optActionCard);
//							currPlayerHand.remove(optActionCard);
//						}
//						currPlayer.updateHand(currPlayerHand);
						for (int playerCard = 0; playerCard < numCardSelections; playerCard++) {
							String currCard = handlePlayerCardSelection(currPlayer, (playerCard == 1) ? true : false);
							if (currCard != null)
								handleHandUpdates(currPlayer, currCard, (playerCard == 1) ? true : false);
						}
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (roundHasEnded) break;

				//Players all pass their selected cards to the player on their left (represented by incrementing 1 player up)
//				ArrayList<String> lastPlayerHand = players.get(players.size() - 1).getHand();
//				for (int p = players.size()-1; p >= 1; p--) {
//					Player currPlayer = players.get(p);
//					Player previous = players.get(p-1);
//					ArrayList<String> previousHand = previous.getHand();
//					currPlayer.updateHand(previousHand);
//				}
//				players.get(0).updateHand(lastPlayerHand);
				players = rotateHandCards(players);
			}

			//Update each player's score
			for (int n = 0; n < players.size(); n++) {
				countScoreInHand(players.get(n).getSelectedCards(), players.get(n));
			}
			//			handleMakiScore();

			//Show each player's score at the end of the round and deal a new hand
			for (int k = 0; k < players.size(); k++) {
				Player currPlayer = players.get(k);
				currPlayer.resetNumMaki();
				currPlayer.resetSelectedCards();
				currPlayer.updateTotalPoints(currPlayer.getRoundPoints());
				System.out.println("Round " + (r+1) + " Score for player " + (k + 1) + ": ");
				System.out.println(players.get(k).getRoundPoints());
				currPlayer.resetNumChopsticks();
			}
		}
		System.out.println("Game is over");
		handlePuddingScore();
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player " + (i + 1) + "'s final score is: " + Integer.toString(players.get(i).getTotalPoints()));
		}
	}
		

	private static void initializeConstants() {
		for (int i = 0; i < TEMPURA; i++)
			deck.add("tempura");
		for (int i = 0; i < SASHIMI; i++)
			deck.add("sashimi");
		for (int i = 0; i < DUMPLING; i++)
			deck.add("dumpling");
		for (int i =0; i < TWO_MAKI; i++)
			deck.add("two-maki");
		for (int i = 0; i < THREE_MAKI; i++)
			deck.add("three-maki");
		for (int i = 0; i < ONE_MAKI; i++)
			deck.add("one-maki");
		for (int i = 0;i < SALMON_NIGIRI; i++)
			deck.add("salmon-nigiri");
		for (int i = 0; i < SQUID_NIGIRI; i++)
			deck.add("squid-nigiri");
		for (int i = 0; i < EGG_NIGIRI; i++)
			deck.add("egg-nigiri");
		for (int i = 0; i < PUDDING; i++)
			deck.add("pudding");
		for (int i =0; i < WASABI; i++)
			deck.add("wasabi");
		for (int i = 0; i < CHOPSTICKS; i++)
			deck.add("chopsticks");
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
			if(card.equals("tempura"))
				numTempura++;
			else if (card.equals("wasabi"))
				numWasabi++;
			else if (card.equals("dumpling"))
				numDumplings++;
			else if (card.equals("sashimi"))
				numSashimi++;
			else if (card.equals("one-maki"))
				numMaki++;
			else if (card.equals("two-maki"))
				numMaki += 2;
			else if (card.equals("three-maki"))
				numMaki += 3;
			else if (card.equals("pudding"))
				numPudding++;
			else if (card.equals("salmon-nigiri")) {
				if (numWasabi > 0) {
					currScore += 6;
					numWasabi--;
				} else {
					currScore += 2;
				}
			}
			else if (card.equals("squid-nigiri")) {
				if (numWasabi > 0) {
					currScore += 9;
					numWasabi--;
				} else {
					currScore += 3;
				}
			}
			else if (card.equals("egg-nigiri")) {
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

		//update number maki and number pudding and total score
		currPlayer.setNumMaki(numMaki);
		currPlayer.updateNumPuddings(numPudding);
		currScore = getScore(currHand, currPlayer);
		currPlayer.setRoundPoints(currScore);
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

		//		System.out.println("Player 1 has Maki Score: " + Integer.toString(firstPlaceScore));
		//		System.out.println("Player 2 has Maki Score: " + Integer.toString(secondPlaceScore));

		if (secondPlaceScore > firstPlaceScore) {
			Player temp = firstPlace;
			firstPlace = secondPlace;
			secondPlace = temp;
		} 

		if (firstPlaceScore == secondPlaceScore) {
			firstPlace.updateRoundPoints(3);
			secondPlace.updateRoundPoints(3);
		} else {
			firstPlace.updateRoundPoints(6);
			secondPlace.updateRoundPoints(3);
		}
	}

	// get score without changing player properties
	private static int getScore(ArrayList<String> currHand, Player currPlayer) {
		int currScore = 0;
		int numWasabi = 0;
		int numTempura = 0;
		int numSashimi = 0;
		int numDumplings = 0;
		int numMaki = 0;
		int numPudding = 0;
		for (String card: currHand) {
			if(card.equals("tempura"))
				numTempura++;
			else if (card.equals("wasabi"))
				numWasabi++;
			else if (card.equals("dumpling"))
				numDumplings++;
			else if (card.equals("sashimi"))
				numSashimi++;
			else if (card.equals("one-maki"))
				numMaki++;
			else if (card.equals("two-maki"))
				numMaki += 2;
			else if (card.equals("three-maki"))
				numMaki += 3;
			else if (card.equals("pudding"))
				numPudding++;
			else if (card.equals("salmon-nigiri")) {
				if (numWasabi > 0) {
					currScore += 6;
					//					System.out.println("Score tripled!");
					numWasabi--;
				} else {
					currScore += 2;
				}
			}
			else if (card.equals("squid-nigiri")) {
				if (numWasabi > 0) {
					currScore += 9;
					numWasabi--;
					//					System.out.println("Score tripled!");
				} else {
					currScore += 3;
				}
			}
			else if (card.equals("egg-nigiri")) {
				if (numWasabi > 0) {
					currScore += 3;
					//					System.out.println("Score tripled!");
					numWasabi--;
				} else {
					currScore += 1;
				}
			}	
		}

		currScore += ((numTempura / 2) * 5);
		currScore += ((numSashimi / 3) * 10);
		currScore += calcDumplingScore(numDumplings);

		currScore += makiScore(currPlayer);
		return currScore;
	}

	private static int makiScore(Player currPlayer) {
		int score = 0;
		int firstMostMaki = 0;
		int numFirstPlayers = 0;
		int secondMostMaki = 0;
		int numSecondPlayers = 0;

		int numMakiCurrPlayer = 0;
		for (Player player: players) {
			int numMaki = 0;
			for (String card: player.getSelectedCards()) {
				if (card.equals("one-maki")) 
					numMaki++;
				else if (card.equals("two-maki"))
					numMaki += 2;
				else if (card.equals("three-maki"))
					numMaki += 3;
			}
			if (numMaki > firstMostMaki) {
				numFirstPlayers = 1;
				firstMostMaki = numMaki;
			} else if (numMaki == firstMostMaki) {
				numFirstPlayers++;
			} else if (numMaki > secondMostMaki) {
				numSecondPlayers = 1;
				secondMostMaki = numMaki;
			} else if (numMaki == secondMostMaki) {
				numSecondPlayers++;
			} 
			if (player == currPlayer) {
				numMakiCurrPlayer = numMaki;
			}
		}

		if (numMakiCurrPlayer == firstMostMaki && numFirstPlayers != 0) {
			score = 6/numFirstPlayers;
		} else if (numFirstPlayers == 0) {
			score = 6/players.size();
		} else if (numMakiCurrPlayer == secondMostMaki && numSecondPlayers != 0) {
			score = 3/numSecondPlayers;
		} else if (numSecondPlayers == 0) {
			score = 3/(players.size()-numFirstPlayers);
		}

		return score;
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

	private static ScoreAction Vmaxmin(ArrayList<Player> allPlayers, int depth, int agentIndex) {
		Player agent = allPlayers.get(agentIndex);
		//TODO: need case if size of cards in hand is 1
		if (agent.getCardsInHand().size() == 0 || depth == 0) {
			int currGameState = evaluationFunction(allPlayers);
			ScoreAction retScoreAction = new ScoreAction();
			retScoreAction.score = currGameState;
			return retScoreAction;
//		} else if (depth == 0) {
			//Evaluation function
		} else {
			int nextDepth = depth;
			int nextAgentIndex = agentIndex + 1;
			if (agentIndex == allPlayers.size() - 1) {
				nextDepth = depth - 1;
				nextAgentIndex = 0;
			}
			ArrayList<String> possibleActions = agent.getCardsInHand();
			ArrayList<ScoreAction> allActionScores = new ArrayList<ScoreAction>();
			for (int s = 0; s < possibleActions.size(); s++) {
				Player copyPlayer = new Player(agent);
				ArrayList<String> selectedCards = copyPlayer.getSelectedCards();
				ArrayList<String> handCards = copyPlayer.getCardsInHand();
				String currCard = handCards.get(s);
				selectedCards.add(currCard);
				handCards.remove(currCard);
				
				//form player array for next iteration
				ArrayList<Player> currChoicePlayers = new ArrayList<Player>();
				for (int p = 0; p < allPlayers.size(); p++) {
					if (p == agentIndex)
						currChoicePlayers.add(copyPlayer);
					else
						currChoicePlayers.add(allPlayers.get(p));
				}
				ScoreAction recurseScoreAction = Vmaxmin(currChoicePlayers, nextDepth, nextAgentIndex);
				ScoreAction currScoreAction = new ScoreAction();
				currScoreAction.actions.add(currCard);
				currScoreAction.score = recurseScoreAction.score;
				allActionScores.add(currScoreAction);
			}
			
			//get the optimal ActionScore
			int bestScore = Integer.MIN_VALUE;
			int worstScore = Integer.MAX_VALUE;
			ScoreAction optimalActionScore = null;
			for (int as = 0; as < allActionScores.size(); as++) {
				ScoreAction currActionScore = allActionScores.get(as);
				if (agentIndex == 0 && currActionScore.score > bestScore) {
					bestScore = currActionScore.score;
					optimalActionScore = currActionScore;
				} else if (agentIndex > 0 && currActionScore.score < worstScore) {
					worstScore = currActionScore.score;
					optimalActionScore = currActionScore;
				}
			}
			return optimalActionScore;
		}
	}

	private static int evaluationFunction(ArrayList <Player> copyPlayers) {
		// update hand based on action
		Player AI = copyPlayers.get(1);
		ArrayList <String> ai_cards = AI.getSelectedCards();
//		ai_cards.add(action);

		// calculate score of AI player's hand
		int AIscore = getScore(ai_cards, AI);

		int difference = 0;
		for (Player player: copyPlayers) {
			int score = getScore(player.getSelectedCards(), player);
			if (AIscore - score < difference) {
				difference = AIscore - score;
			}
		}
		return difference;
	}

	private static String handlePlayerCardSelection(Player player, boolean selectingSecondCard) {
		if (!selectingSecondCard) {
			System.out.println("Choose a card to keep by typing its name here: ");
		} else {
			System.out.println("You have chopsticks! Type the name of card you would like, or type \"no\" "
					+ "if you don't want to use chopsticks yet: ");
		}
		while (true) {
			String currInput = scanner.nextLine().toLowerCase();
			if (selectingSecondCard && currInput.equals("no"))
				return null;
			if (player.getHand().contains(currInput)) {
				return currInput;
			} else {
				if (!selectingSecondCard) 
					System.out.println("That card isn't in the current hand. Please enter again: ");
				else
					System.out.println("That card isn't in the current hand. Please enter again (or type \"no\"): ");
			}
		}
	}

	private static void handleHandUpdates (Player player, String cardToKeep, boolean selectingSecondCard) {
		ArrayList<String> newHand = player.getHand();
		player.updateSelectedCards(cardToKeep);
		if (cardToKeep.equals("chopsticks"))
			player.incrementNumChopsticks();
		System.out.println("card removed " + cardToKeep);
		newHand.remove(cardToKeep);
		if (selectingSecondCard) //using chopsticks
			player.moveChopsticksToHand();
		System.out.println("\n");
		System.out.println("You have chosen to keep the following card: " + cardToKeep);
		System.out.println("\n");

		player.updateHand(newHand);
		System.out.println("================");
		System.out.println("NEW HAND IS: ");
		for (int n = 0; n < newHand.size(); n++) {
			System.out.println(newHand.get(n));
		}
		System.out.println("================");
	}
	
	private static ArrayList<Player> rotateHandCards(ArrayList<Player> playersList) {
		ArrayList<String> lastPlayerHand = playersList.get(playersList.size() - 1).getHand();
		for (int p = playersList.size()-1; p >= 1; p--) {
			Player currPlayer = playersList.get(p);
			Player previous = playersList.get(p-1);
			ArrayList<String> previousHand = previous.getHand();
			currPlayer.updateHand(previousHand);
		}
		playersList.get(0).updateHand(lastPlayerHand);
		return playersList;
	}
}