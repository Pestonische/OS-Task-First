package by.fpmi.os.main;

import java.util.List;

public interface StringProcessor {
    List<String> findFrequentStrings(List<String> strings, int n, int threadsCount) throws Exception;
}
