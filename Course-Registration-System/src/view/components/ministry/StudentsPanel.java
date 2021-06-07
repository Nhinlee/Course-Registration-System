package view.components.ministry;

import data.dao.ClazzDAO;
import data.dao.StudentDAO;
import data.model.Student;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;
import view.base.BaseTablePanel;
import view.components.ministry.edit_panels.EditStudentPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class StudentsPanel extends BaseTablePanel {

    final StudentDAO studentDAO = new StudentDAO();
    final ClazzDAO clazzDAO = new ClazzDAO();

    //
    final List<String> classIds = new ArrayList<>();

    public StudentsPanel() {
        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Add ComboBox for choose what class ID stand for
        getAllClassID();
        JComboBox cbClassList = new JComboBox(classIds.toArray());
        cbClassList.setSelectedIndex(0);
        cbClassList.setBorder(BorderFactory.createEmptyBorder(16, 4, 32, 4));
        cbClassList.setFont(UIDecoratorUtil.customFont());
        cbClassList.addActionListener(this);

        //
        add(cbClassList);

        // Init Main Components
        this.init();
    }

    private void getAllClassID() {
        final List<String> allClassIds = clazzDAO.getAllClassID();
        this.classIds.addAll(allClassIds);
    }

    @Override
    protected void onAddNew() {
        showEditPanel(new EditStudentPanel(null, this));
    }

    @Override
    protected void onDelete(String id) {
        // Update to DB
        studentDAO.delete(id);
        // Update UI
        resetTableData();
    }

    @Override
    protected void onUpdate(String id) {
        Student student = studentDAO.getById(id);
        showEditPanel(new EditStudentPanel(student, this));
    }

    @Override
    protected void onSearch(String textSearch) {
        model.setRowCount(0);
        model.setColumnCount(0);

        List<Student> students = studentDAO.getBySearchText(textSearch);
        // Add column name
        for (String column : ColumnNameHelper.student) {
            model.addColumn(column);
        }
        // Add data
        for (Student student : students) {
            model.addRow(student.toRow());
        }
    }

    @Override
    protected void resetTableData() {
        model.setRowCount(0);
        model.setColumnCount(0);

        List<Student> students = studentDAO.getAll();
        // Add column name
        for (String column : ColumnNameHelper.student) {
            model.addColumn(column);
        }
        // Add data
        for (Student student : students) {
            model.addRow(student.toRow());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        // Event from ComboBox
        try {
            JComboBox cbClassList = (JComboBox) e.getSource();
            final String selectedClassId = classIds.get(cbClassList.getSelectedIndex());
            resetTableDataWithClassIdFilter(selectedClassId);
        } catch (ClassCastException exception) {
        }
    }

    private void resetTableDataWithClassIdFilter(String classId) {
        model.setRowCount(0);
        model.setColumnCount(0);

        List<Student> students = studentDAO.getStudentByClassId(classId);
        // Add column name
        for (String column : ColumnNameHelper.student) {
            model.addColumn(column);
        }
        // Add data
        for (Student student : students) {
            model.addRow(student.toRow());
        }
    }
}
