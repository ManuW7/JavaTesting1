package Domain;

import java.util.*;

public class Person {
    private Head[] heads;
    private Map<Emotion, Float> emotions = new HashMap<>();
    private String name;
    private Room location;
    private PersonPerception perception;
    private List<Observation> observations = new ArrayList<>();

    public Person() {
        this.name = "Unknown";
    }

    public Person(String name, int headsAmount, PersonPerception perception) {
        this.name = name;
        this.perception = perception;

        this.heads = new Head[headsAmount];
        for (int i = 0; i < headsAmount; i++) {
            this.heads[i] = new Head();
        }
    }

    public void feelEmotion(Emotion emotion, float level) {
        emotions.merge(emotion, level, Float::sum);
    }

    public void decayEmotions(float decayRate) {
        emotions.replaceAll((e, level) -> Math.max(0f, level - decayRate));
    }

    public Map<Emotion, Float> getEmotions() {
        return emotions;
    }

    public void setLocation(Room r) {
        this.location = r;
    }

    public PersonPerception getPerception() {
        return perception;
    }

    public void observe(Observation o) {
        observations.add(o);
    }
}