package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;

import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int n = 10000;  // Size of the array
        MinHeap heap = new MinHeap(n);
        PerformanceTracker tracker = new PerformanceTracker();
        Random random = new Random();

        // Benchmark insertions
        for (int i = 0; i < n; i++) {
            int value = random.nextInt(10000);
            heap.insert(value);
            tracker.incrementArrayAccesses();
        }

        // Benchmark extractions
        for (int i = 0; i < n; i++) {
            heap.extractMin();
            tracker.incrementArrayAccesses();
        }

        // Output performance metrics
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Swaps: " + tracker.getSwaps());
        System.out.println("Array Accesses: " + tracker.getArrayAccesses());
    }
}
