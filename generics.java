import java.util.ArrayList;
import java.util.List;

class Animal{
    void walk(){
        System.out.println("All animals can walk");
        System.out.println(sum(1));
    }
    void eat(){
        System.out.println("All animals can eat");
    }
    String sum(String list){
        return "shubham";
    }
    int sum(int list){
        return 5;
    }
}

class Cat extends Animal{
    void eat(){
        System.out.println("All cats can eat");
    }
    void walk(){
        System.out.println("All cats can walk");
    }
    void meow(){
        System.out.println("All cats can meow");
    }
}

class Dog extends Animal{
    void eat(){
        System.out.println("All dogs can eat");
    }
    void walk(){
        System.out.println("All dogs can walk");
    }
    void bark(){
        System.out.println("All dogs can bark");
    }
}

public class generics{
    public static void main(String[] args){
//      List<Cat> cats=new ArrayList<>();
//      cats.add(new Cat());
//      cats.add(new Cat());
        List<Animal> animals=new ArrayList<>();
        animals.add(new Animal());
        animals.add(new Cat());
        generics.print(animals);
      for(int i=0;i<animals.size();i++){
          animals.get(i).walk();
      }
    }

    public static void print(List<? super Animal>list){
          list.add(new Dog());
    }
}

// basically extends means that we can add "read" the values of lower classes using upper classes
// super means we can add the object of lower class to the list of upper classes

// PECS
//Producer->extends
//Consumer->super

// we cannot overload using generics due to type erasure.
// type erasure means the byte code generated doesn't have the type details with the list or collection framework
//and thus can't be differntiated on the basis of type
