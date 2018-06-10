package game;

import java.util.*;

public class GamePlay {
	//	private static final int TEMPURA = 14;
	//	private static final int SASHIMI = 14;
	//	private static final int DUMPLING = 12;
	//	private static final int TWO_MAKI = 12;
	//	private static final int THREE_MAKI = 8;
	//	private static final int ONE_MAKI = 6;
	//	private static final int SALMON_NIGIRI = 10;
	//	private static final int SQUID_NIGIRI = 5;
	//	private static final int EGG_NIGIRI = 5;
	//	private static final int PUDDING = 10;
	//	private static final int WASABI = 6;
	//	private static final int CHOPSTICKS = 0;

	private static final int TEMPURA = 14;
	private static final int SASHIMI = 14;
	private static final int DUMPLING = 12;
	private static final int TWO_MAKI = 12;
	private static final int THREE_MAKI = 8;
	private static final int ONE_MAKI = 6;
	private static final int SALMON_NIGIRI = 10;
	private static final int SQUID_NIGIRI = 5;
	private static final int EGG_NIGIRI = 0;
	private static final int PUDDING = 0;
	private static final int WASABI = 6;
	private static final int CHOPSTICKS = 0;

	private static final int TEMPURA_CARD = 0;
	private static final int SASHIMI_CARD = 1;
	private static final int DUMPLING_CARD = 2;
	private static final int TWO_MAKI_CARD = 3;
	private static final int THREE_MAKI_CARD = 4;
	private static final int ONE_MAKI_CARD = 5;
	private static final int SALMON_NIGIRI_CARD = 6;
	private static final int SQUID_NIGIRI_CARD = 7;
	private static final int EGG_NIGIRI_CARD = 8;
	private static final int PUDDING_CARD = 9;
	private static final int WASABI_CARD = 10;
	private static final int CHOPSTICKS_CARD = 11;

	private static final int NUM_TWO_PLAYER_CARDS = 6;
	//TODO: replicate bug with just squid and salmon and check min scoring
	//Vmaxmin: make sure setting consistent scoring in with right variable names
	//	private static final int NUM_TWO_PLAYER_CARDS = 10;


	private static final int INIT_DEPTH = 6;
	private static final int AGENT_ID = 0;
	private static Scanner scanner;

	private static final ArrayList<Integer> deck = new ArrayList<Integer>();
	private static ArrayList<Player> players = new ArrayList<Player>();

	private static int globalAlphaBetaCounter = 0;

	private static HashMap<Integer, String> constToName;
	private static HashMap<String, Integer> nameToConst;

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

