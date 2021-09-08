package window;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;


public class Topbar extends AnchorPane {
	
	public static final int TOPBAR_HEIGHT = 30;
		
	private double offsetX, offsetY;
	
	protected Topbar() {
		initTopbar();
		readMouseInputs();
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

	private void initTopbar() {
		setStyle("-fx-background-radius: 10 10 0 0;"
				+"-fx-background-color: #0d3040;");
		setMinHeight(TOPBAR_HEIGHT);
		setMaxHeight(TOPBAR_HEIGHT);
		
		HBox nameAndLogo = getNameAndLogo();
		HBox minimizeAndClose = getMinimizeAndClose();

		getChildren().addAll(nameAndLogo, minimizeAndClose);
		AnchorPane.setLeftAnchor(nameAndLogo, 5.);
		AnchorPane.setRightAnchor(minimizeAndClose, 5.);
	}
	
	
	private HBox getNameAndLogo() {
		HBox nameAndLogo = new HBox(10);
		nameAndLogo.setPrefHeight(TOPBAR_HEIGHT);
		nameAndLogo.setAlignment(Pos.CENTER_LEFT);
		
		Label name = new Label(Frame.stage.getTitle());
		name.setStyle("-fx-padding: 4 0 0 0;"
					+ "-fx-font-family: Verdana;"
					+ "-fx-font-size: 12;"
					+ "-fx-text-fill: lightgray;");
		
		if (Frame.stage.getIcons().isEmpty())
			nameAndLogo.getChildren().add(name);
		else {
			String logoPath = Frame.stage.getIcons().get(0).getUrl();
			ImageView logo = new ImageView(new Image(logoPath, TOPBAR_HEIGHT - 5, TOPBAR_HEIGHT - 5, false, true));
			nameAndLogo.getChildren().addAll(logo, name);
		}
		
		return nameAndLogo;
	}
	
	
	private HBox getMinimizeAndClose() {
		HBox minimizeAndClose = new HBox(10);
		minimizeAndClose.setPrefHeight(TOPBAR_HEIGHT);
		minimizeAndClose.setAlignment(Pos.CENTER_LEFT);
		
		TopbarButton minimize = new TopbarButton("-");
		minimize.setOnAction(e -> Frame.stage.setIconified(true));
		
		TopbarButton close = new TopbarButton("x");
		close.setOnAction(e -> Frame.stage.close());
		
		minimizeAndClose.getChildren().addAll(minimize, close);
		
		return minimizeAndClose;		
	}
	
	
	
	private void readMouseInputs() {
		addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			Frame.stage.setX(event.getScreenX() - offsetX);
			Frame.stage.setY(event.getScreenY() - offsetY);
		});
		
		addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			offsetX =  event.getX();
			offsetY = event.getY();
		});
	}
}
