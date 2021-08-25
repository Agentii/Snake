package main;

import java.util.Random;

import controller.KeyboardInputs;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import snake.Body;

public class Game extends Application {
	
	public static int SCENE_WIDTH = 1000, SCENE_HEIGHT = 800;

	public static Stage stage;
	public static BorderPane layoutRoot = new BorderPane();
	public static Pane gameRoot = new Pane();

	public static Scene scene = new Scene(layoutRoot, SCENE_WIDTH, SCENE_HEIGHT);
	
	public static Body head, tail, tmp;
	public static Rectangle food;
	
	public static int xDir, yDir;
	
	public static Random rand = new Random();
	
	private AnimationTimer timer;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		initStage();
		initFood();
		initSnake();
		
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				KeyboardInputs.controlSnake();
				updateSnake();
				
				if (head.x == (int) food.getX() && head.y == (int) food.getY()) {
					addBody();
					updateFood();
				}
				
				try {
					Thread.sleep(70);
				}
				catch (InterruptedException e) {
				}
			}			
		};
		
		timer.start();

		stage.show();

	}
	
	private void initStage() {
		stage.setScene(scene);
		stage.setTitle("Snake");
		stage.setOnCloseRequest(e -> exit());
		stage.setResizable(false);

		scene.setFill(Color.color(0.1, 0.15, 0.18));

		layoutRoot.setCenter(gameRoot);
	}
	
	private void initFood() {
		food = new Rectangle(100, 160, 20, 20);
		food.setFill(Color.RED);
		gameRoot.getChildren().add(food);		
	}
	
	private void initSnake() {
		head = new Body(200, 200);
		tail = head;
		head.next = tail;
		tail.next = head;
	}
	
	private void updateSnake() {
		tail.x = head.x;
		tail.y = head.y;
		head = tail;
		tail = tail.next;
		head.update();
	}
		
	private void updateFood() {
		food.setX(rand.nextInt(SCENE_WIDTH / 20)*20);
		food.setY(rand.nextInt(SCENE_HEIGHT / 20)*20);
	}
	
	private void addBody() {
		tmp = new Body(tail.x, tail.y);
		head.next = tmp;
		tmp.next = tail;
		tail = tmp;
	}

	private void exit() {
		Platform.exit();
	}

}
