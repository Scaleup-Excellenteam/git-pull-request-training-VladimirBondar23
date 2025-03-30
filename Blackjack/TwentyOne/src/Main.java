import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.setProperty("sun.java2d.uiScale", "1.0");
                Game game = new Game();
            }
        });
    }
}