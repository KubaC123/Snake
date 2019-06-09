package sample.logic.objects;

import sample.core.GameObject;
import sample.logic.GameObjectType;
import sample.view.Images;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Food extends GameObject {

    public static List<Images> foodImages = Stream.of(Images.WATERMELLON, Images.COLA,
            Images.APPLE, Images.COLAZERO, Images.BERRY, Images.KIWI)
            .collect(Collectors.toList());

    public Food(double xCoordinate, double yCoordinate, double width, double height, Images image) {
        super(xCoordinate, yCoordinate, width, height, GameObjectType.FOOD, image);
    }
}
