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
		System.out.println("INSIDE MAIN METHOD");
		initializeConstants();
		
		//Eventually, need to add a console prompt asking the user how many players are playing.
		initializePlayers(2);

		
		
		
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
	
	private static int countScoreInHand(ArrayList<String> currHand) {
		int currScore = 0;
		return currScore;
	}
}

