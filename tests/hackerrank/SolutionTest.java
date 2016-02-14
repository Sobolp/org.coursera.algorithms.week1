package hackerrank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by SoBoLp on 2/13/16.
 */
public class SolutionTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testMain() throws Exception {
        // Simulate input
        String input = "INPUT";
        String expected = "INPUT";

        // set stdin
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // set stdout
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        // call the method that reads from stdin and writes to stdout
        String[] arg = new String[1];
        Solution.main(arg);

        // assert stdout's content value
        Assert.assertEquals(expected, baos.toString());


    }
}