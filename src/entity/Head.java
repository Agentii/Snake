package entity;

import main.Game;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Rotate;

import static main.Game.ENTITY_SIZE;

public class Head extends Body {
	
	private ImagePattern eyes = new ImagePattern(new Image("Images/Snake head.png"));
	public Rotate rotate;

	public Head(double x, double y) {
		super(x, y);
		setFill(eyes);
		setViewOrder(0);
		rotate = new Rotate();
		getTransforms().add(rotate);
	}
	
	public void update() {
		setPosition(getX() + Game.xDir * ENTITY_SIZE, getY() + Game.yDir * ENTITY_SIZE);
		updatePivot();
	}
	
	private void updatePivot() {
		rotate.setPivotX(getX() + (ENTITY_SIZE >> 1));
		rotate.setPivotY(getY() + (ENTITY_SIZE >> 1));
		rotate.setAngle((Game.xDir + Game.yDir + Math.abs(Game.yDir)) * 90);
	}

}
