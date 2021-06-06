package view.components.edit_panels;

import data.dao.SemesterDAO;
import data.model.Semester;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;
import view.base.EditMode;
import view.base.SaveEditingCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import static utils.UIDecoratorUtil.createEditRow;

public class EditSemesterPanel extends JPanel implements ActionListener {

    // Edit mode default is add new
    private final EditMode editMode;
    //
    private final String saveString = "Save";
    JTextField tfId;
    JTextField tfSemesterName;
    JTextField tfSchoolYear;
    JTextField tfStartDate;
    JTextField tfEndDate;
    JTextField tfIsCurrent;
    //
    SaveEditingCallback saveEditingCallback;

    public EditSemesterPanel(Semester oldSemester, SaveEditingCallback callback) {
        editMode = oldSemester == null ? EditMode.addNew : EditMode.update;
        saveEditingCallback = callback;
        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create components
        JPanel editIdPanel;
        JPanel editUsernamePanel;
        JPanel editPasswordPanel;
        JPanel editStartDatePanel;
        JPanel editEndDatePanel;
        JPanel editIsCurrentPanel;
        tfId = new JTextField(30);
        tfSemesterName = new JTextField(30);
        tfSchoolYear = new JTextField(30);
        tfStartDate = new JTextField(30);
        tfEndDate = new JTextField(30);
        tfIsCurrent = new JTextField(30);
        if (oldSemester == null) {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.semester[0], "");
            editUsernamePanel = createEditRow(tfSemesterName, ColumnNameHelper.semester[1], "");
            editPasswordPanel = createEditRow(tfSchoolYear, ColumnNameHelper.semester[2], "");
            editStartDatePanel = createEditRow(tfStartDate, ColumnNameHelper.semester[3], "");
            editEndDatePanel = createEditRow(tfEndDate, ColumnNameHelper.semester[4], "");
            editIsCurrentPanel = createEditRow(tfIsCurrent, ColumnNameHelper.semester[5], "");
        } else {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.semester[0], oldSemester.getSemesterId());
            editUsernamePanel = createEditRow(tfSemesterName, ColumnNameHelper.semester[1], oldSemester.getSemesterName());
            editPasswordPanel = createEditRow(tfSchoolYear, ColumnNameHelper.semester[2], oldSemester.getSchoolYear());
            editStartDatePanel = createEditRow(tfStartDate, ColumnNameHelper.semester[3], String.valueOf(oldSemester.getStartDate()));
            editEndDatePanel = createEditRow(tfEndDate, ColumnNameHelper.semester[4], String.valueOf(oldSemester.getEndDate()));
            editIsCurrentPanel = createEditRow(tfIsCurrent, ColumnNameHelper.semester[5], String.valueOf(oldSemester.getIsCurrent()));
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
        add(btnSave);

        setBorder(UIDecoratorUtil.customBorder());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(saveString)) {
            final Semester semester = new Semester();
            semester.setSemesterId(tfId.getText());
            semester.setSemesterName(tfSemesterName.getText());
            semester.setSchoolYear(tfSchoolYear.getText());
            semester.setStartDate(Date.valueOf(tfStartDate.getText()));
            semester.setEndDate(Date.valueOf(tfEndDate.getText()));
            //
            short isCurrent = Short.parseShort(tfIsCurrent.getText());
            if (isCurrent != Semester.IS_CURRENT && isCurrent != Semester.IS_NOT_CURRENT) {
                isCurrent = Semester.IS_NOT_CURRENT;
            }
            semester.setIsCurrent(isCurrent);

            // Begin transaction
            if (editMode == EditMode.addNew) {
                (new SemesterDAO()).insert(semester);
            } else {
                (new SemesterDAO()).update(semester);
            }

            // callback to parent
            saveEditingCallback.onSaved();
        }
    }
}
