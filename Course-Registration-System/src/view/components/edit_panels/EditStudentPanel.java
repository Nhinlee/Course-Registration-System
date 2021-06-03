package view.components.edit_panels;

import data.dao.StudentDAO;
import data.model.Student;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;
import view.base.EditMode;
import view.base.SaveEditingCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import static utils.UIDecoratorUtil.createEditRow;

public class EditStudentPanel extends JPanel implements ActionListener {

    // Edit mode default is add new
    private final EditMode editMode;
    //
    private final String saveString = "Save";
    JTextField tfId;
    JTextField tfUsername;
    JTextField tfPassword;
    JTextField tfFullName;
    JTextField tfBirthDay;
    JTextField tfAddress;
    JTextField tfClassId;
    //
    SaveEditingCallback saveEditingCallback;

    public EditStudentPanel(Student oldStudent, SaveEditingCallback callback) {
        editMode = oldStudent == null ? EditMode.addNew : EditMode.update;
        saveEditingCallback = callback;
        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Create components
        JPanel editIdPanel;
        JPanel editUsernamePanel;
        JPanel editPasswordPanel;
        JPanel editStartDatePanel;
        JPanel editEndDatePanel;
        JPanel editIsCurrentPanel;
        JPanel editClassIdPanel;
        tfId = new JTextField(30);
        tfUsername = new JTextField(30);
        tfPassword = new JTextField(30);
        tfFullName = new JTextField(30);
        tfBirthDay = new JTextField(30);
        tfAddress = new JTextField(30);
        tfClassId = new JTextField(30);
        if (oldStudent == null) {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.student[0], "");
            editUsernamePanel = createEditRow(tfUsername, ColumnNameHelper.student[1], "");
            editPasswordPanel = createEditRow(tfPassword, ColumnNameHelper.student[2], "");
            editStartDatePanel = createEditRow(tfFullName, ColumnNameHelper.student[3], "");
            editEndDatePanel = createEditRow(tfBirthDay, ColumnNameHelper.student[4], "");
            editIsCurrentPanel = createEditRow(tfAddress, ColumnNameHelper.student[5], "");
            editClassIdPanel = createEditRow(tfClassId, ColumnNameHelper.student[6], "");
        } else {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.semester[0], oldStudent.getStudentId());
            editUsernamePanel = createEditRow(tfUsername, ColumnNameHelper.student[1], oldStudent.getUsername());
            editPasswordPanel = createEditRow(tfPassword, ColumnNameHelper.student[2], oldStudent.getPassword());
            editStartDatePanel = createEditRow(tfFullName, ColumnNameHelper.student[3], String.valueOf(oldStudent.getFullName()));
            editEndDatePanel = createEditRow(tfBirthDay, ColumnNameHelper.student[4], String.valueOf(oldStudent.getBirthDay()));
            editIsCurrentPanel = createEditRow(tfAddress, ColumnNameHelper.student[5], String.valueOf(oldStudent.getAddress()));
            editClassIdPanel = createEditRow(tfClassId, ColumnNameHelper.student[6], String.valueOf(oldStudent.getClassId()));
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
        add(editStartDatePanel);
        add(editEndDatePanel);
        add(editIsCurrentPanel);
        add(editClassIdPanel);
        add(btnSave);

        setBorder(UIDecoratorUtil.customBorder());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(saveString)) {
            final Student student = new Student();
            student.setStudentId(tfId.getText());
            student.setUsername(tfUsername.getText());
            student.setPassword(tfPassword.getText());
            student.setFullName(tfFullName.getText());
            student.setBirthDay(Date.valueOf(tfBirthDay.getText()));
            student.setAddress(tfAddress.getText());
            student.setClassId(tfClassId.getText());
            // Begin transaction
            if (editMode == EditMode.addNew) {
                (new StudentDAO()).insert(student);
            } else {
                (new StudentDAO()).update(student);
            }

            // callback to parent
            saveEditingCallback.onSaved();
        }
    }
}