	//	private static final int TEMPURA_CARD = 0;
	//	private static final int SASHIMI_CARD = 1;
	//	private static final int DUMPLING_CARD = 2;
	//	private static final int TWO_MAKI_CARD = 3;
	//	private static final int THREE_MAKI_CARD = 4;
	//	private static final int ONE_MAKI_CARD = 5;
	//	private static final int SALMON_NIGIRI_CARD = 6;
	//	private static final int SQUID_NIGIRI_CARD = 7;
	//	private static final int EGG_NIGIRI_CARD = 8;
	//	private static final int PUDDING_CARD = 9;
	//	private static final int WASABI_CARD = 10;
	//	private static final int CHOPSTICKS_CARD = 11;
	private static void initializeMaps() {
		constToName = new HashMap<>();
		constToName.put(TEMPURA_CARD, "tempura");
		constToName.put(SASHIMI_CARD, "sashimi");
		constToName.put(DUMPLING_CARD, "dumpling");
		constToName.put(TWO_MAKI_CARD, "two-maki");
		constToName.put(THREE_MAKI_CARD, "three-maki");
		constToName.put(ONE_MAKI_CARD, "one-maki");
		constToName.put(SALMON_NIGIRI_CARD, "salmon-nigiri");
		constToName.put(SQUID_NIGIRI_CARD, "squid-nigiri");
		constToName.put(EGG_NIGIRI_CARD, "egg-nigiri");
		constToName.put(PUDDING_CARD, "pudding");
		constToName.put(WASABI_CARD, "wasabi");
		constToName.put(CHOPSTICKS_CARD, "chopsticks");

		nameToConst = new HashMap<>();
		nameToConst.put("tempura", TEMPURA_CARD);
		nameToConst.put("sashimi", SASHIMI_CARD);
		nameToConst.put("dumpling", DUMPLING_CARD);
		nameToConst.put("two-maki", TWO_MAKI_CARD);
		nameToConst.put("three-maki", THREE_MAKI_CARD);
		nameToConst.put("one-maki", ONE_MAKI_CARD);
		nameToConst.put("salmon-nigiri", SALMON_NIGIRI_CARD);
		nameToConst.put("squid-nigiri", SQUID_NIGIRI_CARD);
		nameToConst.put("egg-nigiri", EGG_NIGIRI_CARD);
		nameToConst.put("pudding", PUDDING_CARD);
		nameToConst.put("wasabi", WASABI_CARD);
		nameToConst.put("chopsticks", CHOPSTICKS_CARD);

		//		System.out.println("constToName map:");
		//		for (Map.Entry<Integer, String> entry: constToName.entrySet()) {
		//			System.out.println("const key: " + Integer.toString(entry.getKey()) + " value: " + entry.getValue());
		//		}
		//		
		//		System.out.println("nameToConst map:");
		//		for (Map.Entry<String, Integer> entry: nameToConst.entrySet()) {
		//			System.out.println("name Key: " + entry.getKey() + " value: " + entry.getValue());
		//		}
	}

