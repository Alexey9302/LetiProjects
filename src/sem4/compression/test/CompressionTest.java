package sem4.compression.test;

import org.junit.Assert;
import org.junit.Test;
import sem4.compression.shannonfano.Compression;

public class CompressionTest {

    /**
     * Немного текста на русском.
     * В примере "it is test string" для алгоритма
     * было неверно произведено разделение. Насколько я знаю,
     * в данном алгоритме нужно именно искать индекс до которого
     * будет существовать первая чать и с которого начинается вторая.
     * Например, у нас есть отрезок вероятностей (или частот) [l...r]
     * Выберем точку mid, которая соответствует условию (суммы наиболее
     * приближены), тогда получаться два отрезка [l...mid] и [mid+1...r].
     * Из-за этого такой алгоритм неэффективен в некоторых случаях.
     */
    @Test
    public void encode(){
        Compression.EncodeInfo info = Compression.encodeWithGettingObject("it is test string");
        Assert.assertEquals(info.getEncodeLen(), 50);

        info = Compression.encodeWithGettingObject("aaa bb c dd eee");
        Assert.assertEquals(info.getEncodeLen(), 41);

        info = Compression.encodeWithGettingObject("aaabbc");
        Assert.assertEquals(info.getEncodeLen(), 9);
    }

    @Test
    public void decode(){
        String result = Compression.decode("1 1 1 01 01 00");
        Assert.assertEquals(result, "aaabbc");

        result = Compression.decode("10 10 10 111 010 010 111 00 111 011 011 111 110 110 110");
        Assert.assertEquals(result, "aaabccbdbeebfff");
    }
}
