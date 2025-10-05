package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        Random random = new Random();

        String csvFile = "docs/benchmark_results.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write("Array Size,Time (ms),Swaps,Comparisons\n");

            for (int n : sizes) {
                MinHeap heap = new MinHeap(n);
                PerformanceTracker tracker = new PerformanceTracker();

                long start = System.nanoTime();

                for (int i = 0; i < n; i++) {
                    heap.insert(random.nextInt());
                }

                long end = System.nanoTime();

                writer.write(n + "," + (end - start) / 1_000_000.0 + "," + tracker.getSwaps() + "," + tracker.getComparisons() + "\n");
            }
            System.out.println("Benchmark results saved to " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
