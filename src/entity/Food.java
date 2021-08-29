package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Game;

import java.util.Random;

import static main.Game.ENTITY_SIZE;

public class Food extends Rectangle {

	private Random rand = new Random();
	
	public Food(int x, int y) {
		super(x, y, ENTITY_SIZE, ENTITY_SIZE);
		this.setFocused(true);
		setFill(Color.RED);
		Game.gameRoot.getChildren().add(this);
	}

	public void update() {
		setX(rand.nextInt(Game.GAME_WIDTH / ENTITY_SIZE) * ENTITY_SIZE);
		setY(rand.nextInt(Game.GAME_HEIGHT / ENTITY_SIZE) * ENTITY_SIZE);		
	}

}
