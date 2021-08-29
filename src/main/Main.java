package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static final int SCENE_WIDTH = Game.GAME_WIDTH + Sidebar.SIDEBAR_WIDTH;
	public static final int SCENE_HEIGHT = 520;

	public static Stage stage;
	public static BorderPane layoutRoot = new BorderPane();
	public static VBox center = new VBox();
	public static VBox right = new VBox();
	public static Scene scene = new Scene(layoutRoot, SCENE_WIDTH, SCENE_HEIGHT);
	
	public static Game game;
	public static Sidebar sidebar;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		initStage();
		sidebar = new Sidebar();
		game = new Game();
	}
	
	private void initStage() {
		stage.setScene(scene);
		stage.setTitle("Snake");
		stage.getIcons().add(new Image("Images/Snake icon.png"));
		stage.setOnCloseRequest(e -> exit());
		stage.setResizable(false);

		layoutRoot.setBackground(new Background(new BackgroundFill(Color.color(0.1, 0.15, 0.18), null, null)));

		layoutRoot.setCenter(center);
		layoutRoot.setRight(right);
		center.getChildren().add(Game.gameRoot);
		right.getChildren().add(Sidebar.sidebarRoot);
	}

	public static void exit() {
		Platform.exit();
	}
	
	public static void restart() {
		Sidebar.showGameOver(false);
		Game.gameloop.stop();
		game = new Game();
	}

}

