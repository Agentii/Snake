package main;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {
	
	private static final int SHADOW_SIZE = 10;
	public static final int SCENE_WIDTH = Game.GAME_WIDTH + Sidebar.SIDEBAR_WIDTH + 2*SHADOW_SIZE;
	public static final int SCENE_HEIGHT = Game.GAME_HEIGHT + Topbar.TOPBAR_HEIGHT + SHADOW_SIZE;

	public static Stage stage;
	public static BorderPane layoutRoot = new BorderPane();
	public static Scene scene = new Scene(layoutRoot, SCENE_WIDTH, SCENE_HEIGHT);
	
	public static Game game;
	public static Sidebar sidebar;
	public static Topbar topbar;
	
	Media media = new Media(new File(System.getProperty("user.dir") + "/src/files/media/beatsburg_freehousetypebeat.mp3").toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(media);
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();
		game = new Game();
		sidebar = new Sidebar();
		topbar = new Topbar();
		initStage();
		game.start();
	}
	
	private void initStage() {
		scene.setFill(Color.TRANSPARENT);
		
		stage.setScene(scene);
		stage.setTitle("It's just snake...");
		stage.getIcons().add(new Image("files/images/Snake icon.png"));
		stage.initStyle(StageStyle.TRANSPARENT);
		
		layoutRoot.setCenter(game);
		layoutRoot.setRight(sidebar);
		layoutRoot.setTop(topbar);
		layoutRoot.setPadding(new Insets(0, SHADOW_SIZE, SHADOW_SIZE, SHADOW_SIZE));
		layoutRoot.setStyle("-fx-background-color: transparent;"
							+String.format("-fx-effect: dropshadow(gaussian, black, %d, 0, 0, 2);", SHADOW_SIZE));
	}

	public static void exit() {
		Platform.exit();
	}
}

