package week2;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by SoBoLp on 2/12/16.
 */
public class RandomizedQueueTest {

    @Test
    public void testIsEmpty() throws Exception {
        RandomizedQueue<String> RQ = new RandomizedQueue<>();
        boolean result = RQ.isEmpty();
//        assertEquals(result,true);
        Assert.assertTrue(result);

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testEnqueue() throws Exception {

    }

    @Test
    public void testDequeue() throws Exception {

    }

    @Test
    public void testSample() throws Exception {

    }
}