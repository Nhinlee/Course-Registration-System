package view.components;

import data.dao.MinistryAccountDAO;
import data.dao.StudentDAO;
import data.model.MinistryAccount;
import data.model.Student;
import sun.misc.JavaLangAccess;
import utils.UIDecoratorUtil;
import view.MinistryMenuPanel;
import view.components.student.RegisterCoursePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static utils.UIDecoratorUtil.createEditRow;

public class LoginPanel extends JPanel implements ActionListener {

    private final String studentModeString = "Student";
    private final String ministryModeString = "Ministry";

    // DAO
    final MinistryAccountDAO ministryAccountDAO = new MinistryAccountDAO();
    final StudentDAO studentDAO = new StudentDAO();

    // Components
    private final String btnLoginString = "Login";
    private String[] loginModeString = new String[]{
            studentModeString,
            ministryModeString,
    };
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JComboBox cbLoginMode;
    private JButton btnSubmit;

    // This frame hold main feature content
    private JFrame featureFrame;


    public LoginPanel() {
        // Set Layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Create components
        // Feature frame
        featureFrame = new JFrame();

        // Title
        JLabel titleLabel = new JLabel("Welcome to Registration System");
        titleLabel.setFont(new Font("Aria", Font.BOLD, 28));
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.PAGE_START);

        // Text Field
        JPanel usernamePanel;
        JPanel passwordPanel;
        tfUsername = new JTextField(30);
        tfPassword = new JTextField(30);
        usernamePanel = createEditRow(tfUsername, "User Name: ", "");
        passwordPanel = createEditRow(tfPassword, "Password: ", "");

        // ComboBox Login Mode
        cbLoginMode = new JComboBox(loginModeString);
        cbLoginMode.setFont(UIDecoratorUtil.customFont());
        cbLoginMode.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        cbLoginMode.setSelectedIndex(0);
        JPanel loginModePanel = new JPanel(new BorderLayout());
        loginModePanel.add(cbLoginMode, BorderLayout.CENTER);

        // Login Button
        btnSubmit = new JButton(btnLoginString);
        btnSubmit.addActionListener(this);
        btnSubmit.setActionCommand(btnLoginString);
        btnSubmit.setFont(UIDecoratorUtil.customFont());
        btnSubmit.setBorder(UIDecoratorUtil.customBorder());

        JPanel submitPanel = new JPanel(new BorderLayout());
        submitPanel.add(btnSubmit, BorderLayout.CENTER);
        submitPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        // Lay them out
        add(titleLabel);
        add(usernamePanel);
        add(passwordPanel);
        add(loginModePanel);
        add(submitPanel);

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }

    private void showFeatureFrame(JPanel panel) {
        // set content pane
        featureFrame.setContentPane(panel);
        // show edit frame
        featureFrame.pack();
        featureFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(btnLoginString)) {
            String loginMode = (String) cbLoginMode.getSelectedItem();
            assert loginMode != null;
            if (loginMode.equals(studentModeString)) {
                Student student = studentDAO.login(tfUsername.getText(), tfPassword.getText());
                if (student != null) {
                    showFeatureFrame(new RegisterCoursePanel(student));
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect! Try Again");
                }
            } else if (loginMode.equals(ministryModeString)) {
                if (ministryAccountDAO.login(tfUsername.getText(), tfPassword.getText())) {
                    showFeatureFrame(new MinistryMenuPanel());
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect! Try Again");
                }
            }
        }
    }
}
