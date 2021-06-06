
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utils.HibernateUtil;
import utils.RandomUtils;
import view.MinistryMenuPanel;
import view.components.student.RegisterCoursePanel;

import javax.swing.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Main {


    private static void createAndShowGUI() {
        // Create JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set content pane for the window
        JPanel panel = new RegisterCoursePanel();
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
