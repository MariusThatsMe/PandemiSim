package pandemi1.events;


/**
 * @author evenal
 */
public abstract class Event implements Comparable<Event> {

    public final int day;

    public Event(int day) {
        this.day = day;
    }

    public abstract Event happen();

    public int getDay() {
        return day;
    }

    @Override
    public int compareTo(Event that) {
        return (this.day - that.day);
    }
}
