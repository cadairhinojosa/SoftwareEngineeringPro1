public class Main {

    public static void main(String[] args) {
        // Initialize and display the GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            new ControlFlowGeneratorGUI().setVisible(true);
        });
    }
}
