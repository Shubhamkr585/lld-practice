import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

// demonstrate the use of CompletableFuture.ofAll(taks1,task2,..taskn)

public class  FaceBookFeedLoader {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        System.out.println("Fetching Facebook Feed Data...");

        // Task 1: Fetch User Profile (1 sec)
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            sleep(1);
            System.out.println("  -> User Profile Fetched");
            return "User: Shubham";
        });

        // Task 2: Fetch Friends List (2 sec)
        CompletableFuture<String> friendsFuture = CompletableFuture.supplyAsync(() -> {
            sleep(2);
            System.out.println("  -> Friends List Fetched");
            return "Friends: [Rahul, Priya, Amit]";
        });

        // Task 3: Fetch Recent Posts (3 sec)
        CompletableFuture<String> postsFuture = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            System.out.println("  -> Recent Posts Fetched");
            return "Posts: {'Hello World', 'Java is fun'}";
        });

        // The Magic: Wait for ALL to complete
        // .allOf() returns a void future that finishes when all inputs are done
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                userFuture, friendsFuture, postsFuture
        );

        // BLOCKING call: Wait here until the slowest task (3 sec) finishes
        allFutures.join();

        // Now we can safely get the results (instant, because they are ready)
        String user = userFuture.get();
        String friends = friendsFuture.get();
        String posts = postsFuture.get();

        long endTime = System.currentTimeMillis();

        System.out.println("\n--- FINAL AGGREGATED FEED ---");
        System.out.println(user);
        System.out.println(friends);
        System.out.println(posts);
        System.out.println("Total Time: " + (endTime - startTime) + " ms");
        // Notice it takes ~3000ms, NOT 6000ms!
    }

    // Helper method to simulate delay
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}