package by.fpmi.os.main;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class StringThreadTest {

    private static final List<String> TEST_STRINGS = Arrays.asList("a", "b", "b", "c", "c", "c");
    private static final List<String> SINGLE_ELEMENT = Collections.singletonList("a");


    @Test
    public void testRunShouldSortSingleElementListByFrequency() {

        StringThread thread = new StringThread(SINGLE_ELEMENT, 0, SINGLE_ELEMENT.size());
        thread.run();

        List<Map.Entry<String, Integer>> result = thread.getStringsWithFrequency(1);
        List<Map.Entry<String, Integer>> expected = Collections.singletonList(new AbstractMap.SimpleEntry<>("a", 1));

        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRunShouldSortListByFrequency() {

        StringThread thread = new StringThread(TEST_STRINGS, 0, TEST_STRINGS.size());
        thread.run();

        List<Map.Entry<String, Integer>> result = thread.getStringsWithFrequency(2);
        List<Map.Entry<String, Integer>> expected = Arrays.asList(
                new AbstractMap.SimpleEntry<>("c", 3),
                new AbstractMap.SimpleEntry<>("b", 2)
        );

        Assert.assertEquals(result, expected);
    }


}
