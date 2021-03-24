package sem4.associativearray.test;

import org.junit.Assert;
import org.junit.Test;
import sem4.associativearray.mapka.Mapka;

public class MapkaTest {

    @Test
    public void insert(){
        Mapka<Integer, String> map = new Mapka<>();

        map.insert(1, "Cat");
        map.insert(2, "Dog");

        Assert.assertEquals(map.size(), 2);
    }

    @Test
    public void remove(){
        Mapka<Integer, String> map = new Mapka<>();

        map.insert(1, "Cat");
        map.insert(2, "HotDog");
        map.insert(3, "Dog");
        map.remove(2);

        Assert.assertEquals(map.size(), 2);
    }

    @Test
    public void find(){
        Mapka<Integer, String> map = new Mapka<>();

        map.insert(1, "Cat");
        map.insert(2, "Dog");

        Assert.assertEquals(map.find(2), "Dog");
    }

    @Test
    public void clear(){
        Mapka<Integer, String> map = new Mapka<>();

        map.insert(1, "Cat");
        map.insert(2, "Dog");
        map.clear();

        Assert.assertEquals(map.size(), 0);
    }

    @Test
    public void getKeys(){
        Mapka<Integer, String> map = new Mapka<>();

        map.insert(1, "Cat");
        map.insert(2, "Dog");

        Assert.assertEquals(map.getKeys().getSize(), 2);
    }

    @Test
    public void getValues(){
        Mapka<Integer, String> map = new Mapka<>();
        map.insert(1, "Cat");
        map.insert(2, "Dog");

        Assert.assertEquals(map.getValues().getSize(), 2);
    }
}
