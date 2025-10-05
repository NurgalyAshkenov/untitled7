package algorithms;

import java.util.Arrays;

public class MinHeap {
    private int[] heap;
    private int size;

    // Конструктор для инициализации кучи
    public MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // Помощники для работы с кучей
    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Обмен двух элементов
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Вставка элемента в кучу
    public void insert(int element) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2); // Увеличиваем размер массива, если он переполнен
        }
        heap[size] = element;
        size++;
        heapifyUp(size - 1);
    }

    // Поддержание свойств кучи при добавлении элемента
    private void heapifyUp(int index) {
        while (index > 0 && heap[parent(index)] > heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    // Удаление минимального элемента из кучи (корень)
    public int extractMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    // Поддержание свойств кучи при удалении элемента
    private void heapifyDown(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // Уменьшение значения ключа на определённом индексе
    public void decreaseKey(int index, int newValue) {
        if (newValue > heap[index]) {
            throw new IllegalArgumentException("New value is greater than current value");
        }
        heap[index] = newValue;
        heapifyUp(index);
    }

    // Объединение двух куч
    public void merge(MinHeap other) {
        for (int i = 0; i < other.size; i++) {
            insert(other.heap[i]);
        }
    }

    // Получение размера кучи
    public int size() {
        return size;
    }
}
