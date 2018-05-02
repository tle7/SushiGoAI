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
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to SushiGoAI! \n Here is your hand: ");
		for (String s : players.get(0).getSelectedCards()) {
			System.out.println(s);
		}
		System.out.println("Choose a card to keep by typing its name here: ");
		String cardToKeep = scanner.nextLine().toLowerCase();
		//if (players.get(0))
		players.get(0).updateSelectedCards(cardToKeep);
		System.out.println("You have chosen to keep the following card: " + cardToKeep);
		
		
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
		for (String card: currHand) {
			
		}
	}
}

