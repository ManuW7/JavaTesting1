package Domain;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<Person> visitors = new ArrayList<>();

    public void enter(Person p) {
        visitors.add(p);
        p.setLocation(this);
    }

    public List<Person> getVisitors() {
        return visitors;
    }
}