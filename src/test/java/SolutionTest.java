import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void should() {
        assertArrayEquals(new String[]{"B,C,D"}, Solution.graph(new String[]{"A:B,C,D", "A"}));
        assertArrayEquals(new String[]{"B,C,D", "E"}, Solution.graph(new String[]{"A:B,C,D", "B:A,D,E", "C:E,B", "A"}));
    }

}