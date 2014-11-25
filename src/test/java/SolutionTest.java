import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void shouldFindLis() {
        assertEquals(Arrays.asList(1), Solution.solve(Arrays.asList(1)));
    }

    @Test
    public void should() {
        assertEquals(Arrays.asList(15, 27, 38, 55, 65, 85),
                Solution.solve(Arrays.asList(15, 27, 14, 38, 26, 55, 46, 65, 85)));
    }

}