package view;

import view.components.MinistryAccountsPanel;
import view.components.SemestersPanel;
import view.components.SubjectsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MinistryMenuPanel extends JPanel implements ActionListener {

    // Button Labels
    private final String[] btnTexts = {
            "Ministry Accounts",
            "Subjects",
            "Semester",
            "Classes",
            "Student",
            "Course Registration Session",
            "Courses",
            "Student Of Course",
    };

    private final List<JButton> buttons = new ArrayList<>();
    private final int d = 240;
    private final JFrame featureFrame; // This frame hold content of all feature

    public MinistryMenuPanel() {
        // Set Layout
        setLayout(new GridLayout(2, btnTexts.length / 2));
        setPreferredSize(new Dimension(d * btnTexts.length / 2, d * 2));

        // Create components
        featureFrame = new JFrame();
        for (String btnText : btnTexts) {
            // Create new button
            JButton btn = new JButton(btnText);
            btn.setActionCommand(btnText);
            btn.addActionListener(this);
            btn.setFont(new Font("Aria", Font.PLAIN, 20));
            // Add to list
            buttons.add(btn);
        }
        // Lay them out
        for (JButton button : buttons) {
            add(button);
        }
    }

    private void showFeatureFrame(JPanel contentPanel) {
        // Set content pane
        featureFrame.setContentPane(contentPanel);
        // Display the window
        featureFrame.pack();
        featureFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(btnTexts[0])) {
            showFeatureFrame(new MinistryAccountsPanel());
        } else if (command.equals(btnTexts[1])) {
            showFeatureFrame(new SubjectsPanel());
        } else if (command.equals(btnTexts[2])) {
            showFeatureFrame(new SemestersPanel());
        }
    }
}
