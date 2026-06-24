import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    public static void main(String[] args) {
        System.out.println("Main Thread Started.");

        // 1. Create a Pool of 3 Threads
        // Even though we have 5 tasks, only 3 threads exist to do the work.
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 2. Submit 5 Tasks
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " is running on " + threadName);

                try {
                    // Simulate work (2 seconds)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Task " + taskId + " COMPLETED on " + threadName);
            });
        }

        // 3. Shutdown the executor
        // Important: If you don't do this, the program will keep running forever!
        executor.shutdown();
        System.out.println("All tasks submitted. Main thread is free.");
    }
}