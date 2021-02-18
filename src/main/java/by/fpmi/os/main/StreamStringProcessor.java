package by.fpmi.os.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStringProcessor implements StringProcessor {
    @Override
    public List<String> findFrequentStrings(List<String> strings, int n, int threadsCount) {
        ForkJoinPool threadPool = new ForkJoinPool(threadsCount);
        Map<String, Long> stringFrпequency;
        List<String>res = new ArrayList<>();
        try {
            stringFrequency = threadPool.submit(() -> strings.parallelStream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                    .get();
            List<Map.Entry<String, Long>> result= new ArrayList<>(stringFrequency.entrySet());
            result.sort((first, second) -> (int) (second.getValue() - first.getValue()));
            for (int i = 0; i < n; i++) {
                String string = result.get(i).getKey();
                res.add(string);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return res;
    }
}
