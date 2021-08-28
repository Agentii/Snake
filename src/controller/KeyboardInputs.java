package controller;

import javafx.scene.input.KeyEvent;
import main.Game;

import static main.Main.scene;
import static main.Game.xDir;
import static main.Game.yDir;

public class KeyboardInputs {
	
	public static boolean keyWait = false;  // To prevent 180-turns
	
	public static void controlSnake() {
		// Event handler for key presses
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			switch (key.getCode()) {
			
			case UP:
				Game.gameloop.start();
				if (Math.abs(xDir) == 1 && !keyWait) {
					xDir = 0;
					yDir = -1;
					keyWait = true;
				}
				break;
			
			case DOWN:
				Game.gameloop.start();
				if (Math.abs(xDir) == 1 && !keyWait) {
					xDir = 0;
					yDir = 1;
					keyWait = true;
				}
				break;
				
			case LEFT:
				Game.gameloop.start();
				if (Math.abs(yDir) == 1 && !keyWait) {
					xDir = -1;
					yDir = 0;	
					keyWait = true;
				}
				break;
				
			case RIGHT:
				Game.gameloop.start();
				if (Math.abs(yDir) == 1 && !keyWait) {
					xDir = 1;
					yDir = 0;
					keyWait = true;
				}
				break;
			
			case P:
			case ESCAPE:
				Game.gameloop.stop();
			
			default:
				break;
			}
		});
		
	}
}
