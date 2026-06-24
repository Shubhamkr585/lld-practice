/*
 An anonymous class in Java is a class without a name that is declared
 and instantiated in a single expression.
*/
interface A {
    void show();
}

abstract class Base {
    abstract void method();
}

public class AnonymousClass {
    public static void main(String[] args) {
        A interfaceObj = new A() {
            @Override
            public void show() {
                System.out.println("Anonymous class implementing interface");
            }
        };
        interfaceObj.show();

        Base abstractObj = new Base() {
            @Override
            void method() {
                System.out.println("Anonymous class extending abstract class");
            }
        };
        abstractObj.method();
    }
}
