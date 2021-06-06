package view.components.ministry.edit_panels;

import data.dao.CourseDAO;
import data.dao.SemesterDAO;
import data.dao.SubjectDAO;
import data.model.Course;
import data.model.Subject;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;
import view.base.EditMode;
import view.base.SaveEditingCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static utils.UIDecoratorUtil.createEditRow;
import static utils.UIDecoratorUtil.createEditRowWithComboBox;

public class EditCoursePanel extends JPanel implements ActionListener {

    // External DAO
    private final SubjectDAO subjectDAO = new SubjectDAO();
    private final SemesterDAO semesterDAO = new SemesterDAO();
    private final CourseDAO courseDAO = new CourseDAO();
    // Edit mode default is add new
    private final EditMode editMode;
    //
    private final String saveString = "Save";
    private final String[] stringDayOfWeek = new String[]{"1", "2", "3", "4", "5", "6", "7"};
    private final String[] stringPartOfDay = new String[]{"1", "2", "3", "4"};
    private final List<Subject> subjects = new ArrayList<>();
    JTextField tfId;
    JTextField tfCourseName;
    JComboBox cbSubject;
    JTextField tfLecturersName;
    JTextField tfMaxSlot;
    JTextField tfRoomName;
    JComboBox cbDayOfWeek;
    JComboBox cbPartOfDay;
    //
    SaveEditingCallback saveEditingCallback;

    public EditCoursePanel(Course oldCourse, SaveEditingCallback callback) {
        editMode = oldCourse == null ? EditMode.addNew : EditMode.update;
        saveEditingCallback = callback;
        if (oldCourse == null) oldCourse = new Course();
        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel editIdPanel;
        JPanel editCourseNamePanel;
        JPanel editSubjectPanel;
        JPanel editLecturersNamePanel;
        JPanel editMaxSlotPanel;
        JPanel editRoomNamePanel;
        JPanel editSemesterIdPanel;
        JPanel editDayOfWeekPanel;
        JPanel editPartOfDayPanel;
        tfId = new JTextField(30);
        tfCourseName = new JTextField(30);
        tfLecturersName = new JTextField(30);
        tfMaxSlot = new JTextField(30);
        tfRoomName = new JTextField(30);
        cbDayOfWeek = new JComboBox(stringDayOfWeek);
        cbPartOfDay = new JComboBox(stringPartOfDay);
        // Create components
        editIdPanel = createEditRow(tfId, ColumnNameHelper.course[0], oldCourse.getCourseId());
        editCourseNamePanel = createEditRow(tfCourseName, ColumnNameHelper.course[1], oldCourse.getCourseName());
        editLecturersNamePanel = createEditRow(tfLecturersName, ColumnNameHelper.course[3], oldCourse.getLecturersFullName());
        editMaxSlotPanel = createEditRow(tfMaxSlot, ColumnNameHelper.course[4], Short.valueOf(oldCourse.getMaxSlot()).toString());
        editRoomNamePanel = createEditRow(tfRoomName, ColumnNameHelper.course[5], oldCourse.getRoomName());

        editDayOfWeekPanel = createEditRowWithComboBox(cbDayOfWeek, "Day Of Week");
        editPartOfDayPanel = createEditRowWithComboBox(cbPartOfDay, "Part Of Day");

        subjects.addAll(subjectDAO.getAll());
        List<String> stringSubjects = new ArrayList<>();
        for (Subject subject : subjects) {
            stringSubjects.add(subject.getSubjectName());
        }
        cbSubject = new JComboBox(stringSubjects.toArray());
        editSubjectPanel = createEditRowWithComboBox(cbSubject, "Subject Name");

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        btnSave.setActionCommand(saveString);
        btnSave.setFont(UIDecoratorUtil.customFont());
        btnSave.setBorder(UIDecoratorUtil.customBorder());

        // Lay them out
        add(editIdPanel);
        add(editCourseNamePanel);
        add(editSubjectPanel);
        add(editLecturersNamePanel);
        add(editMaxSlotPanel);
        add(editRoomNamePanel);
        add(editDayOfWeekPanel);
        add(editPartOfDayPanel);

        add(btnSave);

        setBorder(UIDecoratorUtil.customBorder());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(saveString)) {
            Course course = new Course();
            course.setCourseId(tfId.getText());
            course.setCourseName(tfCourseName.getText());
            course.setSubject(subjects.get(cbSubject.getSelectedIndex()));
            course.setLecturersFullName(tfLecturersName.getText());
            course.setMaxSlot(Short.parseShort(tfMaxSlot.getText()));
            course.setRoomName(tfRoomName.getText());
            course.setSemester(semesterDAO.getCurrentSemester());
            course.setDayOfWeek(Short.parseShort(stringDayOfWeek[cbDayOfWeek.getSelectedIndex()]));
            course.setPartOfDay(Short.parseShort(stringPartOfDay[cbPartOfDay.getSelectedIndex()]));

            // Begin transaction
            if (editMode == EditMode.addNew) {
                courseDAO.insert(course);
            } else {
                courseDAO.update(course);
            }

            saveEditingCallback.onSaved();
        }
    }
}
