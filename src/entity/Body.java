package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import main.Game;

import static main.Game.ENTITY_SIZE;

public class Body extends Rectangle {
		
	public Body next;
	public Rotate rotate;
	
	public Body(double x, double y) {
		super(x, y, ENTITY_SIZE, ENTITY_SIZE);
		setFill(Color.LIGHTGREEN);
		setViewOrder(1);
		rotate = new Rotate();
		getTransforms().add(rotate);
		Game.gameRoot.getChildren().add(this);
	}
	
	public void update() {
		setPosition(getX() + Game.xDir * ENTITY_SIZE, getY() + Game.yDir * ENTITY_SIZE);
		rotate.setAngle((Game.xDir + Game.yDir + Math.abs(Game.yDir)) * 90);
	}
	
	public void setPosition(double x, double y) {
		setX(x);
		setY(y);
		rotate.setPivotX(getX() + ENTITY_SIZE / 2);
		rotate.setPivotY(getY() + ENTITY_SIZE / 2);
	}

}
