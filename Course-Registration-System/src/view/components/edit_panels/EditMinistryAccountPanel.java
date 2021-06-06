package view.components.edit_panels;

import data.dao.MinistryAccountDAO;
import data.model.MinistryAccount;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;
import view.base.EditMode;
import view.base.SaveEditingCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utils.UIDecoratorUtil.createEditRow;


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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel editIdPanel;
        JPanel editUsernamePanel;
        JPanel editPasswordPanel;
        tfId = new JTextField(30);
        tfUsername = new JTextField(30);
        tfPassword = new JTextField(30);
        // Create components
        if (oldAccount == null) {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.ministryAccount[0], "");
            editUsernamePanel = createEditRow(tfUsername, ColumnNameHelper.ministryAccount[1], "");
            editPasswordPanel = createEditRow(tfPassword, ColumnNameHelper.ministryAccount[2], "");
        } else {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.ministryAccount[0], oldAccount.getMinistryId());
            editUsernamePanel = createEditRow(tfUsername, ColumnNameHelper.ministryAccount[1], oldAccount.getUsername());
            editPasswordPanel = createEditRow(tfPassword, ColumnNameHelper.ministryAccount[2], oldAccount.getPassword());
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
