import java.util.Scanner;

// ===== Product Interfaces =====
interface IButton {
    void press();
}

interface ITextbox {
    void setText();
}

// ===== Mac Products =====
class MacButton implements IButton {
    public void press() {
        System.out.println("Mac button pressed");
    }
}

class MacTextBox implements ITextbox {
    public void setText() {
        System.out.println("Setting text in Mac TextBox");
    }
}

// ===== Windows Products =====
class WinButton implements IButton {
    public void press() {
        System.out.println("Win button pressed");
    }
}

class WinTextBox implements ITextbox {
    public void setText() {
        System.out.println("Setting text in Win TextBox");
    }
}

// ===== Abstract Factory Interface =====
interface IFactory {
    IButton createButton();
    ITextbox createTextBox();
}

// ===== Concrete Factories =====
class WinFactory implements IFactory {
    public IButton createButton() {
        return new WinButton();
    }

    public ITextbox createTextBox() {
        return new WinTextBox();
    }
}

class MacFactory implements IFactory {
    public IButton createButton() {
        return new MacButton();
    }

    public ITextbox createTextBox() {
        return new MacTextBox();
    }
}

// ===== Factory Selector (Factory of Factories) =====
class GUIAbstractFactory {

    public static IFactory createFactory(String osType) {

        if (osType.equalsIgnoreCase("windows")) {
            return new WinFactory();
        }
        else if (osType.equalsIgnoreCase("mac")) {
            return new MacFactory();
        }

        throw new IllegalArgumentException("Unsupported OS type");
    }
}

// ===== Client =====
public class GUIAbstractFactoryDemo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter machine OS (windows/mac): ");
        String osType = scanner.nextLine();

        IFactory factory = GUIAbstractFactory.createFactory(osType);

        IButton button = factory.createButton();
        button.press();

        ITextbox textBox = factory.createTextBox();
        textBox.setText();

        scanner.close();
    }
}