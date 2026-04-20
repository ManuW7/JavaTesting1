package Domain;

import java.util.Map;

public class PersonBuilder {
    String name;
    int headsAmount;
    Map<WeirdnessType, Float> percepion;

    public PersonBuilder withName(String name){
        this.name = name;
        return this;
    }

    public PersonBuilder withHeadsAmount(int amount){
        this.headsAmount = amount;
        return this;
    }

    public PersonBuilder withPerception(WeirdnessType type, float perception){
        this.percepion.put(type, perception);
        return this;
    }

    public Person build(){
        PersonPercepion perception = new PersonPercepion(this.percepion);
        Person newPerson = new Person(this.name, this.headsAmount, perception);
        return  newPerson;
    }

}
