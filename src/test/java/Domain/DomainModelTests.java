package Domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DomainModelTests {

    @Test
    void shouldCreatePersonWithDefaults() {
        Person person = new PersonBuilder()
                .withName("Arthur")
                .withHeadsAmount(1)
                .build();

        assertEquals("Arthur", person.getName());
        assertEquals(1, person.getHeads().length);
        assertNotNull(person.getPerception());
        assertTrue(person.getPerception().getPerception().isEmpty());
    }

    @Test
    void shouldThrowExceptionForInvalidPerception() {
        assertThrows(InvalidPerceptionValueException.class, () ->
                new PersonBuilder()
                        .withPerception(WeirdnessType.BODY_WEIRDNESS, -0.1f)
        );

        assertThrows(InvalidPerceptionValueException.class, () ->
                new PersonBuilder()
                        .withPerception(WeirdnessType.BODY_WEIRDNESS, 1.1f)
        );
    }


    @Test
    void actionShouldBeActiveByDefault() {
        Action action = new Action("test");
        assertTrue(action.isActive());
    }

    @Test
    void actionShouldStopCorrectly() {
        Action action = new Action("test");
        action.stop();
        assertFalse(action.isActive());
    }

    @Test
    void shouldThrowExceptionForNullActionDescription() {
        assertThrows(IllegalArgumentException.class, () ->
                new Action(null)
        );
    }


    @Test
    void headShouldStartAndStopAction() {
        Head head = new Head(1);
        Action action = new Action("smile");

        head.startAction(action);
        assertTrue(head.getActions().contains(action));

        head.stopAction(action);
        assertFalse(head.getActions().contains(action));
        assertFalse(action.isActive());
    }

    @Test
    void headsShouldBeIndependent() {
        Head h1 = new Head(1);
        Head h2 = new Head(2);

        Action action = new Action("test");
        h1.startAction(action);

        assertTrue(h1.getActions().contains(action));
        assertTrue(h2.getActions().isEmpty());
    }

    @Test
    void stoppingNonExistingActionShouldNotFail() {
        Head head = new Head(1);
        Action action = new Action("ghost");

        head.stopAction(action);

        assertFalse(head.getActions().contains(action));
    }


    @Test
    void personShouldStartAndStopAction() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));
        Action action = new Action("run");

        p.startAction(action);
        assertTrue(p.getActions().contains(action));

        p.stopAction(action);
        assertFalse(p.getActions().contains(action));
    }

    @Test
    void stoppingNonExistingPersonActionShouldNotFail() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));
        Action action = new Action("ghost");

        p.stopAction(action);

        assertFalse(p.getActions().contains(action));
    }


    @Test
    void shouldAccumulateShockEmotion() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));

        p.feelEmotion(Emotion.SHOCKED, 1.0f);
        p.feelEmotion(Emotion.SHOCKED, 2.0f);

        assertEquals(3.0f, p.getEmotions().get(Emotion.SHOCKED));
    }

    @Test
    void emotionDecayShouldNotGoBelowZero() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));

        p.feelEmotion(Emotion.SHOCKED, 0.2f);
        p.decayEmotions(1.0f);

        assertEquals(0f, p.getEmotions().get(Emotion.SHOCKED));
    }

    @Test
    void nullEmotionShouldThrowException() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));

        assertThrows(NullPointerException.class, () ->
                p.feelEmotion(null, 1.0f)
        );
    }

    @Test
    void shouldAddShockEmotionFromObservation() {
        Person p = new Person("Arthur", 1,
                new PersonPerception(Map.of(WeirdnessType.BODY_WEIRDNESS, 1.0f)));

        Observation o = new Observation(
                new Object(),
                WeirdnessType.BODY_WEIRDNESS,
                WeirdnessLevel.HIGH
        );

        EmotionEngine engine = new EmotionEngine();
        engine.observe(p, o);

        assertTrue(p.getEmotions().containsKey(Emotion.SHOCKED));
        assertTrue(p.getEmotions().get(Emotion.SHOCKED) > 0);
    }

    @Test
    void shouldNotAddEmotionWhenPerceptionMissing() {
        Person p = new Person("Arthur", 1,
                new PersonPerception(Map.of()));

        Observation o = new Observation(
                new Object(),
                WeirdnessType.BODY_WEIRDNESS,
                WeirdnessLevel.HIGH
        );

        EmotionEngine engine = new EmotionEngine();
        engine.observe(p, o);

        assertTrue(p.getEmotions().isEmpty());
    }

    @Test
    void shouldNotAddEmotionWhenWeirdnessIsZero() {
        Person p = new Person("Arthur", 1,
                new PersonPerception(Map.of(WeirdnessType.BODY_WEIRDNESS, 1.0f)));

        Observation o = new Observation(
                new Object(),
                WeirdnessType.BODY_WEIRDNESS,
                WeirdnessLevel.NONE
        );

        EmotionEngine engine = new EmotionEngine();
        engine.observe(p, o);

        assertTrue(p.getEmotions().isEmpty());
    }

    @Test
    void personShouldEnterRoom() {
        Room room = new Room();
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));

        room.enter(p);

        assertTrue(room.getVisitors().contains(p));
        assertEquals(room, p.getLocation());
    }

    @Test
    void roomShouldSupportMultiplePeople() {
        Room room = new Room();

        Person p1 = new Person("A", 1, new PersonPerception(Map.of()));
        Person p2 = new Person("B", 1, new PersonPerception(Map.of()));

        room.enter(p1);
        room.enter(p2);

        assertEquals(2, room.getVisitors().size());
    }


    @Test
    void shouldReturnCalmWhenNoEmotions() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));

        assertEquals(PersonState.CALM, p.evaluateState());
    }

    @Test
    void shouldReturnNervous() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));
        p.feelEmotion(Emotion.NERVOUS, 1.0f);

        assertEquals(PersonState.NERVOUS, p.evaluateState());
    }

    @Test
    void shouldReturnShocked() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));
        p.feelEmotion(Emotion.SHOCKED, 1.0f);

        assertEquals(PersonState.SHOCKED, p.evaluateState());
    }

    @Test
    void shouldReturnDisbelief() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));
        p.feelEmotion(Emotion.SHOCKED, 3.0f);

        assertEquals(PersonState.DISBELIEF, p.evaluateState());
    }

    @Test
    void shouldReturnOverwhelmed() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));
        p.feelEmotion(Emotion.SHOCKED, 6.0f);

        assertEquals(PersonState.OVERWHELMED, p.evaluateState());
    }

    @Test
    void shouldReturnJawDropped() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));
        p.feelEmotion(Emotion.SHOCKED, 9.0f);

        assertEquals(PersonState.JAW_DROPPED, p.evaluateState());
    }


    @Test
    void tickShouldDecayEmotions() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));
        p.feelEmotion(Emotion.SHOCKED, 1.0f);

        EmotionEngine engine = new EmotionEngine();
        engine.tick(p);

        assertTrue(p.getEmotions().get(Emotion.SHOCKED) < 1.0f);
    }


    @Test
    void shouldStoreObservation() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));

        Observation o = new Observation(
                new Object(),
                WeirdnessType.BODY_WEIRDNESS,
                WeirdnessLevel.LOW
        );

        p.observe(o);

        assertEquals(1, p.getObservations().size());
        assertEquals(o, p.getObservations().get(0));
    }


    @Test
    void shouldReturnCorrectHead() {
        Person p = new Person("Test", 2, new PersonPerception(Map.of()));

        assertNotNull(p.getHead(0));
        assertNotNull(p.getHead(1));
    }

    @Test
    void getHeadShouldThrowOnInvalidIndex() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));

        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                p.getHead(5)
        );
    }


    @Test
    void observationShouldReturnValues() {
        Object obj = new Object();

        Observation o = new Observation(
                obj,
                WeirdnessType.BODY_WEIRDNESS,
                WeirdnessLevel.HIGH
        );

        assertEquals(obj, o.getObservationObject());
        assertEquals(WeirdnessType.BODY_WEIRDNESS, o.getWeirdnessType());
        assertEquals(WeirdnessLevel.HIGH, o.getWeirdnessLevel());
    }


    @Test
    void environmentObjectShouldStoreName() {
        EnvironmentObject obj = new EnvironmentObject("Chair");

        assertEquals("Chair", obj.getName());
    }

    @Test
    void poseShouldStoreDescription() {
        Pose pose = new Pose("sitting relaxed");

        assertEquals("sitting relaxed", pose.getDescription());
    }

    @Test
    void personShouldStorePose() {
        Person p = new Person("Test", 1, new PersonPerception(Map.of()));
        Pose pose = new Pose("lying");

        p.setPose(pose);

        assertEquals(pose, p.getPose());
    }

    @Test
    void actionShouldReturnDescription() {
        Action action = new Action("smiling");

        assertEquals("smiling", action.getDescription());
    }

    @Test
    void headShouldReturnId() {
        Head head = new Head(42);

        assertEquals(42, head.getId());
    }

    @Test
    void headShouldThrowOnNullAction() {
        Head head = new Head(1);

        assertThrows(IllegalArgumentException.class, () ->
                head.startAction(null)
        );
    }

    @Test
    void builderShouldStorePerceptionCorrectly() {
        Person person = new PersonBuilder()
                .withName("Test")
                .withHeadsAmount(1)
                .withPerception(WeirdnessType.BODY_WEIRDNESS, 0.7f)
                .build();

        float value = person.getPerception()
                .getPerception()
                .get(WeirdnessType.BODY_WEIRDNESS);

        assertEquals(0.7f, value);
    }

    @Test
    void builderShouldSupportChaining() {
        PersonBuilder builder = new PersonBuilder();

        PersonBuilder result = builder.withPerception(
                WeirdnessType.BODY_WEIRDNESS, 0.5f
        );

        assertSame(builder, result);
    }


}