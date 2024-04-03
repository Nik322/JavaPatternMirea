package pr3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        // Пример использования потокобезопасного Set
        ConcurrentSet<Integer> concurrentSet = new ConcurrentSet<>();
        try {
            concurrentSet.add(1);
            concurrentSet.add(2);
            concurrentSet.add(3);
            concurrentSet.add(4);

            System.out.println("Set contains 2: " + concurrentSet.contains(2));
            System.out.println("Set contains 5: " + concurrentSet.contains(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Пример использования потокобезопасного List
        ConcurrentList<String> concurrentList = new ConcurrentList<>();
        concurrentList.add("apple");
        concurrentList.add("banana");
        concurrentList.add("orange");

        System.out.println("List contains 'banana': " + concurrentList.contains("banana"));
        System.out.println("List contains 'grape': " + concurrentList.contains("grape"));
    }

    // Потокобезопасная реализация Set с использованием Semaphore
    static class ConcurrentSet<T> {
        private final Set<T> set = new HashSet<>();
        private final Semaphore semaphore = new Semaphore(1);

        public void add(T element) throws InterruptedException {
            semaphore.acquire();
            try {
                set.add(element);
            } finally {
                semaphore.release();
            }
        }

        public boolean contains(T element) throws InterruptedException {
            semaphore.acquire();
            try {
                return set.contains(element);
            } finally {
                semaphore.release();
            }
        }
    }

    // Потокобезопасная реализация List с использованием Lock
    static class ConcurrentList<T> {
        private final List<T> list = new ArrayList<>();
        private final Lock lock = new ReentrantLock();

        public void add(T element) {
            lock.lock();
            try {
                list.add(element);
            } finally {
                lock.unlock();
            }
        }

        public boolean contains(T element) {
            lock.lock();
            try {
                return list.contains(element);
            } finally {
                lock.unlock();
            }
        }
    }
}

