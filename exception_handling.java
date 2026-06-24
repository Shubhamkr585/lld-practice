import java.util.*;

public class exception_handling {

    public static void age_limitation(int age) throws ArithmeticException {
        if (age < 18) {
            throw new ArithmeticException("Age must be 18 or above");
        }
    }
    public static void main(String[] args) {
        int age=17;
        // demonstrate without using try-catch
          try {
              age_limitation(age);
              System.out.println("hey my name is shubham\n");
          }
          catch (ArithmeticException e) {
              System.out.println("hi there seems that you not eligible : "+ e);
          }
    }
}