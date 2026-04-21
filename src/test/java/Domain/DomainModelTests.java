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

        // perception может быть пустым, но не null
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
}