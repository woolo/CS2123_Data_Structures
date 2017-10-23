import java.util.Comparator;

public class SpikeComparator implements Comparator<Spike>
{
    public int compare(Spike x, Spike y)
    { 
        if (x.getPoint() != y.getPoint())
        {
        	return x.getPoint() - y.getPoint();
        }
        else{
            return x.getHeight() - y.getHeight();
        }
    }
}