import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class pwmlh{
    public  static void main(String args[]){
//        Cricket cr=new Cricket();
//        Thread t1=new Thread(cr);
//        Thread t2=new Thread(cr);
//        Thread t3=new Thread(cr);
//        t1.start();
//        t1.start();//IllegalThreadStateException
////        t3.start();
        Lock lock = new ReentrantLock();

        Thread mainThread=Thread.currentThread();
        Thread t1=new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "  "+ Thread.currentThread().getState());
            System.out.println(mainThread.getName()+"  "+mainThread.getState());
        });
        t1.start();
        try{
            t1.join();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

//class Cricket implements Runnable{
//    AtomicInteger count=new AtomicInteger(0);
//    public void run() {
//        for (int j = 1; j <= 10000; j++) {
//            count.incrementAndGet();
//        }
//        // Take a snapshot of the count right now!
//        int snapShot=count.get();
//
//        // Print the snapshot, not the live variable
//        System.out.println(Thread.currentThread().getName() + " finished. Value was: " +  snapShot);
//    }
//}