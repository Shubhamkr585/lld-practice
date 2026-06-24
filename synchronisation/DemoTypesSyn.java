
// 1.synchronise the method
class Counter{
    // Shared variable
    private int c = 0;
    // Synchronized method to increment counter
    public void inc(){
        c++;
        System.out.println(Thread.currentThread().getName());
    }
    // Synchronized method to get counter value
    public synchronized int get(){
        return c;
    }
}

public class DemoTypesSyn{

    public static void main(String[] args){

        // Shared resource
        Counter cnt = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++)
                cnt.inc();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++)
                cnt.inc();
        });
        System.out.println(t1.getClass().getName());
// Output: "DemoTypesSyn$1" (Yaani DemoTypesSyn class ke andar pehli anonymous class)

        t1.setName("t1");
        t2.setName("t2");



        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + cnt.get());
    }
}

//2. Synchronize  block
//synchronized (this) { c++; }

//3. Static synchronisation
//synchronize static methods
//the lock is placed on the class object rather than the instance

//4.Reentrancy

//If a thread already holds a monitor,
//it can re-enter synchronized block on same object.
//synchronized(obj) {
//synchronized(obj) {
//        // allowed
//        }
//        }

//No other thread can enter a synchronized block using the same monitor object.
//Every object in Java has its own monitor (intrinsic lock).

//internally,
//public synchronized void increment() {
//    System.out.println(Thread.currentThread().getName());
//}  this gets converted to  ---->
//synchronized(this) {
//        ...
//        }

// AND IF IT IS STATIC METHOD
// public static synchronised increment(){
//....
//}  ----->   get converted to

//synchronized(className.class) {
//        ...
//        }

//Instance Method (synchronized void foo()): Locks on 'this'.
// If you have 10 Counter objects, you have 10 different locks.
// Threads can run increment() on obj1 and obj2 simultaneously.

//Static Method (static synchronized void bar()):
// Locks on the 'Class Object'. There is only ONE 'Counter.class'(className.class)
// object in the entire JVM (per ClassLoader).

//Result: No matter how many instances of the class exist,
// only one thread can enter any static synchronized method of that class at a time.
