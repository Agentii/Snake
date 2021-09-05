package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Sidebar extends AnchorPane {
	
	public static final int SIDEBAR_WIDTH = 100;
	public static final int SIDEBAR_HEIGHT = Game.GAME_HEIGHT;
	
//	public static AnchorPane sidebarRoot = new AnchorPane();
	private static Label highscore, score;
	private static Text gameOver;
	
	public Sidebar() {
		initSidebar();
	}
	
	private void initSidebar() {
		setStyle("-fx-background-radius: 0 0 10 0;"
							+"-fx-background-color: #0d3329;");
		setMinSize(SIDEBAR_WIDTH, SIDEBAR_HEIGHT);
		setMaxSize(SIDEBAR_WIDTH, SIDEBAR_HEIGHT);

		// Scoreboard
		VBox scoreboard = new SidebarVBox(10, Pos.TOP_CENTER);	

		highscore = new SidebarLabel(String.format("HIGHSCORE\n%d", 0));
		score = new SidebarLabel(String.format("SCORE\n%d", 0));
		
		// Game Over text
		gameOver = new Text("\n\n\n\nGame\nOver");
		gameOver.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gameOver.setTextAlignment(TextAlignment.CENTER);
		gameOver.setFill(Color.RED);
		gameOver.setVisible(false);
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.color(0.8, 0.2, 0.2));
		shadow.setRadius(30);
		shadow.setSpread(0.3);
		gameOver.setEffect(shadow);
		
		scoreboard.getChildren().addAll(highscore, score, gameOver);
		
		// Restart and quit (options)
		VBox options = new SidebarVBox(10, Pos.BOTTOM_CENTER);
		
		Button restart = new SidebarButton("Restart");
		restart.setOnAction(e -> Main.game.restart());
		
		Button quit = new SidebarButton("Quit");
		quit.setOnAction(e -> Main.exit());
		
		options.getChildren().addAll(restart, quit);
		
		// Add to root
		getChildren().addAll(scoreboard, options);
		AnchorPane.setBottomAnchor(options, 0.);
		
	}
	
	private class SidebarLabel extends Label {
		
		private SidebarLabel(String text) {
			super(text);
			setAlignment(Pos.CENTER);
			setTextAlignment(TextAlignment.CENTER);
			setPrefWidth(90);
			setTextFill(Color.WHITE);
			setStyle("-fx-background-color: #19262e; -fx-background-radius: 10 10 10 10;");
		}
	}
	
	private class SidebarButton extends Button {
		
		private SidebarButton(String text) {
			super(text);
			setStyle("-fx-cursor: hand");
			setFont(Font.font("Verdana", FontWeight.BOLD, 11));
			setPrefWidth(80);
			setFocusTraversable(false);
		}
	}
	
	private class SidebarVBox extends VBox {
		
		private SidebarVBox(double spacing, Pos alignment) {
			super(spacing);
			setPrefSize(SIDEBAR_WIDTH, SIDEBAR_HEIGHT);
			setAlignment(alignment);
			setPadding(new Insets(15, 0, 15, 0));
			setStyle("-fx-background-color: transparent;");
		}
	}
	
	public static void updateScoreboard() {
		highscore.setText(String.format("HIGHSCORE\n%d", Game.highscore));
		score.setText(String.format("SCORE\n%d", Game.score));
	}
	
	public static void showGameOver(boolean visible) {
		gameOver.setVisible(visible);
	}
	
}
