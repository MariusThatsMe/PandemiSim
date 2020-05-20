package pandemi1.events;

import java.util.Comparator;

public class EventTimeComparator 
        implements Comparator<Event>{
    @Override
    public int compare(Event e1, Event e2){
        return e1.day - e2.day;
    }
}