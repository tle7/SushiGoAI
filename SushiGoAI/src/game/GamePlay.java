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
	
	private ArrayList<String> deck = new ArrayList<String>();
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public void main() {
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
		
		
		/*For simple version, we will assume 2 players*/
		Player player1 = new Player();
		Player player2 = new Player();
		
		players.add(player1);
		players.add(player2);
		
	}
}
