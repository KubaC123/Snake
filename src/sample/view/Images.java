package sample.view;

public enum Images {

    WATERMELLON("watermellon.png"),
    APPLE("apple.png"),
    BERRY("berry.png"),
    COLA("cocacola.png"),
    COLAZERO("cocacolazero.png"),
    KIWI("kiwi.png"),
    POISON("poison.png"),
    TRUMP("trump.png");

    String fileName;

    Images(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
