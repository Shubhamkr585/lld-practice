//// Functional interface are those interface that have only one abstract function
//// lambda expression can be used only with functional interface
//// the use of annotation like @functionalinterface is suggested as it clarify the intension of the
//// programmer
//import java.util.function.Predicate;
//import java.util.function.Function;
//import java.util.function.Supplier;
//import java.util.function.Consumer;
//import java.util.stream.*;
//import java.util.Arrays;
//import java.util.List;
//
//
//@FunctionalInterface
//interface MathOperation {
//    int apply(int a,int b);
//}
//public class FunctionalInterfaceDemo{
//
//    public static void main(String[] args){
//        MathOperation addition=(a,b)-> a+b;
//        MathOperation subtraction=(a,b)-> a-b;
////        System.out.println(addition.apply(1,2
        // Predicate -> functional interface (boolean valued function)
//        Predicate<Integer> isEven= x -> x % 2==0;
////        System.out.println(isEven.test(4));
//        // even you can join two predicate
//        Predicate<String> startsWithS=x -> x.charAt(0)=='S';
//        Predicate<String> endsWithM=x -> x.charAt(x.length()-1)=='m';
//        Predicate<String> and=startsWithS.and(endsWithM);
////        System.out.println(and.test("Shubham"));
//
//          //Function ---> work for you
//
//         Function<Integer,Integer> doubleIt=x->2*x;
//         Function<Integer,Integer> tripleIt=x->3*x;
////        System.out.println(doubleIt.andThen(tripleIt).apply(10));
//
//        // Consumer->takes data but return nothing
//        // Consumer<T>,void accept(T t)
//        Consumer<String> logger = (msg) -> {
//            System.out.println("Log: " + msg);
//        };
//
//        logger.accept("Server Started");

        //supplier -> The "Factory." It takes nothing but returns data.
//        Supplier<T>,T get()
//        Supplier<Double> randomGen = () -> {
//            return Math.random();
//        };
//
////        System.out.println(randomGen.get());
//
//        //Method refernce --> use method without invoking, used in place of lambda expression
//        List<String> students=Arrays.asList("Ram","Shyam","Ghanshayam");
////        students.forEach(x->System.out.println(x));
//
//    }
//}

//Functional programming (FP) is a declarative paradigm focusing on building
// software by applying pure functions, emphasizing immutable data,
// and avoiding side effects. It differs from Object-Oriented Programming (OOP),
// which uses mutable objects, state, and encapsulation to organize
// code. FP prioritizes "what to do," while OOP prioritizes "how to do it"
package basic_syntax;
        interface C{
        void f();
    }
        public class FunctionalInterfaceDemo {

        public static void main(String[] args) {
            // lambda expression -> works only for functional interface
            C c = () -> System.out.println("hello");
//            c.f();
            Thread t=new Thread(() -> System.out.println("running"));

            t.start();
        }
        }

        // lambda syntax
//1 . () -> System.out.println("hello")
//2 . (x)->x*x;
//3 . (a,b)->{
//        int c=a+b;
//        return c;
//        }

// type inference
// (int a,int b)->a+b ===  (a,b)->a+b

// threads
// before java 8 use anonymous class
//   new Thread(new Runnable(){
//    public void run(){
//        System.out.println("running");
//    }
//}).start();

// with lambda
//new Thread(() -> System.out.println("running")).start();

// use in comparators
//normally
//Collections.sort(list,new Comparator<Integer>(){
//    public int compare(Integer a,Integer b){
//        return a-b;
//    }
//});
//list.sort((a,b)->a-b);