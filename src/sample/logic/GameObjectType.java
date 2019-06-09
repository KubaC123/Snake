package sample.logic;

import javafx.scene.paint.Color;

public enum GameObjectType {

    SNAKE_HEAD("SnakeHead", Color.LIGHTGREEN, OnCollisionAction.NONE),
    SNAKE_BODY("SnakePart", Color.BLUE, OnCollisionAction.DEAD),
    FOOD("Food", Color.BLUE, OnCollisionAction.GAIN),
    POISON("Poison", Color.DARKRED, OnCollisionAction.LOSS),
    WALL("Wall", Color.GREY, OnCollisionAction.DEAD),
    FIRE("Fire", Color.ORANGE, OnCollisionAction.BURNED);

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
