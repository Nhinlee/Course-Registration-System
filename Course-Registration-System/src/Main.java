
import view.components.MinistryMenuPanel;

import javax.swing.*;

public class Main {


    private static void createAndShowGUI() {
        // Create JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set content pane for the window
        JPanel panel = new MinistryMenuPanel();
        panel.setOpaque(true);
        frame.setContentPane(panel);

        // Set visible
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
        /*MinistryAccountDAO ministryAccountDAO = new MinistryAccountDAO();
        ministryAccountDAO.getAll();*/
    }
}
