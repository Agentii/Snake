package controller;

import javafx.scene.input.KeyEvent;

import static main.Game.scene;
import static main.Game.xDir;
import static main.Game.yDir;

public class KeyboardInputs {
	
	public static void controlSnake() {
		// Event handler for key presses
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			switch (key.getCode()) {
			
			case UP:
				xDir = 0;
				yDir = -1;
				break;
			
			case DOWN:
				xDir = 0;
				yDir = 1;
				break;
				
			case LEFT:
				xDir = -1;
				yDir = 0;
				break;
				
			case RIGHT:
				xDir = 1;
				yDir = 0;
				break;
			
			default:
				break;
			}
		});
		
	}
}
