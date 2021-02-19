package by.fpmi.os.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StringThread extends Thread {

    private final List<String> strings;
    private final int start;
    private final int size;
    private List<Entry<String, Integer>> result;

    public StringThread(List<String> strings, int start, int size) {
        this.strings = strings;
        this.start = start;
        this.size = size;
    }

    @Override
    public void run() {
        int end = start + size;
        Map<String, Integer> stringsWithFrequency = new HashMap<>();
        for (int i = start; i < end; i++) {
            String element = strings.get(i);
            if (!stringsWithFrequency.containsKey(element)) {
                stringsWithFrequency.put(element, 1);
                findEqualsElements(i + 1, stringsWithFrequency, element);
            }
        }
        result = new ArrayList<>(stringsWithFrequency.entrySet());
        result.sort((first, second) -> second.getValue() - first.getValue());
    }

    private void findEqualsElements(int start, Map<String, Integer> stringsWithFrequency, String element) {
        for (int j = start; j < strings.size(); j++) {
            String current = strings.get(j);
            if (current.equals(element)) {
                int previousFrequency = stringsWithFrequency.get(element);
                stringsWithFrequency.replace(element, previousFrequency + 1);
            }
        }
    }

    public List<Entry<String, Integer>> getStringsWithFrequency(int n) {
        if (n >= result.size()) {
            return result;
        }
        return result.subList(0, n);
    }
}