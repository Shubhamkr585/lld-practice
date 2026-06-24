// define a static method
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
//
//enum Directions {
//    UP,
//    DOWN,
//    LEFT,
//    RIGHT;
//}


public class play1{
    public static void main(String[] args){
       List<int[]> list=new ArrayList<>();
        Predicate<Integer> p=i->i%2==1;
//       list.addAll(List.of(11,2,31,42,15,62,17,18,91,10));
//            list.stream()
//                .filter(x->p.test(x))
//                .map(x->2*x)
//                .forEach(x-> System.out.print(x+ " "));
//        list.addAll(List.of(1,2,3,4,5,6,7,8,9,10));
//                list.parallelStream()
//                    .map(x->x*x)
//                    .forEach(System.out::println);
        List<int[]> list1=new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new int[2500000]);
            System.out.println("block of size "+list.size());
        }

    }
}

//OS → Process create karta hai → JVM start hota hai →
//Class loading → Bytecode verify → Memory allocate →
//main() execute

//1. User runs `java ClassName`
//2. OS creates a Java process
//3. JVM starts inside that process
//4. JVM initializes memory + threads
//5. ClassLoader loads ClassName.class
//6. Bytecode verification
//9. main() method executes