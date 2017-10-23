import java.util.Comparator;

public class EventItemComparator implements Comparator<EventItem>
{
    public int compare(EventItem x, EventItem y)
    {
        if (x.getTimeOfDay() < y.getTimeOfDay())
        {
            return -1;
        }
        if (x.getTimeOfDay() > y.getTimeOfDay())
        {
            return 1;
        }
        return 0;
    }
}