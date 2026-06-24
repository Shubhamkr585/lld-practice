import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.*;

class A{
    void run(){
        System.out.println("A is running");
    }
}

class B extends A{
    void run(){
        System.out.println("B is running");
    }
    void walk(){
        System.out.println("B is Walking");
    }

}

public class TreeSett{
    public static void main(String[] args){
       A a=new B();
    }
}