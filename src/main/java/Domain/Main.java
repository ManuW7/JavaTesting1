package Domain;

public class Main {
    public static void main(String[] args) {

        Person arthur = new PersonBuilder()
                .withName("Arthur")
                .withHeadsAmount(1)
                .withPerception(WeirdnessType.BODY_WEIRDNESS, 1.0f)
                .build();

        Person creature = new PersonBuilder()
                .withName("Creature")
                .withHeadsAmount(2)
                .build();

        Action smile = new Action("smiling");
        creature.getHead(0).startAction(smile);

        Action weirdAction = new Action("picking teeth");
        creature.getHead(1).startAction(weirdAction);

        Observation obs = new Observation(
                creature,
                WeirdnessType.BODY_WEIRDNESS,
                WeirdnessLevel.HIGH
        );

        EmotionEngine engine = new EmotionEngine();
        engine.observe(arthur, obs);

        System.out.println(arthur.getEmotions());
    }
}