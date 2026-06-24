//
//
//1. Vector is a legacy synchronized collection class that
//implements the List interface.
//2. It behaves like a dynamic array and grows as needed.
//3. It provides thread safety but with a performance cost
//in single-threaded environments.
//4. In modern applications, ArrayList or concurrent
//alternatives like CopyOnWriteArrayList are typically
//preferred over Vector unless thread safety is a
//priority.
//

import java.util.*;

public class VectorDemo {

    public static void main(String[] args) throws InterruptedException {

        Vector<Integer> vector = new Vector<>();

        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                vector.add(i);
            }
        };

        Runnable r1 = () -> {
            for (int i = 0; i < 100; i++) {
                vector.add(100 + i);
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r1);

        t1.start();
        t2.start();

       try {
        t1.join();
        t2.join();
       }
       catch (InterruptedException  e) {
           e.printStackTrace();
       }

        System.out.println(vector.size());
    }
}


// vector provides thread safety whereas ArrayList not
// op with vector is 200 which is what expected but with arraylist it is 162(can be anything)
//proving arraylist is not thread safe