	public static void main(String[] args) {
		initializeConstants();
		//Eventually, need to add a console prompt asking the user how many players are playing.
		initializePlayers(2);
		initializeMaps();

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
						ArrayList<Integer> currentSelection= players.get(i).getSelectedCards();
						for (int j = 0; j < currentSelection.size(); j++) {
							System.out.println(constToName.get(currentSelection.get(j)));
						}	
						System.out.println("\n");
					}
					System.out.println("Here is your current hand: ");
					for (int s : currPlayer.getHand()) {
						System.out.println(constToName.get(s));
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
						ScoreAction optimalScoreAction = Vmaxmin(copyPlayers, INIT_DEPTH, AGENT_ID, 
								Integer.MIN_VALUE, Integer.MAX_VALUE);
						System.out.println("number states explored: " + Integer.toString(globalAlphaBetaCounter));
						System.out.println("number of actions selected is: " + Integer.toString(optimalScoreAction.actions.size()));
						ArrayList<Integer> currPlayerHand = currPlayer.getCardsInHand();
						for (int numAct = 0; numAct < optimalScoreAction.actions.size(); numAct++) {
							int optActionCard = optimalScoreAction.actions.get(numAct);
							handleHandUpdates(currPlayer, optActionCard, (numAct == 1));
						}
						currPlayer.updateHand(currPlayerHand);
					} else {
						for (int playerCard = 0; playerCard < numCardSelections; playerCard++) {
							int currCard = handlePlayerCardSelection(currPlayer, (playerCard == 1) ? true : false);
							if (currCard != -1)
								handleHandUpdates(currPlayer, currCard, (playerCard == 1) ? true : false);
						}
					}
					//					try {
					//						Thread.sleep(2000);
					//					} catch (InterruptedException e) {
					//						e.printStackTrace();
					//					}
				}

				if (roundHasEnded) break;

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
		handlePuddingScore(players);
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player " + (i + 1) + "'s final score is: " + Integer.toString(players.get(i).getTotalPoints()));
		}
	}


	private static void initializeConstants() {
		for (int i = 0; i < TEMPURA; i++)
			deck.add(TEMPURA_CARD);
		for (int i = 0; i < SASHIMI; i++)
			deck.add(SASHIMI_CARD);
		for (int i = 0; i < DUMPLING; i++)
			deck.add(DUMPLING_CARD);
		for (int i =0; i < TWO_MAKI; i++)
			deck.add(TWO_MAKI_CARD);
		for (int i = 0; i < THREE_MAKI; i++)
			deck.add(THREE_MAKI_CARD);
		for (int i = 0; i < ONE_MAKI; i++)
			deck.add(ONE_MAKI_CARD);
		for (int i = 0;i < SALMON_NIGIRI; i++)
			deck.add(SALMON_NIGIRI_CARD);
		for (int i = 0; i < SQUID_NIGIRI; i++)
			deck.add(SQUID_NIGIRI_CARD);
		for (int i = 0; i < EGG_NIGIRI; i++)
			deck.add(EGG_NIGIRI_CARD);
		for (int i = 0; i < PUDDING; i++)
			deck.add(PUDDING_CARD);
		for (int i =0; i < WASABI; i++)
			deck.add(WASABI_CARD);
		for (int i = 0; i < CHOPSTICKS; i++)
			deck.add(CHOPSTICKS_CARD);
	}

	private static void initializePlayers(int numPlayers) {
		/*For simple version, we will assume 2 players*/
		Player player1 = new Player();
		Player player2 = new Player();

		//		players.add(player1);
		//		players.add(player2);
		//		ArrayList<String> player1hand = new ArrayList<String>() {{
		//			add("salmon-nigiri");
		//			add("salmon-nigiri");
		//			add("salmon-nigiri");
		//		}};
		//		player1.updateHand(player1hand);
		//		ArrayList<String> player2hand = new ArrayList<String>() {{
		//			add("salmon-nigiri");
		//			add("salmon-nigiri");
		//			add("squid-nigiri");
		//		}};
		//		player1.updateHand(player1hand);
		//		player2.updateHand(player2hand);
		player1.updateHand(getCardHand(NUM_TWO_PLAYER_CARDS));
		player2.updateHand(getCardHand(NUM_TWO_PLAYER_CARDS));

		players.add(player1);
		players.add(player2);

		//		ArrayList<String> start1 = new ArrayList<String>();
		//		start1.add("squid-nigiri");
		//		start1.add("squid-nigiri");
		//		start1.add("squid-nigiri");
		//		start1.add("salmon-nigiri");
		//
		//		ArrayList<String> start2 = new ArrayList<String>();
		//		start2.add("salmon-nigiri");
		//		start2.add("salmon-nigiri");
		//		start2.add("salmon-nigiri");
		//		start2.add("egg-nigiri");
		//
		//		player1.updateHand(start1);
		//		player2.updateHand(start2);

	}

	private static ArrayList<Integer> getCardHand (int numCards) {
		Random rand = new Random();
		ArrayList<Integer> currHand = new ArrayList<Integer>();
		for (int i = 0; i < numCards; i++) {
			int deckInd = rand.nextInt(deck.size());
			int currCard = deck.remove(deckInd);
			currHand.add(currCard);
		}
		return currHand;
	}

	private static void countScoreInHand(ArrayList<Integer> currHand, Player currPlayer) {
		int currScore = 0;
		//		int numWasabi = 0;
		//		int numTempura = 0;
		//		int numSashimi = 0;
		//		int numDumplings = 0;
		//		int numMaki = 0;
		int numPudding = 0;
		for (int card: currHand) {
			//			if(card == TEMPURA_CARD)
			//				numTempura++;
			//			else if (card == WASABI_CARD)
			//				numWasabi++;
			//			else if (card == DUMPLING_CARD)
			//				numDumplings++;
			//			else if (card == SASHIMI_CARD)
			//				numSashimi++;
			//			else if (card == ONE_MAKI_CARD)
			//				numMaki++;
			//			else if (card == TWO_MAKI_CARD)
			//				numMaki += 2;
			//			else if (card == THREE_MAKI_CARD)
			//				numMaki += 3;
			if (card == PUDDING_CARD)
				numPudding++;
			//			else if (card == SALMON_NIGIRI_CARD) {
			//				if (numWasabi > 0) {
			//					currScore += 6;
			//					numWasabi--;
			//				} else {
			//					currScore += 2;
			//				}
			//			}
			//			else if (card == SQUID_NIGIRI_CARD) {
			//				if (numWasabi > 0) {
			//					currScore += 9;
			//					numWasabi--;
			//				} else {
			//					currScore += 3;
			//				}
			//			}
			//			else if (card == EGG_NIGIRI_CARD) {
			//				if (numWasabi > 0) {
			//					currScore += 3;
			//					numWasabi--;
			//				} else {
			//					currScore += 1;
			//				}
			//			}	
			//		}
			//
			//		currScore += ((numTempura / 2) * 5);
			//		currScore += ((numSashimi / 3) * 10);
			//		currScore += calcDumplingScore(numDumplings);
		}

		//update number maki and number pudding and total score
		//		currPlayer.setNumMaki(numMaki);
		currPlayer.updateNumPuddings(numPudding);
		currScore = getScore(currHand, currPlayer, players); //TODO why is this function used here
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
	private static int getScore(ArrayList<Integer> currHand, Player currPlayer, ArrayList<Player> allPlayers) {
		int currScore = 0;
		int numWasabi = 0;
		int numTempura = 0;
		int numSashimi = 0;
		int numDumplings = 0;
		int numMaki = 0;
		int numPudding = 0;
		for (int card: currHand) {
			if(card == TEMPURA_CARD)
				numTempura++;
			else if (card == WASABI_CARD)
				numWasabi++;
			else if (card == DUMPLING_CARD)
				numDumplings++;
			else if (card == SASHIMI_CARD)
				numSashimi++;
			else if (card == ONE_MAKI_CARD)
				numMaki++;
			else if (card == TWO_MAKI_CARD)
				numMaki += 2;
			else if (card == THREE_MAKI_CARD)
				numMaki += 3;
			else if (card == PUDDING_CARD)
				numPudding++;
			else if (card == SALMON_NIGIRI_CARD) {
				if (numWasabi > 0) {
					currScore += 6;
					numWasabi--;
				} else {
					currScore += 2;
				}
			}
			else if (card == SQUID_NIGIRI_CARD) {
				if (numWasabi > 0) {
					currScore += 9;
					numWasabi--;
				} else {
					currScore += 3;
				}
			}
			else if (card == EGG_NIGIRI_CARD) {
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
		currScore += makiScore(currPlayer, allPlayers);
		//TODO add boolean to add pudding score similarly to the makiScore function
		return currScore;
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
		//		System.out.println("Maki Score player: " + currPlayer + " " + Integer.toString(score));
		return score;
	}


	//TODO: format like maki score
	private static void handlePuddingScore(ArrayList<Player> players) {
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

	private static void updateHandAndCards (Player player, int input) {
		// update player's selected cards
		player.updateCards(input);

		// remove from hand
		ArrayList<Integer> cardsInHand = player.getCardsInHand();
		cardsInHand.remove(Integer.valueOf(input));
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
			ArrayList<Integer> possibleActions = agent.getCardsInHand();
			boolean chopsticksExplore = true;
			Set<Integer> seenActions = new HashSet<Integer>();
			for (int s = 0; s < possibleActions.size(); s++) {
				int currCard = possibleActions.get(s);
				if (seenActions.contains(currCard)) {
					continue;
				}
				seenActions.add(currCard);
				Player copyPlayer = new Player(agent);
				ArrayList<Integer> handCards = copyPlayer.getCardsInHand();
				if (currCard == CHOPSTICKS_CARD) {
					copyPlayer.incrementNumChopsticks();
				} else if (currCard == ONE_MAKI_CARD) {
					copyPlayer.updateNumMaki(1);
				} else if (currCard == TWO_MAKI_CARD) {
					copyPlayer.updateNumMaki(2);
				} else if (currCard == THREE_MAKI_CARD) {
					copyPlayer.updateNumMaki(3);
				}
				copyPlayer.updateSelectedCards(currCard);
				copyPlayer.removeHandCard(currCard);

				//form player array for next iteration
				ArrayList<Player> currChoicePlayers = new ArrayList<Player>();
				for (int p = 0; p < allPlayers.size(); p++) {
					if (p == agentIndex) {
						currChoicePlayers.add(copyPlayer);
						//						System.out.println("selected cards are: " + String.join(",", copyPlayer.getSelectedCards()));
					} else {
						Player other = new Player(allPlayers.get(p));
						currChoicePlayers.add(other);
					}
				}
				if (nextAgentIndex == 0) {
					//					ArrayList<String> temp = currChoicePlayers.get(0).getHand();
					//					currChoicePlayers.get(0).updateHand(currChoicePlayers.get(1).getHand());
					//					currChoicePlayers.get(1).updateHand(temp);
					currChoicePlayers = rotateHandCards(currChoicePlayers);
				}
				//				System.out.println("new hand of agent " + Integer.toString(agentIndex) + ": " + currChoicePlayers.get(agentIndex).getHand());
				ScoreAction recurseScoreAction = Vmaxmin(currChoicePlayers, nextDepth, nextAgentIndex, 
						alpha, beta);
				ScoreAction currScoreAction = new ScoreAction();
				currScoreAction.actions.add(currCard);
				currScoreAction.score = recurseScoreAction.score;
				if (agentIndex == 0) {
					if (depth == INIT_DEPTH) {
						//						System.out.println("action: " + constToName.get(currCard) + "has point value: " + Integer.toString(currScoreAction.score));
						//						System.out.println("");
					}
					//					if (depth == INIT_DEPTH)
					//						System.out.println("action: " + currCard + "has point value: " + Integer.toString(currScoreAction.score));
					if (recurseScoreAction.score > bestScore) {
						bestScore = recurseScoreAction.score;
						optimalActionScore = currScoreAction;
					}
					if (bestScore > alpha) {
						alpha = bestScore;
					}
					if (beta <= alpha) {
						//						chopsticksExplore = false;
						break;
					}

				} else {
					//					if (depth == 1)
					//						System.out.println("possible min action1: " + currCard + " has AI point value: " + Integer.toString(currScoreAction.score));
					if (depth == INIT_DEPTH)
						System.out.println("possible min action: " + constToName.get(currCard) + " has value: " + Integer.toString(currScoreAction.score));
					if (recurseScoreAction.score < worstScore) {
						worstScore = recurseScoreAction.score;
						optimalActionScore = currScoreAction;
					}

					if (worstScore < beta) {
						beta = worstScore;
					}
					if (beta <= alpha) {
						//						chopsticksExplore = false;
						break;
					}
				}
			}

			//			if (chopsticksExplore && (agent.getNumChopsticks() > 0)) {
			//				for (int i = 0; i < agent.getCardsInHand().size()-1; i++) {
			//					for (int j = i+1; j < agent.getCardsInHand().size(); j++) {
			//						Player copyPlayer = new Player(agent);
			//						String firstCard = copyPlayer.getCardsInHand().get(i);
			//						String secondCard = copyPlayer.getCardsInHand().get(j);
			//						if (firstCard.equals("chopsticks")) {
			//							copyPlayer.incrementNumChopsticks();
			//						} else if (firstCard.equals("one-maki")) {
			//							copyPlayer.updateNumMaki(1);
			//						} else if (firstCard.equals("two-maki")) {
			//							copyPlayer.updateNumMaki(2);
			//						} else if (firstCard.equals("three-maki")) {
			//							copyPlayer.updateNumMaki(3);
			//						}
			//						
			//						if (secondCard.equals("chopsticks")) {
			//							copyPlayer.incrementNumChopsticks();
			//						} else if (secondCard.equals("one-maki")) {
			//							copyPlayer.updateNumMaki(1);
			//						} else if (secondCard.equals("two-maki")) {
			//							copyPlayer.updateNumMaki(2);
			//						} else if (secondCard.equals("three-maki")) {
			//							copyPlayer.updateNumMaki(3);
			//						}
			//						copyPlayer.updateSelectedCards(firstCard);
			//						copyPlayer.removeSelectedCards(firstCard);
			//						
			//						copyPlayer.updateSelectedCards(secondCard);
			//						copyPlayer.removeSelectedCards(secondCard);
			//						copyPlayer.moveChopsticksToHand();
			//						
			//						//form player array for next iteration
			//						ArrayList<Player> currChoicePlayers = new ArrayList<Player>();
			//						for (int p = 0; p < allPlayers.size(); p++) {
			//							if (p == agentIndex)
			//								currChoicePlayers.add(copyPlayer);
			//							else
			//								currChoicePlayers.add(allPlayers.get(p));
			//						}
			//						if (nextAgentIndex == 0)
			//							currChoicePlayers = rotateHandCards(currChoicePlayers);
			//						ScoreAction recurseScoreAction = Vmaxmin(currChoicePlayers, nextDepth, nextAgentIndex,
			//																  alpha, beta);
			//						ScoreAction currScoreAction = new ScoreAction();
			//						currScoreAction.actions.add(firstCard);
			//						currScoreAction.actions.add(secondCard);
			//						currScoreAction.score = recurseScoreAction.score;
			//						if (agentIndex == 0) {
			//							if (recurseScoreAction.score > bestScore) {
			//								bestScore = recurseScoreAction.score;
			//								optimalActionScore = currScoreAction;
			//							}
			//							if (recurseScoreAction.score > alpha)
			//								alpha = recurseScoreAction.score;
			//							if (beta <= alpha)
			//								break;
			//							
			//						} else {
			//							if (recurseScoreAction.score < worstScore) {
			//								worstScore = recurseScoreAction.score;
			//								optimalActionScore = currScoreAction;
			//							}
			//							if (recurseScoreAction.score < worstScore) 
			//								beta = recurseScoreAction.score;
			//							if (beta <= alpha) 
			//								break;
			//						}
			//					}
			//				}
			//				
			//			}

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
			//				if (agentIndex == 1 && depth == (INIT_DEPTH))
			//					System.out.println("min action is: " + optimalActionScore.actions.get(0) + 
			//							" with value: " + Integer.toString(optimalActionScore.score) + " for depth: " +
			//							Integer.toString(depth));
			//			System.out.println("num Vmaxmin so far: " + Integer.toString(globalAlphaBetaCounter));
			globalAlphaBetaCounter++;
			//			if (agentIndex == AGENT_ID && optimalActionScore.actions.contains("one-maki")) {
			//				String changedMakiAction = null;
			//				if (agent.getCardsInHand().contains("three-maki")) {
			//					changedMakiAction = "three-maki";
			//				} else if (agent.getCardsInHand().contains("two-maki"))
			//					changedMakiAction = "two-maki";
			//				optimalActionScore.actions.remove("one-maki");
			//				optimalActionScore.actions.add(changedMakiAction);
			//			}
			//			System.out.println("finished checking hand");
			return optimalActionScore;
		}
	}

	private static int evaluationFunction(ArrayList <Player> copyPlayers) {
		// update hand based on action
		Player AI = copyPlayers.get(AGENT_ID);
		ArrayList <Integer> ai_cards = AI.getSelectedCards();

		// calculate score of AI player's hand

		Player humanPlayer = copyPlayers.get(1);
		int AIscore = getScore(ai_cards, AI, copyPlayers);
		int humanScore = getScore(humanPlayer.getSelectedCards(), humanPlayer, copyPlayers);

		//		handlePuddingScore(copyPlayers);

		//		System.out.println("AI hand is: " + String.join(",", AI.getSelectedCards()));
		//		System.out.println("AI score is: " + Integer.toString(AI.getRoundPoints()));
		//		System.out.println("human hand is: " + String.join(",", humanPlayer.getSelectedCards()));
		//		System.out.println("human score is: " + Integer.toString(humanScore));
		//		handlePuddingScore(copyPlayers);

		//		System.out.println("AI hand is: " + String.join(",", AI.getSelectedCards()));
		//		System.out.println("AI score is: " + Integer.toString(AIscore));
		//		System.out.println("human hand is: " + String.join(",", humanPlayer.getSelectedCards()));
		//		System.out.println("human score is: " + Integer.toString(humanScore));
		assert AI.getSelectedCards().size() == humanPlayer.getSelectedCards().size();
		//		return AIscore;
		//		System.out.println("human score: " + Integer.toString(humanScore) + " AI score: " + Integer.toString(AIscore));
		return AIscore - humanScore;
	}

	private static int handlePlayerCardSelection(Player player, boolean selectingSecondCard) {
		if (!selectingSecondCard) {
			System.out.println("Choose a card to keep by typing its name here: ");
		} else {
			System.out.println("You have chopsticks! Type the name of card you would like, or type \"no\" "
					+ "if you don't want to use chopsticks yet: ");
		}
		while (true) {
			String currInput = scanner.nextLine().toLowerCase();
			if (selectingSecondCard && currInput.equals("no"))
				return -1;
			int inputConstVal = -1;
			if (nameToConst.containsKey(currInput))
				inputConstVal = nameToConst.get(currInput);
			if (player.getHand().contains(inputConstVal)) {
				return inputConstVal;
			} else {
				if (!selectingSecondCard) 
					System.out.println("That card isn't in the current hand. Please enter again: ");
				else
					System.out.println("That card isn't in the current hand. Please enter again (or type \"no\"): ");
			}
		}
	}

	private static void handleHandUpdates (Player player, int cardToKeep, boolean selectingSecondCard) {
		ArrayList<Integer> newHand = player.getHand();
		player.updateSelectedCards(cardToKeep);
		if (cardToKeep == CHOPSTICKS_CARD) {
			player.incrementNumChopsticks();
		} else if (cardToKeep == ONE_MAKI_CARD) {
			player.updateNumMaki(1);
		} else if (cardToKeep  == TWO_MAKI_CARD) {
			player.updateNumMaki(2);
		} else if (cardToKeep  == THREE_MAKI_CARD) {
			player.updateNumMaki(3);
		}
		newHand.remove(Integer.valueOf(cardToKeep));
		if (selectingSecondCard) //using chopsticks
			player.moveChopsticksToHand();
		System.out.println("\n");
		System.out.println("You have chosen to keep the following card: " + constToName.get(cardToKeep));
		System.out.println("\n");

		player.updateHand(newHand);
		System.out.println("================");
		System.out.println("NEW HAND IS: ");
		for (int n = 0; n < newHand.size(); n++) {
			System.out.println(constToName.get(newHand.get(n)));
		}
		System.out.println("================");
	}

	private static ArrayList<Player> rotateHandCards(ArrayList<Player> playersList) {
		ArrayList<Integer> lastPlayerHand = playersList.get(playersList.size() - 1).getHand();
		for (int p = playersList.size()-1; p >= 1; p--) {
			Player currPlayer = playersList.get(p);
			Player previous = playersList.get(p-1);
			ArrayList<Integer> previousHand = previous.getHand();
			currPlayer.updateHand(previousHand);
		}
		playersList.get(0).updateHand(lastPlayerHand);
		return playersList;
	}
}