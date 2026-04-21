package Domain;

public class EmotionEngine {

    public void observe(Person p, Observation o) {

        p.observe(o);

        float perception = p.getPerception()
                .getPerception()
                .getOrDefault(o.getWeirdnessType(), 0f);

        float score = o.getWeirdnessLevel().value() * perception;
        
        if (score <= 0f) {
            return;
        }

        p.feelEmotion(Emotion.SHOCKED, score);
    }

    public void tick(Person p) {
        p.decayEmotions(0.1f);
    }
}