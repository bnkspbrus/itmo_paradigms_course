package queue;

import java.util.Arrays;

public class ArrayQueueModuleTest {
    public static void fill() {
        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }
    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println("element: " + ArrayQueueModule.element() + " dequeue " + ArrayQueueModule.dequeue() + " size: " + ArrayQueueModule.size() + " toArray: " + Arrays.toString(ArrayQueueModule.toArray()));
        }
    }
    public static void main(String[] args) {
        fill();
        dump();
    }
}