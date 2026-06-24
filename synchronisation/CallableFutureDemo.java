import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureDemo {

    public static void main(String[] args) {

        // Create a pool
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // Define a Callable task (note it returns Integer, not void)
        Callable<Integer> sumTask = () -> {
            System.out.println("Task started... calculating sum of 1 to 5");
            Thread.sleep(3000); // Simulate heavy calculation for 3 seconds
            int sum = 0;
            for (int i = 1; i <= 5; i++) sum += i;
            return sum;
        };

        System.out.println("Submitting Callable task...");

        // 1. Submit task. It returns a 'Future' immediately.
        // The main thread does NOT wait here.
        Future<Integer> futureResult = executor.submit(sumTask);

        System.out.println("Task submitted. Main thread is doing other work...");

        try {
            // 2. Get the result
            // This line BLOCKS. Main thread stops here until the result is ready
            Integer result = futureResult.get();

            System.out.println("Calculation Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}