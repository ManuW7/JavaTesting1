package Domain;

import java.util.Map;

public class PersonPerception {
    private Map<WeirdnessType, Float> perception;

    public PersonPerception(Map<WeirdnessType, Float> perception) {
        this.perception = perception;
    }

    public Map<WeirdnessType, Float> getPerception() {
        return this.perception;
    }
}
