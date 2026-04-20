package Domain;

import java.util.HashMap;
import java.util.Map;

public class PersonBuilder {
    String name;
    int headsAmount;
    Map<WeirdnessType, Float> percepion = new HashMap<>();

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder withHeadsAmount(int amount) {
        this.headsAmount = amount;
        return this;
    }

    public PersonBuilder withPerception(WeirdnessType type, float perception) {
        if (perception < 0.0 || perception > 1.0) {
            throw new InvalidPerceptionValueException("Invalid perception value");
        }
        this.percepion.put(type, perception);
        return this;
    }

    public Person build() {
        PersonPerception perception = new PersonPerception(this.percepion);
        return new Person(this.name, this.headsAmount, perception);
    }

}
