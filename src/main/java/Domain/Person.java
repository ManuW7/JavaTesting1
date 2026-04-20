package Domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private Head[] heads;
    private List<Emotion> emotions = new ArrayList<>();
    private String name;
    private Room location;
    private PersonPercepion perception;

    public Person(){
        this.name = "Unknown";
    }

    public Person(String name, int headsAmount, PersonPercepion perception){
        this.name = name;
        this.heads = new Head[headsAmount];
        for (int i = 0; i < headsAmount; i++){
            Head newHead = new Head();
            this.heads[i] = newHead;
        }
        this.perception = perception;

    }

    public void setLocation(Room r){
        this.location =  r;
    }


}
