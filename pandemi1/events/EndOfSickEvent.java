package pandemi1.events;

import pandemi1.Conf;
import pandemi1.Person;
import pandemi1.RandomWrapper;
import pandemi1.State;

public class EndOfSickEvent extends Event implements Conf{


    RandomWrapper random;
    Person person;
    int day;

    public EndOfSickEvent(int day, Person person) {
        super(day);
        this.day = day;
        this.person = person;
        random = RandomWrapper.getInstance();
    }

    @Override
    public Event happen() {
        if (random.isNextDoubleWithinProbability(DEATH_PROBILITY)){
            person.setState(State.DEAD);
            System.out.println("Someone died...");
        }
        else{
            person.setState(State.IMMUNE);
            System.out.println("Someone got immune...");
        }
        return null;
    }
    
}