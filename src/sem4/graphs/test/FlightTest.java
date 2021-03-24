package sem4.graphs.test;

import org.junit.Assert;
import org.junit.Test;
import sem4.graphs.algorithms.Flight;

public class FlightTest {

    @Test
    public void getFlightsInfo(){
        Flight.Info info = Flight.getFlightsInfo("3\n" +
                "1;2;10;20\n" +
                "2;3;1;100\n" +
                "1;3;100;1\n");

        Assert.assertEquals(info.getD("1", "3"), 11);
        Assert.assertEquals(info.getD("1", "2"), 10);
        Assert.assertEquals(info.getD("2", "2"), 0);
        Assert.assertEquals(info.getD("3", "2"), 11);
    }
}
