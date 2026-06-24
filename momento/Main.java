public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        editor.setText("Hello");
        history.save(editor.save());

        editor.setText("Hello World");
        history.save(editor.save());

        editor.setText("Hello World!!!");

        System.out.println("Current: " + editor.getText());

        editor.restore(history.undo());
        System.out.println("Undo 1: " + editor.getText());

        editor.restore(history.undo());
        System.out.println("Undo 2: " + editor.getText());
    }
}