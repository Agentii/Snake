package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Game;

import static main.Game.ENTITY_SIZE;

public class Body extends Rectangle {
		
	public Body next;
	
	public Body(double x, double y) {
		super(x, y, ENTITY_SIZE, ENTITY_SIZE);
		
		setFill(Color.LIGHTGREEN);
		setViewOrder(1);
		Game.gameRoot.getChildren().add(this);
	}
	
	public void update() {
		setX(getX() + Game.xDir * ENTITY_SIZE);
		setY(getY() + Game.yDir * ENTITY_SIZE);
	}

}
