// component interface

interface Coffee {
    String getDescription();
    double cost();
}

// concrete component

class SimpleCoffee implements Coffee{
    @Override
    public String getDescription(){
        return "Simple Coffee";
    }

    public double cost(){
        return 5.0;
    }
}


// base decorator

abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
        System.out.println(this.getDescription()+ " " + this.cost());
    }

    public String getDescription() {
        return coffee.getDescription();
    }

    public double cost() {
        return coffee.cost();
    }
}

// concrete Decorators

// MilkDecorator

class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
//        System.out.println("call made by "+ this.getClass()+ " to " + coffee.getClass());
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    public double cost() {
        return coffee.cost() + 2.0;
    }
}

// sugar decorator
class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
//        System.out.println("call made by "+ this.getClass()+ " to " + coffee.getClass());

    }

    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    public double cost() {
        return coffee.cost() + 1.0;
    }
}


public class DecoratorDemo {

    public static void main(String[] args) {

        Coffee coffee = new SimpleCoffee();

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println("Total Cost: " + coffee.cost());
    }
}