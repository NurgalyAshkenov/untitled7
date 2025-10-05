package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};  // Разные размеры массивов
        Random random = new Random();

        // Указываем путь к файлу для записи в папку docs
        String csvFile = "docs/benchmark_results.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Записываем заголовок CSV файла
            writer.write("Array Size,Time (ms),Swaps,Comparisons\n");

            // Цикл для различных размеров массивов
            for (int n : sizes) {
                MinHeap heap = new MinHeap(n);
                PerformanceTracker tracker = new PerformanceTracker();

                long start = System.nanoTime();

                // Вставка случайных значений в кучу
                for (int i = 0; i < n; i++) {
                    heap.insert(random.nextInt());
                }

                long end = System.nanoTime();

                // Записываем результаты в CSV
                writer.write(n + "," + (end - start) / 1_000_000.0 + "," + tracker.getSwaps() + "," + tracker.getComparisons() + "\n");
            }
            System.out.println("Benchmark results saved to " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
