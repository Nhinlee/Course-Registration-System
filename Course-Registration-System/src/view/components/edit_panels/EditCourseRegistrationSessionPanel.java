package view.components.edit_panels;

import data.dao.CourseRegistrationSessionDAO;
import data.dao.SemesterDAO;
import data.model.CourseRegistrationSession;
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

public class EditCourseRegistrationSessionPanel extends JPanel implements ActionListener {

    // Edit mode default is add new
    private final EditMode editMode;
    //
    private final String saveString = "Save";
    JTextField tfId;
    JTextField tfStartDate;
    JTextField tfEndDate;
    //
    SaveEditingCallback saveEditingCallback;

    final SemesterDAO semesterDAO = new SemesterDAO();

    public EditCourseRegistrationSessionPanel(CourseRegistrationSession oldSession, SaveEditingCallback callback) {
        editMode = oldSession == null ? EditMode.addNew : EditMode.update;
        saveEditingCallback = callback;

        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel editIdPanel;
        JPanel editUsernamePanel;
        JPanel editPasswordPanel;
        tfId = new JTextField(30);
        tfStartDate = new JTextField(30);
        tfEndDate = new JTextField(30);
        // Create components
        if (oldSession == null) {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.courseRegisSession[0], "");
            editUsernamePanel = createEditRow(tfStartDate, ColumnNameHelper.courseRegisSession[1], "");
            editPasswordPanel = createEditRow(tfEndDate, ColumnNameHelper.courseRegisSession[2], "");
        } else {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.courseRegisSession[0], oldSession.getSessionId());
            editUsernamePanel = createEditRow(tfStartDate, ColumnNameHelper.courseRegisSession[1], oldSession.getStartDate().toString());
            editPasswordPanel = createEditRow(tfEndDate, ColumnNameHelper.courseRegisSession[2], oldSession.getEndDate().toString());
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
            final CourseRegistrationSession session = new CourseRegistrationSession();
            session.setSessionId(tfId.getText());
            session.setStartDate(Date.valueOf(tfStartDate.getText()));
            session.setEndDate(Date.valueOf(tfEndDate.getText()));

            // Begin transaction
            if (editMode == EditMode.addNew) {
                final Semester currentSemester = semesterDAO.getCurrentSemester();
                session.setSemesterId(currentSemester.getSemesterId());
                (new CourseRegistrationSessionDAO()).insert(session);
            } else {
                (new CourseRegistrationSessionDAO()).update(session);
            }

            // callback to parent
            saveEditingCallback.onSaved();
        }
    }
}
