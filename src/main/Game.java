package main;

import controller.KeyboardInputs;
import entity.Body;
import entity.Food;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;


public class Game {
	
	public static Pane gameRoot = new Pane();
	
	public static final int GAME_WIDTH = 600, GAME_HEIGHT = Main.SCENE_HEIGHT;
	public static final int ENTITY_SIZE = 20;
	public static final int EASY = 40, MEDIUM = 70, HARD = 100;

	public static AnimationTimer gameloop;

	public static int xDir = 1, yDir = 1;

	private Body head, tail, tmpBody;
	private Food food;
	private int snakeSize = 1;

	public static int score, highscore;
	public static boolean gameOver;

	private List<Body> badBodyparts = new ArrayList<Body>();
	
	public Game() {
		gameOver = false;
		updateScore(0);		
		gameRoot.setMaxWidth(GAME_WIDTH);
		gameRoot.getChildren().clear();
		start();
	}

	private void start() {
		initSnake();
		initFood();
		KeyboardInputs.controlSnake();
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
	
	private void initFood() {
		food = new Food(0, 0);
		updateFood();
	}
	
	private void initSnake() {
		head = new Body(GAME_WIDTH / 2, GAME_HEIGHT / 2);
		tail = head;
		head.next = tail;
		tail.next = head;
	}
	
	private void updateSnake() {
		tail.setX(head.getX());
		tail.setY(head.getY());
		head = tail;
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
		Sidebar.updateScoreboard();		
	}
	
	private void addBody() {
		tmpBody = new Body(tail.getX(), tail.getY());
		head.next = tmpBody;
		tmpBody.next = tail;
		tail = tmpBody;
		snakeSize++;
		if (snakeSize > 4 && (snakeSize & 1) == 1) {
			badBodyparts.add(tail);
		}
	}
	
	private void checkWallCollision() {
		if ((head.getX() + ENTITY_SIZE) % (GAME_WIDTH + ENTITY_SIZE) * ((head.getY() + ENTITY_SIZE) % (GAME_HEIGHT + ENTITY_SIZE)) == 0) {
			head.setX((head.getX() + GAME_WIDTH) % GAME_WIDTH);
			head.setY((head.getY() + GAME_HEIGHT) % GAME_HEIGHT);
		}
	}
	
	private void checkFoodCollision() {
		if (head.getX() == food.getX() && head.getY() == food.getY()) {
			addBody();
			updateFood();
			updateScore(++score);
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

}
