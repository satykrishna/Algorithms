package java8.defaultmethod.client;

import java8.defaultmethod.impl.Game;
import java8.defaultmethod.impl.Monster;

public class App {

	public static void playGame() {
		Game game = new Game();
		game.drawShapes();
	}

	public static void moveMonster() {
		Monster monster = new Monster();
		monster.rotateBy(100);
		monster.moveHorizontally(10);
		monster.moveVertically(10);
	}
	
	public static void main(String[] args) {

		
	}

}
