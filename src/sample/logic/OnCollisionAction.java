package sample.logic;

public enum OnCollisionAction {

    DEAD("Dead"),
    GAIN("Gain"),
    DOUBLE_GAIN("DoubleGain"),
    LOSS("Loss"),
    DOUBLE_LOSS("DoubleLoss"),
    NONE("None");

    private String name;

    OnCollisionAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
