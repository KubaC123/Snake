package sample.logic;

import javafx.concurrent.Task;
import sample.core.CollisionDetector;
import sample.core.Direction;
import sample.core.GameObject;
import sample.logic.objects.*;
import sample.view.GameCanvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class GameController {

    public static double SPEED = 20.d;
    public static double GAME_CANVAS_WIDTH = 600.d;
    public static double GAME_CANVAS_HEIGHT = 600.d;
    public static double MAIN_STAGE_WIDTH = 600.d;
    public static double MAIN_STAGE_HEIGHT = 600.d;
    public static final double SNAKE_INITIAL_X_COORDINATE = 100.d;
    public static final double SNAKE_INITIAL_Y_COORDINATE = 100.d;
    public static Direction DEFAULT_DIRECTION = Direction.RIGHT;

    private Direction currentDirection;
    private boolean gameRunning;
    private Snake snake;
    private AtomicReference<Food> food;
    private AtomicReference<Poison> poison;
    private AtomicReference<Fire> fire;
    private List<AtomicReference<Wall>> walls;
    private AtomicInteger score = new AtomicInteger(0);
    private GameCanvas gameCanvas;

    public GameController(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        currentDirection = Direction.RIGHT;
        snake = ObjectSpawned.newSnake();
        food = new AtomicReference<>(ObjectSpawned.startFood());
        poison = new AtomicReference<>(null);
        fire = new AtomicReference<>(null);
        walls = new ArrayList<>();
        startGame();
    }

    private void startGame() {
        gameRunning = true;
        startGameCanvasRenderThread();
        startSnakeMovementThread();
    }

    private void startGameCanvasRenderThread() {
        Thread gameCanvasRenderThread = new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(gameRunning && !isCancelled()) {
                    gameCanvas.setObjects(getAllCurrentGameObjects());
                    gameCanvas.setScore(score.get());
                    gameCanvas.render();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException exception) {
                        if(isCancelled()) {
                            break;
                        }
                    }
                }
                return null;
            }
        });
        gameCanvasRenderThread.setDaemon(true);
        gameCanvasRenderThread.start();
    }

    private synchronized  List<GameObject> getAllCurrentGameObjects() {
        List<GameObject> allGameObjects = new ArrayList<>(snake.getHeadAndBody());
        Optional.ofNullable(food.get()).ifPresent(allGameObjects::add);
        Optional.ofNullable(poison.get()).ifPresent(allGameObjects::add);
        Optional.ofNullable(fire.get()).ifPresent(allGameObjects::add);
        walls.stream()
                .map(AtomicReference::get)
                .forEach(allGameObjects::add);
        return allGameObjects;
    }

    private void startSnakeMovementThread() {
        Thread snakeMovementThread = new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(gameRunning && !isCancelled()) {
                    snake.move(currentDirection);
                    resolveCollisions(CollisionDetector.detectCollisionsWithRoot(snake.getHead(), getAllCurrentGameObjects()));
                }
                return null;
            }
        });
        snakeMovementThread.setDaemon(true);
        snakeMovementThread.start();
    }

    private void resolveCollisions(List<OnCollisionAction> collisionActions) {
        collisionActions.forEach(this::resolveCollision);
    }

    private void resolveCollision(OnCollisionAction onCollisionAction) {
        switch (onCollisionAction) {
            case DEAD: {
                setGameFail();
                break;
            }
            case GAIN: {
                foodEaten();
                break;
            }
            case LOSS: {
                poisonEaten();
                break;
            }
            case BURNED: {
                burned();
            }
            default: {
                break;
            }
        }
    }

    private void foodEaten() {
        poison.set(null);
        fire.set(null);
        score.set(score.intValue() + 20);
        snake.addBodyPart();
        food.set(ObjectSpawned.newFood(getAllCurrentGameObjects()));
        randomlySpawnObstacles();
    }

    private void randomlySpawnObstacles() {
        if(ObjectSpawned.random.nextInt(2) == 1) {
            poison.set(ObjectSpawned.newPoison(getAllCurrentGameObjects()));
        }
        if(ObjectSpawned.random.nextInt(8) == 4) {
            fire.set(ObjectSpawned.newFire(getAllCurrentGameObjects()));
        }
        if(ObjectSpawned.random.nextInt(3) == 2) {
            walls.add(new AtomicReference<>(ObjectSpawned.newWall(getAllCurrentGameObjects())));
        }
    }

    private void poisonEaten() {
        poison.set(null);
        snake.removeLastBodyPart();
        score.set(score.intValue() - 20);
    }

    private void burned() {
        fire.set(null);
        for(int i = 0; i < 4; i++) {
            snake.removeLastBodyPart();
        }
        score.set(score.intValue() - 80);
    }

    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    private void setGameFail() {
        gameRunning = false;
        gameCanvas.youLostScreen();
    }

    public void restartGame() {
        resetGameState();
        startGame();
    }

    private void resetGameState() {
        score.set(0);
        snake = ObjectSpawned.newSnake();
        food.set(ObjectSpawned.startFood());
        this.currentDirection = Direction.RIGHT;
    }
}
