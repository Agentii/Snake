package controller;

import static main.Main.topbar;
import static main.Main.stage;

import javafx.scene.input.MouseEvent;


public class MouseInputs {
	
	public static void readInputs() {
		
		topbar.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			stage.setX(event.getScreenX() - topbar.offsetX);
			stage.setY(event.getScreenY() - topbar.offsetY);
		});
		
		topbar.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			topbar.offsetX =  event.getX();
			topbar.offsetY = event.getY();
		});
	}

}
