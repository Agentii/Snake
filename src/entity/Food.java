package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.Game;
import main.Main;

import java.util.Random;

import static main.Game.ENTITY_SIZE;

public class Food extends Circle {

	private Random rand = new Random();
	
	public Food() {
		super(0, 0, ENTITY_SIZE >> 1);
		setFill(Color.RED);
		Main.game.getChildren().add(this);
	}

	public void update() {
		setCenterX(rand.nextInt(Game.GAME_WIDTH / ENTITY_SIZE) * ENTITY_SIZE + (ENTITY_SIZE >> 1));
		setCenterY(rand.nextInt(Game.GAME_HEIGHT / ENTITY_SIZE) * ENTITY_SIZE + (ENTITY_SIZE >> 1));		
	}

	public double getX() {
		return getCenterX() - (ENTITY_SIZE >> 1);
	}

	public double getY() {
		return getCenterY() - (ENTITY_SIZE >> 1);
	}

}
