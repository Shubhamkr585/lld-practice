import java.util.*;
public class play2 {
    int x;
    play2(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "the value of x is " + x ;
    }

    public static void main(String[] args){
        Object ob=new play2(2);
        System.out.println(ob.toString());
    }

}