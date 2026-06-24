import java.util.*;
import java.lang.*;

// note:using runnable interface is always a better option than using thread class
// beacause java allows single inheritance and when we will use the thread class
// we would loose the abilty to inherit the other class

//JVM
//Asks OS to create a new native thread
//Allocates stack memory
//Schedules it
//Eventually calls run()
//Thread scheduling is done by OS, not JVM.

class MyTask implements Runnable {
    public void run() {
        System.out.println("Thread running");
        this.otherMethod();
    }
    public void otherMethod() {
        System.out.println("This method cannot be called by runnable interface");
    }
}

public class DemoUsingRunnableInterface {
    public static void main(String[] args) {
        Runnable task = new MyTask();
        Thread t = new Thread(task);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(t.getState());
        t.start();
//        t.otherMethod();// note the task is of type Runnable so you can't make this call
    }
}
//Predicate  → boolean condition
//Function   → transformation
//Consumer   → action
//Supplier   → generator
