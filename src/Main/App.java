package Main;

import ui.UI;

public class App {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new UI().createUI());
    }
}
