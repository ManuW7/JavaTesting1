package Domain;

import java.util.HashSet;
import java.util.Set;

public class Head {
    private final int id;
    private final Set<Action> actions = new HashSet<>();

    public Head(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void startAction(Action action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        actions.add(action);
    }

    public void stopAction(Action action) {
        if (actions.contains(action)) {
            action.stop();
            actions.remove(action);
        }
    }

    public Set<Action> getActions() {
        return actions;
    }
}