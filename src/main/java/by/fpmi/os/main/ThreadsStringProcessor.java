package by.fpmi.os.main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThreadsStringProcessor implements StringProcessor {

    @Override
    public List<String> findFrequentStrings(List<String> strings, int n, int threadsCount)
            throws InterruptedException {
        List<StringThread> stringThreads = buildThreads(strings, threadsCount);
        for (StringThread thread : stringThreads) {
            thread.start();
        }
        for (StringThread thread : stringThreads) {
            thread.join();
        }
        List<Map.Entry<String, Integer>> stringsWithFrequency = new ArrayList<>();
        for (StringThread thread : stringThreads) {
            stringsWithFrequency.addAll(thread.getStringsWithFrequency(n));
        }
        List<String> result = new ArrayList<>();
        stringsWithFrequency.sort((first, second) -> second.getValue() - first.getValue());

        for (int i = 0; i < n; i++) {
            String string = stringsWithFrequency.get(i).getKey();
            result.add(string);
        }
        return result;
    }

    private List<StringThread> buildThreads(List<String> strings, int threadsCount) {
        int size = strings.size();
        List<StringThread> threads = new ArrayList<>();
        int threadBegin = 0;
        List<Integer> threadSlices = defineSlices(size, threadsCount);

        for (var currentSlice : threadSlices) {
            StringThread thread = new StringThread(strings, threadBegin, currentSlice);
            threads.add(thread);
            threadBegin += currentSlice;
        }
        return threads;
    }

    private List<Integer> defineSlices(int size, int threadsCount) {
        int remainPart = size;
        ArrayList<Integer> slicesSizes = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++) {
            if (i == threadsCount - 1) {
                slicesSizes.add(remainPart);
            } else {
                int currentSize = size / threadsCount;
                remainPart -= currentSize;
                slicesSizes.add(currentSize);
            }
        }
        return slicesSizes;
    }
}
