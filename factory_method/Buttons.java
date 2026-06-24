

// Product interface
// abstract button
interface Button {
    void render();
}

// Concrete Products
// concrete button
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a Windows style button");
    }
}
//concrete button
class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a Mac style button");
    }
}

// Creator (Factory Method)
abstract class ButtonFactory {
    // Factory Method
    public abstract Button createButton();

    // An operation that uses the product
    public void renderButton() {
        Button button = this.createButton();
        button.render();
    }
}

// Concrete Creators
class WindowsButtonFactory extends ButtonFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

class MacButtonFactory extends ButtonFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }
}

// Client Code
public class Buttons {
    public static void main(String[] args) {
        ButtonFactory windowsFactory = new WindowsButtonFactory();
        windowsFactory.renderButton();

        ButtonFactory macFactory = new MacButtonFactory();
        macFactory.renderButton();
    }
}