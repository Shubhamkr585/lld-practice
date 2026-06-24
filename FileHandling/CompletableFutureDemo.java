import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        System.out.println("Main Thread: Ordering Pizza...");

        // 1. Start an Async Task (Supply)
        CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("Kitchen: Making Pizza... (" + Thread.currentThread().getName() + ")");
                        TimeUnit.SECONDS.sleep(3); // Simulate cooking
                    } catch (InterruptedException e) { }
                    return "Margherita Pizza";
                })

                // 2. Process the result when ready (Transformation)
                .thenApply(pizza -> {
                    System.out.println("Driver: Delivering " + pizza + "... (" + Thread.currentThread().getName() + ")");
                    return pizza + " (Delivered)";
                })

                // 3. Final Step (Consumption)
                .thenAccept(finalStatus -> {
                    System.out.println("Customer: I received " + finalStatus);
                })

                // 4. Exception Handling (If anything goes wrong above)
                .exceptionally(ex -> {
                    System.out.println("Oops! Something went wrong: " + ex.getMessage());
                    return null;
                });

        System.out.println("Main Thread: I am watching TV while waiting...");

        // BLOCKING LINE: Just to keep main thread alive so we can see the output
        // In a real web server, you wouldn't need this sleep.
        try { Thread.sleep(4000); } catch (InterruptedException e) {}
    }
}