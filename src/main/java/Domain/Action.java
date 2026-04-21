package Domain;

public class Action {
    private final String description;
    private boolean active;

    public Action(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = description;
        this.active = true;
    }

    public void stop() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }
}