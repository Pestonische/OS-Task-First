package by.fpmi.os.main;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStringProcessor implements StringProcessor {
    @Override
    public List<String> findFrequentStrings(List<String> strings, int n, int threadsCount)
            throws ExecutionException, InterruptedException {
        ForkJoinPool threadPool = new ForkJoinPool(threadsCount);
        List<String> result = new ArrayList<>();
        threadPool.submit(() -> strings.parallelStream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                .get()
                .entrySet()
                .stream()
                .sorted((first, second) -> (int) (second.getValue() - first.getValue()))
                .forEach(element -> result.add(element.getKey()));
        return result.subList(0, n);
    }
}
