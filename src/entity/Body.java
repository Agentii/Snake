package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static main.Game.ENTITY_SIZE;
import static main.Main.gameRoot;
import static main.Game.xDir;
import static main.Game.yDir;

public class Body extends Rectangle {
		
	public Body next;
	
	public Body(double x, double y) {
		super(x, y, ENTITY_SIZE, ENTITY_SIZE);
		
		setFill(Color.LIGHTGREEN);
		setViewOrder(1);
		gameRoot.getChildren().add(this);
	}
	
	public void update() {
		setX(getX() + xDir * ENTITY_SIZE);
		setY(getY() + yDir * ENTITY_SIZE);

	}

}
