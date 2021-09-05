package main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Topbar extends AnchorPane {
	
	public static final int TOPBAR_WIDTH = Game.GAME_WIDTH + Sidebar.SIDEBAR_WIDTH;
	public static final int TOPBAR_HEIGHT = 30;
	
	public double offsetX, offsetY;

	public Topbar() {
		initTopbar();
	}

	private void initTopbar() {
		setStyle("-fx-background-radius: 10 10 0 0;"
				+"-fx-background-color: #0d3040;");
		setMinSize(TOPBAR_WIDTH, TOPBAR_HEIGHT);
		setMaxSize(TOPBAR_WIDTH, TOPBAR_HEIGHT);
		
		// Name and logo
		HBox nameAndLogo = new HBox(10);
		nameAndLogo.setPrefHeight(TOPBAR_HEIGHT);
		nameAndLogo.setAlignment(Pos.CENTER_LEFT);
		
		Label name = new Label("It's just snake...");
		name.setStyle("-fx-padding: 4 0 0 0;"
					+ "-fx-font-family: Verdana;"
					+ "-fx-font-size: 12");
		name.setTextFill(Color.LIGHTGRAY);
		
		ImageView logo = new ImageView(new Image("files/images/Snake icon.png", TOPBAR_HEIGHT - 5, TOPBAR_HEIGHT - 5, false, true));
		
		nameAndLogo.getChildren().addAll(logo, name);
		
		// Minimize and close
		HBox minimizeAndClose = new HBox(10);
		minimizeAndClose.setPrefHeight(TOPBAR_HEIGHT);
		minimizeAndClose.setAlignment(Pos.CENTER_LEFT);
		
		TopbarButton minimize = new TopbarButton("-");
		minimize.setOnAction(e -> Main.stage.setIconified(true));
		
		TopbarButton close = new TopbarButton("x");
		close.setOnAction(e -> Main.exit());
		
		minimizeAndClose.getChildren().addAll(minimize, close);
		
		// Setup AnchorPane
		getChildren().addAll(nameAndLogo, minimizeAndClose);
		AnchorPane.setLeftAnchor(nameAndLogo, 5.);
		AnchorPane.setRightAnchor(minimizeAndClose, 5.);
	}
	
	private class TopbarButton extends Button {
		
		private TopbarButton(String text) {
			super(text);
			setStyle("-fx-cursor: hand;"
				   + "-fx-font-family: Verdana;"
				   + "-fx-font-weight: bold;"
				   + "-fx-background-color: "
				   + "        linear-gradient(#3e4c4a, #19263e),"
				   + "        linear-gradient(#19263e, #263e75);"
				   + "-fx-background-radius: 4 4 4 4;"
				   + "-fx-text-fill: white;"
				   + "-fx-font-size: 9px;");
			setMinSize(20, 20);
			setPrefSize(20, 20);
			setFocusTraversable(false);
		}
	}
}
