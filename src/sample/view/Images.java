package sample.view;

public enum Images {

    WATERMELLON("watermellon.png"),
    APPLE("apple.png"),
    BERRY("berry.png"),
    COLA("cocacola.png"),
    COLAZERO("cocacolazero.png"),
    KIWI("kiwi.png"),
    POISON("poison.png"),
    LEGO("lego.png"),
    HEAD("head.png"),
    FIRE("fire.png"),
    WALL("wall.png");

    String fileName;

    Images(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
