package Domain;

import java.util.List;

public class Room {
    private List<Person> visitors;

    public void enter(Person c){
        this.visitors.add(c);
        c.setLocation(this);
    }

}
