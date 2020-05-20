package pandemi1;

import java.util.PriorityQueue;

import pandemi1.events.ContactEvent;
import pandemi1.events.Event;
import pandemi1.RandomWrapper;

/**
 * PandemiSim is the main class of the application. It contains the main loop,
 * which advances the time, and a secondary loop that goes through the events of
 * the current day
 */
public class PandemiSim implements Conf {
    // PandemiSim uses the singleton pattern
    // to make the single PandemiSim object available everywhere.
    private static final PandemiSim theInstance
            = new PandemiSim();

    public static PandemiSim getInstance() {
        return theInstance;
    }

    // main just starts the sim
    public static void main(String[] args) {
        PandemiSim sim = PandemiSim.getInstance();
        sim.run();
    }

    // the day that are currently being simulated
    private int today;
    // queue of events
    PriorityQueue<Event> eventQueue;
    Person[] population;
    RandomWrapper random;

    public PandemiSim() {
        eventQueue = new PriorityQueue<Event>();
        population = new Person[POPULATION];
        random = RandomWrapper.getInstance();
    }

    public void run() {
        setup();
        for (int day = 0; day < MAX_DAY; day++) {
            sickContacts();
            runDay(day);

            System.out.println("");
            System.out.println("Day " + day );
            
            getDailyStatistics();
            System.out.println("");
        }
    }

    /**
     * Get all events that happen on the specified day, and make them happen()
     *
     * @param day
     */
    private void runDay(int day) {
        today = day;
        while (!eventQueue.isEmpty()) {
            Event e = eventQueue.poll();
            addEvent(e.happen());
        }
    }

    public void sickContacts(){
        for(Person person : population){
            if(person.getState() == State.SICK){
                int contacts = random.nextInt(0, MAX_CONTACTS_PER_DAY);
                for(int i = 0; i < contacts; i++){
                    addEvent(new ContactEvent(today + 1, person, getInstance()));
                }
            }
        }
    }

    public Person getRandomPerson() {
        RandomWrapper random = RandomWrapper.getInstance();
        int i = random.nextInt(0, POPULATION);
        return population[i];
    }

    public Person getNonDeadPerson(){
        Person nondead = getRandomPerson();
        if (nondead.getState() == State.DEAD)
            getNonDeadPerson();
        return nondead;
    }

    public void addEvent(Event e) {
        if (null == e)
            return;
        eventQueue.add(e);
    }

    public void setup(){
        for (int i = 0; i < population.length; i++)
            population[i] = new Person("p" + i, State.CLEAN);

        for (int i = 0; i < INITALLY_SICK; i++){
            population[i].setState(State.SICK);
        }
    }
    
    public void getDailyStatistics(){

        int clean = 0;
        int sick = 0;
        int immune = 0;
        int dead = 0;

        for(Person person : population){
            if(person.getState() == State.CLEAN)
                clean++;
            else if(person.getState() == State.SICK)
                sick++;
            else if(person.getState() == State.IMMUNE)
                immune++;
            else if(person.getState() == State.DEAD)
                dead++;
        }

        System.out.println("Daily Statistics | Clean: " + clean + " | Sick: " + sick + " | Immune: " + immune + " | Dead: " + dead);
    }   
}
