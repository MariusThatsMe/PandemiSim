package pandemi1.events;

import pandemi1.Conf;
import pandemi1.PandemiSim;
import pandemi1.Person;
import pandemi1.RandomWrapper;
import pandemi1.State;

public class ContactEvent extends Event implements Conf{

    RandomWrapper random;
    PandemiSim sim;

    Person person;
    Person contact;
    int day;

    public ContactEvent(int day, Person person, PandemiSim sim) {
        super(day);
        this.person = person;
        this.day = day;
        this.sim = sim;
        contact = sim.getRandomPerson();
        random = RandomWrapper.getInstance();
    }

    @Override
    public Event happen() {
        if (person.getState() == State.SICK && contact.getState() == State.CLEAN){ // sjekker om personen man møter kan bli påvirket av sykdommen
            if (random.isNextDoubleWithinProbability(SICK_PROBABILITY)){ // sjekker om den man møter blir syk av kontakten
                contact.setState(State.SICK);
                int sickTime = random.nextInt(MIN_SICK_DAYS, MAX_SICK_DAYS);
                System.out.println("Someone got sick...");
                return new EndOfSickEvent(day + sickTime, person);
            }
        }
        return null;
    }
    
}