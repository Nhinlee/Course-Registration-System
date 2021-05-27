package view.base;

import data.dao.MinistryAccountDAO;
import data.model.MinistryAccount;
import utils.UIDecoratorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditMinistryAccountPanel extends JPanel implements ActionListener {

    // Edit mode default is add new
    private final EditMode editMode;
    //
    private final String saveString = "Save";
    JTextField tfId;
    JTextField tfUsername;
    JTextField tfPassword;
    //
    SaveEditingCallback saveEditingCallback;

    public EditMinistryAccountPanel(MinistryAccount oldAccount, SaveEditingCallback callback) {
        editMode = oldAccount == null ? EditMode.addNew : EditMode.update;
        saveEditingCallback = callback;
        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel editIdPanel;
        JPanel editUsernamePanel;
        JPanel editPasswordPanel;
        tfId = new JTextField(30);
        tfUsername = new JTextField(30);
        tfPassword = new JTextField(30);
        // Create components
        if (oldAccount == null) {
            editIdPanel = createEditRow(tfId, "Ministry ID", "");
            editUsernamePanel = createEditRow(tfUsername, "Username", "");
            editPasswordPanel = createEditRow(tfPassword, "Password", "");
        } else {
            editIdPanel = createEditRow(tfId, "Ministry ID", oldAccount.getMinistryId());
            editUsernamePanel = createEditRow(tfUsername, "Username", oldAccount.getUsername());
            editPasswordPanel = createEditRow(tfPassword, "Password", oldAccount.getPassword());
        }

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        btnSave.setActionCommand(saveString);
        btnSave.setFont(UIDecoratorUtil.customFont());
        btnSave.setBorder(UIDecoratorUtil.customBorder());

        // Lay them out
        add(editIdPanel);
        add(editUsernamePanel);
        add(editPasswordPanel);
        add(btnSave);

        setBorder(UIDecoratorUtil.customBorder());
    }

    private JPanel createEditRow(JTextField tf, String labelText, String tfText) {
        //
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        //
        JLabel label = new JLabel(labelText);
        label.setFont(UIDecoratorUtil.customFont());
        label.setBorder(UIDecoratorUtil.customBorder());

        tf.setText(tfText);
        tf.setFont(UIDecoratorUtil.customFont());
        tf.setBorder(UIDecoratorUtil.customBorder());
        //
        panel.add(label);
        panel.add(tf);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(saveString)) {
            final MinistryAccount account = new MinistryAccount();
            account.setMinistryId(tfId.getText());
            account.setUsername(tfUsername.getText());
            account.setPassword(tfPassword.getText());

            // Begin transaction
            if (editMode == EditMode.addNew) {
                (new MinistryAccountDAO()).insert(account);
            } else {
                (new MinistryAccountDAO()).update(account);
            }

            // callback to parent
            saveEditingCallback.onSaved();
        }
    }


}
