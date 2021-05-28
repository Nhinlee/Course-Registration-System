package view.components.edit_panels;

import data.dao.SubjectDAO;
import data.model.Subject;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;
import view.base.EditMode;
import view.base.SaveEditingCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utils.UIDecoratorUtil.createEditRow;

public class EditSubjectPanel extends JPanel implements ActionListener {
    // Edit mode default is add new
    private final EditMode editMode;
    //
    private final String saveString = "Save";
    JTextField tfId;
    JTextField tfSubjectName;
    JTextField tfCredits;
    //
    SaveEditingCallback saveEditingCallback;

    public EditSubjectPanel(Subject oldSubject, SaveEditingCallback callback) {
        editMode = oldSubject == null ? EditMode.addNew : EditMode.update;
        saveEditingCallback = callback;

        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel editIdPanel;
        JPanel editUsernamePanel;
        JPanel editPasswordPanel;
        tfId = new JTextField(30);
        tfSubjectName = new JTextField(30);
        tfCredits = new JTextField(30);
        // Create components
        if (oldSubject == null) {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.subject[0], "");
            editUsernamePanel = createEditRow(tfSubjectName, ColumnNameHelper.subject[1], "");
            editPasswordPanel = createEditRow(tfCredits, ColumnNameHelper.subject[2], "");
        } else {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.subject[0], oldSubject.getSubjectId());
            editUsernamePanel = createEditRow(tfSubjectName, ColumnNameHelper.subject[1], oldSubject.getSubjectName());
            editPasswordPanel = createEditRow(tfCredits,
                    ColumnNameHelper.subject[2],
                    Short.toString(oldSubject.getNumberOfCredits()));
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
            final Subject account = new Subject();
            account.setSubjectId(tfId.getText());
            account.setSubjectName(tfSubjectName.getText());
            account.setNumberOfCredits(Short.parseShort(tfCredits.getText()));

            // Begin transaction
            if (editMode == EditMode.addNew) {
                (new SubjectDAO()).insert(account);
            } else {
                (new SubjectDAO()).update(account);
            }

            // callback to parent
            saveEditingCallback.onSaved();
        }
    }
}
