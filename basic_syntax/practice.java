package basic_syntax;
import java.time.LocalTime;
class A{
    private int value;
    A(int val){
        value=val;
    }
    A(){
        value=0;
    }
    int getValue(){
        return value;
    }
}

class B extends  A{
    String name;
    public B(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}

public class practice {
    public static void main(String[] args){
        B b=new B("Shubham");
        System.out.println(b.getName());
    }

}
//collectors

//toList()
//toSet()
//toMap()
//joining()
//counting()
//groupingBy()
//partitioningBy()
//mapping()
//summarizingInt()