package pr4;

import java.util.LinkedList;
import java.util.Queue;

public class CustomExecutorService {
    private final int threadCount;
    private final Thread[] threads;
    private final Queue<Runnable> taskQueue;
    private volatile boolean isShutdown = false;

    // Конструктор, принимающий количество потоков в пуле
    public CustomExecutorService(int threadCount) {
        this.threadCount = threadCount;
        this.threads = new Thread[threadCount];
        this.taskQueue = new LinkedList<>();

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
    }

    // Метод для отправки задачи на выполнение
    public void submit(Runnable task) {
        if (!isShutdown) {
            synchronized (taskQueue) {
                taskQueue.add(task);
                taskQueue.notify(); // Уведомляем ожидающие потоки о наличии новой задачи
            }
        } else {
            throw new IllegalStateException("ExecutorService has been shut down");
        }
    }

    // Метод для завершения работы пула потоков
    public void shutdown() {
        isShutdown = true;
        for (Thread thread : threads) {
            thread.interrupt(); // Прерываем все потоки
        }
    }

    // Внутренний класс, представляющий рабочий поток
    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {
                Runnable task;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait(); // Ожидаем новой задачи
                        } catch (InterruptedException e) {
                            // Если поток прерван, проверяем флаг isShutdown и выходим из цикла
                            if (isShutdown) {
                                return;
                            }
                        }
                    }
                    task = taskQueue.poll(); // Получаем и удаляем задачу из очереди
                }
                try {
                    task.run(); // Выполняем задачу
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Пример использования
    public static void main(String[] args) {
        CustomExecutorService executorService = new CustomExecutorService(5);

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            executorService.submit(() -> {
                System.out.println("Task " + taskNumber + " executed by thread " + Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }
}

