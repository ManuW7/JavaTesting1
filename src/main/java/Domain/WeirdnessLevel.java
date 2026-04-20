package Domain;

public enum WeirdnessLevel {
    NONE(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    EXTREME(4);

    private final int value;

    WeirdnessLevel(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    }
