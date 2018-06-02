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

//	private static final int NUM_TWO_PLAYER_CARDS = 5;

	private static final int NUM_TWO_PLAYER_CARDS = 10;
	
	private static final int INIT_DEPTH = 5;
	private static final int AGENT_ID = 0;
	private static Scanner scanner;

	private static final ArrayList<String> deck = new ArrayList<String>();
	private static ArrayList<Player> players = new ArrayList<Player>();
	
	private static int globalAlphaBetaCounter = 0;
	
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

//						for (int k = 0; k < copyPlayers.size(); k++) {
////							copyPlayers.get(k).updateCards("hello there");
//							ArrayList<String> testHand = new ArrayList<String>();
//							testHand.add("ok");
//							copyPlayers.get(k).updateHand(testHand);
////							ArrayList<String> actualPlayerHand = players.get(k).getSelectedCards();
//							ArrayList<String> actualPlayerHand = players.get(k).getCardsInHand();
//							System.out.println("The actual player's hand cards are:");
//							for (int checkInd = 0; checkInd < actualPlayerHand.size(); checkInd++)
//								System.out.println(actualPlayerHand.get(checkInd));
//						}
						ScoreAction optimalScoreAction = Vmaxmin(copyPlayers, INIT_DEPTH, AGENT_ID, 
																Integer.MIN_VALUE, Integer.MAX_VALUE);
						System.out.println("number states explored: " + Integer.toString(globalAlphaBetaCounter));
						System.out.println("number of actions selected is: " + Integer.toString(optimalScoreAction.actions.size()));
						ArrayList<String> currPlayerHand = currPlayer.getCardsInHand();
						for (int numAct = 0; numAct < optimalScoreAction.actions.size(); numAct++) {
							String optActionCard = optimalScoreAction.actions.get(numAct);
							handleHandUpdates(currPlayer, optActionCard, (numAct == 1));
						}
						currPlayer.updateHand(currPlayerHand);
					} else {
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

				players = rotateHandCards(players);
			}

			//Update each player's score
			for (int n = 0; n < players.size(); n++) {
//				countScoreInHand(players.get(n).getSelectedCards(), players.get(n));
				getScore(players.get(n).getSelectedCards(), players.get(n), players);
			}
			//			handleMakiScore();

			//Show each player's score at the end of the round and deal a new hand
			for (int k = 0; k < players.size(); k++) {
				Player currPlayer = players.get(k);
				currPlayer.resetRoundCards();
				currPlayer.resetSelectedCards();
				currPlayer.updateTotalPoints(currPlayer.getRoundPoints());
				System.out.println("Round " + (r+1) + " Score for player " + (k + 1) + ": ");
				System.out.println(players.get(k).getRoundPoints());
				currPlayer.setRoundPoints(0);
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
		getScore(currHand, currPlayer, players);
//		currPlayer.setRoundPoints(currScore);
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

	// get score based on num of each card in player class
	private static int getScore(ArrayList<String> currHand, Player currPlayer, ArrayList<Player> allPlayers) {
		int currScore = 0;

		currScore += ((currPlayer.getNumTempura() / 2) * 5);
		currScore += ((currPlayer.getNumSashimi() / 3) * 10);
		currScore += calcDumplingScore(currPlayer.getNumDumpling());
		currScore += makiScore(currPlayer, allPlayers);
		currPlayer.updateRoundPoints(currScore);
		return currPlayer.getRoundPoints();
	}

	private static int makiScore(Player currPlayer, ArrayList<Player> allPlayers) {
		int score = 0;
		int firstMostMaki = 0;
		int numFirstPlayers = 0;
		int secondMostMaki = 0;
		int numSecondPlayers = 0;

		int numMakiCurrPlayer = currPlayer.getNumMaki();
		for (Player player: allPlayers) {
			int numMaki = player.getNumMaki();
			
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

	private static ScoreAction Vmaxmin(ArrayList<Player> allPlayers, int depth, int agentIndex,
			int alpha, int beta) {
		Player agent = allPlayers.get(agentIndex);
		int bestScore = Integer.MIN_VALUE;
		int worstScore = Integer.MAX_VALUE;
		ScoreAction optimalActionScore = null;
		
		if (agent.getCardsInHand().size() == 0 || depth == 0) {
			int currGameState = evaluationFunction(allPlayers);
			ScoreAction retScoreAction = new ScoreAction();
			retScoreAction.score = currGameState;
			return retScoreAction;
		} else {
			int nextDepth = depth;
			int nextAgentIndex = agentIndex + 1;
			if (agentIndex == allPlayers.size() - 1) {
				nextDepth = depth - 1;
				nextAgentIndex = 0;
			}
			ArrayList<String> possibleActions = agent.getCardsInHand();
//			ArrayList<ScoreAction> allActionScores = new ArrayList<ScoreAction>();
			boolean chopsticksExplore = true;
			for (int s = 0; s < possibleActions.size(); s++) {
				Player copyPlayer = new Player(agent);
//				ArrayList<String> selectedCards = copyPlayer.getSelectedCards();
				ArrayList<String> handCards = copyPlayer.getCardsInHand();
				String currCard = handCards.get(s);
				if (currCard.equals("chopsticks")) {
					copyPlayer.incrementNumChopsticks();
				}
				copyPlayer.updateSelectedCards(currCard);
				copyPlayer.removeSelectedCards(currCard);
				updatePlayerHand(copyPlayer, currCard);
				
				//form player array for next iteration
				ArrayList<Player> currChoicePlayers = new ArrayList<Player>();
				for (int p = 0; p < allPlayers.size(); p++) {
					if (p == agentIndex)
						currChoicePlayers.add(copyPlayer);
					else
						currChoicePlayers.add(allPlayers.get(p));
				}
				if (nextAgentIndex == 0)
					currChoicePlayers = rotateHandCards(currChoicePlayers);
				ScoreAction recurseScoreAction = Vmaxmin(currChoicePlayers, nextDepth, nextAgentIndex, 
														 alpha, beta);
				ScoreAction currScoreAction = new ScoreAction();
				currScoreAction.actions.add(currCard);
				currScoreAction.score = recurseScoreAction.score;
				if (agentIndex == 0) {
					if (recurseScoreAction.score > bestScore) {
						bestScore = recurseScoreAction.score;
						optimalActionScore = currScoreAction;
					}
					if (recurseScoreAction.score > alpha) {
						alpha = recurseScoreAction.score;
					}
					if (beta <= alpha) {
						chopsticksExplore = false;
						break;
					}
					
				} else {
					if (recurseScoreAction.score < worstScore) {
						worstScore = recurseScoreAction.score;
						optimalActionScore = currScoreAction;
					}
					
					if (recurseScoreAction.score < beta) {
						beta = recurseScoreAction.score;
					}
					if (beta <= alpha) {
						chopsticksExplore = false;
						break;
					}
				}
			}
			
			if (chopsticksExplore && (agent.getNumChopsticks() > 0)) {
				for (int i = 0; i < agent.getCardsInHand().size()-1; i++) {
					for (int j = i+1; j < agent.getCardsInHand().size(); j++) {
						Player copyPlayer = new Player(agent);
						String firstCard = copyPlayer.getCardsInHand().get(i);
						String secondCard = copyPlayer.getCardsInHand().get(j);
						if (firstCard.equals("chopsticks")) {
							copyPlayer.incrementNumChopsticks();
						}
						if (secondCard.equals("chopsticks")) {
							copyPlayer.incrementNumChopsticks();
						}
						copyPlayer.updateSelectedCards(firstCard);
						copyPlayer.removeSelectedCards(firstCard);
						updatePlayerHand(copyPlayer, firstCard);
						copyPlayer.updateSelectedCards(secondCard);
						copyPlayer.removeSelectedCards(secondCard);
						copyPlayer.moveChopsticksToHand();
						updatePlayerHand(copyPlayer, secondCard);
						
						//form player array for next iteration
						ArrayList<Player> currChoicePlayers = new ArrayList<Player>();
						for (int p = 0; p < allPlayers.size(); p++) {
							if (p == agentIndex)
								currChoicePlayers.add(copyPlayer);
							else
								currChoicePlayers.add(allPlayers.get(p));
						}
						if (nextAgentIndex == 0)
							currChoicePlayers = rotateHandCards(currChoicePlayers);
						ScoreAction recurseScoreAction = Vmaxmin(currChoicePlayers, nextDepth, nextAgentIndex,
																  alpha, beta);
						ScoreAction currScoreAction = new ScoreAction();
						currScoreAction.actions.add(firstCard);
						currScoreAction.actions.add(secondCard);
						currScoreAction.score = recurseScoreAction.score;
//						allActionScores.add(currScoreAction);
						if (agentIndex == 0) {
							if (recurseScoreAction.score > bestScore) {
								bestScore = recurseScoreAction.score;
								optimalActionScore = currScoreAction;
							}
							if (recurseScoreAction.score > alpha)
								alpha = recurseScoreAction.score;
							if (beta <= alpha)
								break;
							
						} else {
							if (recurseScoreAction.score < worstScore) {
								worstScore = recurseScoreAction.score;
								optimalActionScore = currScoreAction;
							}
							if (recurseScoreAction.score < worstScore) 
								beta = recurseScoreAction.score;
							if (beta <= alpha) 
								break;
						}
//						ScoreAction currScoreAction = new ScoreAction();
//						currScoreAction.actions.add(firstCard);
//						currScoreAction.actions.add(secondCard);
//						currScoreAction.score = recurseScoreAction.score;
//						allActionScores.add(currScoreAction);
					}
				}
				
			}
			
			//get the optimal ActionScore
//			int bestScore = Integer.MIN_VALUE;
//			int worstScore = Integer.MAX_VALUE;
//			ScoreAction optimalActionScore = null;
//			for (int as = 0; as < allActionScores.size(); as++) {
//				ScoreAction currActionScore = allActionScores.get(as);
//				if (agentIndex == 0 && currActionScore.score > bestScore) {
//					bestScore = currActionScore.score;
//					optimalActionScore = currActionScore;
//				} else if (agentIndex > 0 && currActionScore.score < worstScore) {
//					worstScore = currActionScore.score;
//					optimalActionScore = currActionScore;
//				}
//			}
//			System.out.println("num Vmaxmin so far: " + Integer.toString(globalAlphaBetaCounter));
			globalAlphaBetaCounter++;
			return optimalActionScore;
		}
	}

	private static int evaluationFunction(ArrayList <Player> copyPlayers) {
		// update hand based on action
		Player AI = copyPlayers.get(1);
		ArrayList <String> ai_cards = AI.getSelectedCards();
//		ai_cards.add(action);

		// calculate score of AI player's hand
		int AIscore = getScore(ai_cards, AI, copyPlayers);

		int difference = 0;
		for (Player player: copyPlayers) {
			int score = getScore(player.getSelectedCards(), player, copyPlayers);
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
		updatePlayerHand(player, cardToKeep);
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
	
	private static void updatePlayerHand(Player player, String card) {
		if (card.equals("chopsticks"))
			player.incrementNumChopsticks();
		else if (card.equals("tempura"))
			player.incrementNumTempura();
		else if (card.equals("wasabi"))
			player.incrementNumWasabi();
		else if (card.equals("dumpling"))
			player.incrementNumDumplings();
		else if (card.equals("sashimi"))
			player.incrementNumSashimi();
		else if (card.equals("one-maki"))
			player.incrementNumMaki(1);
		else if (card.equals("two-maki"))
			player.incrementNumMaki(2);
		else if (card.equals("three-maki"))
			player.incrementNumMaki(3);
		else if (card.equals("pudding"))
				player.incrementNumPudding();
		else if (card.equals("salmon-nigiri")) {
			if (player.getNumWasabi() > 0) {
				player.updateRoundPoints(6);
				player.decrementNumWasabi();
			} else player.updateRoundPoints(2);
		} else if (card.equals("squid-nigiri")) {
			if (player.getNumWasabi() > 0) {
				player.updateRoundPoints(9);
				player.decrementNumWasabi();
			} else player.updateRoundPoints(3);
		} else if (card.equals("egg-nigiri")) {
			if (player.getNumWasabi() > 0) {
				player.updateRoundPoints(3);
				player.decrementNumWasabi();
			} else player.updateRoundPoints(1);
		}
	}
}