package snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static main.Game.gameRoot;
import static main.Game.xDir;
import static main.Game.yDir;

public class Body {
	
	public static final int SIZE = 20;
	
	public Rectangle rect;
	public int x, y;
	public Body next;
	
	public Body(int x, int y) {
		this.x = x; this.y = y;
		
		this.rect = new Rectangle(x, y, SIZE, SIZE);
		this.rect.setFill(Color.LIGHTGREEN);
		gameRoot.getChildren().add(rect);
	}
	
	public void update() {
		x += xDir*SIZE;
		y += yDir*SIZE;
		rect.setX(x);
		rect.setY(y);

	}

}
