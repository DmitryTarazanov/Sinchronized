package src;

import java.util.*;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq=new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        final int numberOfThreads = 100;

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(new RobotPath(sizeToFreq));
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        Optional<Map.Entry<Integer, Integer>> maxEntry = sizeToFreq.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue));
        int maxCount = maxEntry.get().getValue();

        System.out.print("Самые частые количества повторений (встретились " + maxCount + " раз):");
        for (Map.Entry<Integer, Integer> freq: sizeToFreq.entrySet()) {
            if (freq.getValue() == maxCount) {
                System.out.print("  " + freq.getKey());
            }
        }
        System.out.println("\nДругие размеры:");
        for (Map.Entry<Integer, Integer> freq: sizeToFreq.entrySet()) {
            if (freq.getValue() < maxCount) {
                System.out.println(String.format("%s (%s раз)", freq.getKey(), freq.getValue()));
            }
        }
    }
}