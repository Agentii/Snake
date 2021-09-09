package main;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import window.Frame;

public class Main extends Application {
	
	public static final int SCENE_WIDTH = Game.GAME_WIDTH + Sidebar.SIDEBAR_WIDTH + Frame.DFLT_WIDTH;
	public static final int SCENE_HEIGHT = Game.GAME_HEIGHT + Frame.DFLT_HEIGHT;

	public static Stage stage;
	public static Frame root;
	public static Scene scene;
	
	public static Game game;
	public static Sidebar sidebar;
	
	Media media = new Media(new File(System.getProperty("user.dir") + "/src/files/media/beatsburg_freehousetypebeat.mp3").toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(media);
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		game = new Game();
		sidebar = new Sidebar();
		initStage();
		playMusic();

		game.start();
	}
	
	private void initStage() {
		stage.setTitle("It's just snake...");
		stage.getIcons().add(new Image("files/images/Snake icon.png"));
		
		root = new Frame(stage);
		root.setCenter(game);
		root.setRight(sidebar);
		
		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		scene.setFill(Color.TRANSPARENT);
		
		stage.setScene(scene);
	}
	
	private void playMusic() {
		mediaPlayer.setVolume(0.04);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();
	}

	public static void exit() {
		Platform.exit();
	}
}

