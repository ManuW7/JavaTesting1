package Domain;

public class Observation {
    private Object observationObject;
    private WeirdnessType weirdnessType;
    private WeirdnessLevel weirdnessLevel;

    public Observation(Object objectName, WeirdnessType weirdnessType,
                       WeirdnessLevel weirdnessLevel) {
        this.observationObject = objectName;
        this.weirdnessType = weirdnessType;
        this.weirdnessLevel = weirdnessLevel;
    }

    public Object getObservationObject() {
        return observationObject;
    }

    public WeirdnessLevel getWeirdnessLevel() {
        return weirdnessLevel;
    }

    public WeirdnessType getWeirdnessType() {
        return weirdnessType;
    }
}
