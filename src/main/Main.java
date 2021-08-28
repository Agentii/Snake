package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static final int SCENE_WIDTH = 400, SCENE_HEIGHT = 400;

	public static Stage stage;
	public static BorderPane layoutRoot = new BorderPane();
	public static Pane gameRoot = new Pane();
	public static AnchorPane sidebarRoot = new AnchorPane();
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

		scene.setFill(Color.color(0.1, 0.15, 0.18));

		gameRoot.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
		sidebarRoot.setViewOrder(2);
		layoutRoot.setCenter(gameRoot);
		layoutRoot.setRight(sidebarRoot);
	}

	private void exit() {
		Platform.exit();
	}
	
	public static void restart() {
		game = new Game();
	}

}

