//The Builder pattern is used to construct a complex object step by step.
// It allows you to produce different types and representations of an object
// using the same construction process.
// It's especially useful when an object has a large number of optional parameters.
// chaining in javascript and even functional interface  in java
//obj.method1()
//   .method2()
//   .method3();

class Computer{
    // Required parameters
    private String CPU;
    private String RAM;

    // Optional parameters
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    // Private constructor to enforce object creation through the builder
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    // Builder Class
    public static class Builder {
        // Required parameters
        private String CPU;
        private String RAM;

        // Optional parameters - initialized to default values
        private boolean isGraphicsCardEnabled = false;
        private boolean isBluetoothEnabled = false;

        // Constructor with required parameters
        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        // Methods to set optional parameters
        public Builder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public Builder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;        }

        // Method to construct the final object
        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return this.getClass()+" {\nCPU= " + CPU + "\nRAM= "+RAM+ "\nis graphic card enabled= "+ isGraphicsCardEnabled +
                "\nis bluetooth enabled= " + isBluetoothEnabled + "\n}";
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder("Intel", "16GB")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();

        // Use the computer object
        System.out.println(computer);
    }
}

// also remember the concepts of director in builder design pattern
// 1.the product
// 2.the builder
// 3. client
// 4.director->optional
