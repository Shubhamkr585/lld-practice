import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// We extend RecursiveTask because we want to return a value (the sum)
// If we didn't want to return anything, we would use RecursiveAction
class SumTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10; // If list is small, do it directly
    private long[] arr;
    private int start;
    private int end;

    public SumTask(long[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 1. Base Case: If task is small enough, solve it directly
        if ((end - start) <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        }

        // 2. Recursive Case: Split the task
        else {
            int mid = (start + end) / 2;

            System.out.println("Splitting task: " + start + " to " + end);

            SumTask leftTask = new SumTask(arr, start, mid);
            SumTask rightTask = new SumTask(arr, mid, end);

            // "Fork" pushes the task to the pool (starts execution asynchronously)
            leftTask.fork();

            // We compute the right task immediately on the current thread
            Long rightResult = rightTask.compute();

            // "Join" waits for the left task to finish and gets the result
            Long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) {
        // Create a large array
        long[] array = new long[2000];
        for (int i = 0; i < array.length; i++) array[i] = i + 1; // Fill 1 to 20

        // Create the Pool
        ForkJoinPool pool = new ForkJoinPool();

        // Submit the main task
        SumTask task = new SumTask(array, 0, array.length);

        System.out.println("Starting ForkJoin Pool...");
        Long result = pool.invoke(task); // invoke() is a blocking call

        System.out.println("Final Sum: " + result); // Should be 210
    }
}