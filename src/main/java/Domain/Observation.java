package Domain;

public class Observation {
    private final Object observationObject;
    private final WeirdnessType weirdnessType;
    private final WeirdnessLevel weirdnessLevel;

    public Observation(Object observationObject,
                       WeirdnessType weirdnessType,
                       WeirdnessLevel weirdnessLevel) {

        this.observationObject = observationObject;
        this.weirdnessType = weirdnessType;
        this.weirdnessLevel = weirdnessLevel;
    }

    public Object getObservationObject() {
        return observationObject;
    }

    public WeirdnessType getWeirdnessType() {
        return weirdnessType;
    }

    public WeirdnessLevel getWeirdnessLevel() {
        return weirdnessLevel;
    }
}