package pandemi1.events;

import pandemi1.Person;
import pandemi1.State;

public class DiesEvent extends Event {

    Person person;
    int day;
    
    public DiesEvent(int day, Person person){
        super(day);
        this.person = person;
        this.day = day;

    }

    @Override
    public Event happen() {
        person.setState(State.DEAD);
        return null;
    }
    
}