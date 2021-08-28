package main;

import controller.KeyboardInputs;
import entity.Body;
import entity.Food;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;

import static main.Main.SCENE_WIDTH;
import static main.Main.SCENE_HEIGHT;


public class Game {
	
	public static final int ENTITY_SIZE = 20;

	public static AnimationTimer gameloop;

	public static int xDir = 1, yDir = 1;

	private Body head, tail, tmpBody;
	private Food food;
	private int snakeSize = 1;

	private List<Body> badBodyparts = new ArrayList<Body>();
	
	public Game() {
		start();
	}

	private void start() {		
		initSnake();
		initFood();
		KeyboardInputs.controlSnake();

		gameloop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				KeyboardInputs.keyWait = false;
				updateSnake();
				
				checkWallCollision();
				checkFoodCollision();
				if (snakeSize > 4)
					checkSnakeCollision();
				
				try {
					Thread.sleep(70);
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
		head = new Body(Main.SCENE_WIDTH / 2, Main.SCENE_HEIGHT / 2);
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
		if ((head.getX() + ENTITY_SIZE) % (SCENE_WIDTH + ENTITY_SIZE) * ((head.getY() + ENTITY_SIZE) % (SCENE_HEIGHT + ENTITY_SIZE)) == 0) {
			head.setX((head.getX() + SCENE_WIDTH) % SCENE_WIDTH);
			head.setY((head.getY() + SCENE_HEIGHT) % SCENE_HEIGHT);
		}
	}
	
	private void checkFoodCollision() {
		if (head.getX() == food.getX() && head.getY() == food.getY()) {
			addBody();
			updateFood();
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
		gameloop.stop();
		System.out.println("Damn, u bad...");
	}

}
