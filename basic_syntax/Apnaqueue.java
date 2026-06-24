
package basic_syntax;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Apnaqueue {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayBlockingQueue<>(3);
        for (int i=0;i<3;i++){
            queue.add(i);
        }
        for(int i=0;i<5;i++){

            try {
                System.out.println(queue.element());
            } catch (Exception e) {
                throw new RuntimeException("size chota ho gaya");
            }
            queue.remove();
        }
    }
}