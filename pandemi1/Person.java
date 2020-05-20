package pandemi1;

/**
 *
 * @author evenal
 */
public class Person implements Conf {

    String name;
    State state;

    public Person(String name, State state) {
        this.name = name;
        this.state = state;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public State setState(State s){
        state = s;
        return state;
    }

    public State getState() {
        return state;
    }
}
