package main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static main.Main.sidebarRoot;

public class Sidebar {
	
	public Sidebar() {
		initSidebar();
	}
	
	private void initSidebar() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(0, 10, 10, 10));
		
		Label highscore = new Label("HIGH");
		Label score = new Label("SCORE");
		
		vbox.getChildren().addAll(highscore, score);
		
		Button restart = new Button("Restart");
		restart.setOnAction((event) -> Main.restart());
		
		sidebarRoot.getChildren().addAll(vbox, restart);
		AnchorPane.setBottomAnchor(restart, 8.0);
		AnchorPane.setRightAnchor(restart, 5.0);
		AnchorPane.setTopAnchor(vbox, 10.0);
	}
	
}
