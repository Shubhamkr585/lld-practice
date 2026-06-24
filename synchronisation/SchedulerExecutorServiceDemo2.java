import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerExecutorServiceDemo2 {

    public static void main(String[] args) {

        // Create a scheduled pool
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        System.out.println("Scheduler started at: " + System.currentTimeMillis());

        // Schedule a task to run every 2 seconds
        // Initial Delay: 1 sec, Period: 2 sec
        scheduler.scheduleAtFixedRate(
                () -> {
                    System.out.println("Heartbeat Check... Time: " + System.currentTimeMillis());
                },
                1, 2, TimeUnit.SECONDS
        );

        // Since the task runs forever, we need a way to stop the program
        // Let's schedule a "Killer Task" that shuts down the pool after 10 seconds
        scheduler.schedule(() -> {
            System.out.println("Shutting down scheduler...");
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);
    }
}