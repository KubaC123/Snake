package sample.logic;

import javafx.scene.paint.Color;

public enum GameObjectType {

    SNAKE_HEAD("SnakeHead", Color.LIGHTGREEN,OnCollisionAction.NONE),
    SNAKE_BODY("SnakePart", Color.GREEN, OnCollisionAction.DEAD),
    FOOD("Food", Color.BLUE, OnCollisionAction.GAIN),
    SUPER_FOOD("SuperFood", Color.LIGHTBLUE, OnCollisionAction.DOUBLE_GAIN),
    POISON("Poison", Color.DARKRED, OnCollisionAction.LOSS),
    SUPER_POISON("SuperPoison", Color.RED, OnCollisionAction.DOUBLE_LOSS),
    WALL("Wall", Color.GREY, OnCollisionAction.DEAD);

    private String typeName;
    private Color color;
    private OnCollisionAction onCollisionAction;

    GameObjectType(String typeName, Color color, OnCollisionAction onCollisionAction) {
        this.typeName = typeName;
        this.color = color;
        this.onCollisionAction = onCollisionAction;
    }

    public String getTypeName() {
        return typeName;
    }

    public Color getColor() {
        return color;
    }

    public OnCollisionAction getOnCollisionAction() {
        return onCollisionAction;
    }
}
