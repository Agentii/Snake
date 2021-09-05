package main;

import controller.KeyboardInputs;
import controller.MouseInputs;
import entity.Head;
import entity.Body;
import entity.Food;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class Game extends Pane {

	public static final int GAME_WIDTH = 600, GAME_HEIGHT = 520;
	public static final int ENTITY_SIZE = 20;
	public static final int EASY = 40, MEDIUM = 70, HARD = 100;

	public static AnimationTimer gameloop;

	public static int xDir = 1, yDir = 1;

	private Head head;
	private Body neck, tail, tmpBody;
	private Food food;
	private int snakeSize = 2;

	public static int score, highscore;
	public static boolean gameOver;

	private List<Body> badBodyparts = new ArrayList<Body>();
	
	public Game() {
		setStyle("-fx-background-radius: 0 0 0 10;"
						+ "-fx-background-color: #19262e;");
		setMinSize(GAME_WIDTH, GAME_HEIGHT);
		setMaxSize(GAME_WIDTH, GAME_HEIGHT);
	}

	public void start() {
		initSnake();
		initFood();
		KeyboardInputs.readInputs();
		MouseInputs.readInputs();
		gameloop = new AnimationTimer() {
			@Override
			public void handle(long now) {				
				if (gameOver)
					return;
				KeyboardInputs.keyWait = false;

				updateSnake();
				checkWallCollision();
				checkFoodCollision();
				if (snakeSize > 4)
					checkSnakeCollision();
				
				try {
					Thread.sleep(MEDIUM);
				}
				catch (InterruptedException e) {
				}
			}			
		};
		Main.stage.show();

	}
	
	private void initSnake() {
		head = new Head(GAME_WIDTH >> 1, GAME_HEIGHT >> 1);
		neck = tail = new Body(head.getX(), head.getY());
		neck.next = tail.next = tail;
		snakeSize = 2;
		badBodyparts.clear();
	}
	
	private void initFood() {
		food = new Food();
		updateFood();
	}
	
	private void updateSnake() {
		tail.setPosition(head.getX(), head.getY());
		neck = tail;
		tail = tail.next;
		head.update();
	}
		
	private void updateFood() {
		food.update();
	}
	
	private void updateScore(int newScore) {
		score = newScore;
		if (score > highscore) {
			highscore = score;
		}
		try {
			Sidebar.updateScoreboard();
		} 
		catch (IOException e) {
		}		
	}
	
	private void addBody() {
		tmpBody = new Body(tail.getX(), tail.getY());
		neck.next = tmpBody;
		tmpBody.next = tail;
		tail = tmpBody;
		snakeSize++;
		if (snakeSize > 4 && (snakeSize & 1) == 1) {
			badBodyparts.add(tail);
		}
	}
	
	private void checkWallCollision() {
		if ((head.getX() + ENTITY_SIZE) % (GAME_WIDTH + ENTITY_SIZE) * ((head.getY() + ENTITY_SIZE) % (GAME_HEIGHT + ENTITY_SIZE)) == 0) {
			double newX = (head.getX() + GAME_WIDTH) % GAME_WIDTH - ENTITY_SIZE * xDir;
			double newY = (head.getY() + GAME_HEIGHT) % GAME_HEIGHT - ENTITY_SIZE * yDir;
			head.setPosition(newX, newY);
			head.update();
		}
	}
	
	private void checkFoodCollision() {
		if (head.getX() == food.getX() && head.getY() == food.getY()) {
			addBody();
			updateFood();
			updateScore(score+10);
		}
	}
	
	private void checkSnakeCollision() {
		Body body;
		for (int i=0; i < badBodyparts.size(); i++) {
			body = badBodyparts.get(i);
			if (head.getX() == body.getX() && head.getY() == body.getY())
				gameOver();
			badBodyparts.set(i, body.next);
		}
	}
	
	private void gameOver() {
		gameOver = true;
		Sidebar.showGameOver(true);
	}
	
	public void restart() {
		gameloop.stop();
		gameOver = false;
		Sidebar.showGameOver(false);
		updateScore(0);		
		getChildren().clear();
		start();
	}

}
