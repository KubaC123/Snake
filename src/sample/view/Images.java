package sample.view;

public enum Images {

    WATERMELLON("watermellon.png"),
    APPLE("apple.png");

    String fileName;

    Images(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
