package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Main;

import static main.Game.ENTITY_SIZE;

public class Body extends Rectangle {
		
	public Body next;
	
	public Body(double x, double y) {
		super(x, y, ENTITY_SIZE, ENTITY_SIZE);
		setFill(Color.LIGHTGREEN);
		setViewOrder(1);
		setStroke(Color.color(0.1, 0.15, 0.18));
		setStrokeWidth(0.1);
		
		Main.game.getChildren().add(this);
	}
	
	public void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}

}
