package Domain;

import java.util.*;

public class Person {

    private String name;
    private Head[] heads;
    private Set<Action> actions = new HashSet<>();
    private Map<Emotion, Float> emotions = new HashMap<>();
    private Room location;
    private PersonPerception perception;
    private List<Observation> observations = new ArrayList<>();
    private Pose pose;

    public Person() {
        this.name = "Unknown";
    }

    public Person(String name, int headsAmount, PersonPerception perception) {
        this.name = name;
        this.perception = perception;

        this.heads = new Head[headsAmount];
        for (int i = 0; i < headsAmount; i++) {
            this.heads[i] = new Head(i);
        }
    }

    public void startAction(Action action) {
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

    public Head getHead(int index) {
        return heads[index];
    }

    public Head[] getHeads() {
        return heads;
    }

    public void feelEmotion(Emotion emotion, float level) {
        if (emotion == null) {
            throw new NullPointerException("Emotion cannot be null");
        }
        emotions.merge(emotion, level, Float::sum);
    }

    public void decayEmotions(float decayRate) {
        emotions.replaceAll((e, level) -> Math.max(0f, level - decayRate));
    }

    public Map<Emotion, Float> getEmotions() {
        return emotions;
    }

    public PersonPerception getPerception() {
        return perception;
    }


    public void observe(Observation o) {
        observations.add(o);
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setLocation(Room r) {
        this.location = r;
    }

    public Room getLocation() {
        return location;
    }

    public void setPose(Pose pose) {
        this.pose = pose;
    }

    public Pose getPose() {
        return pose;
    }

    public String getName() {
        return name;
    }
}