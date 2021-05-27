
import data.dao.MinistryAccountDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utils.HibernateUtil;
import view.components.MinistryMenuPanel;

import javax.swing.*;
import java.util.List;

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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    createAndShowGUI();
                }
            });
        } catch (HibernateException e) {
            System.err.println(e);
        }

    }
}
