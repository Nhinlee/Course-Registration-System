package view.components;

import data.dao.CourseDAO;
import data.model.Clazz;
import data.model.Course;
import data.model.CourseRegistrationSession;
import utils.ColumnNameHelper;
import view.base.BaseTablePanel;
import view.components.edit_panels.EditCoursePanel;
import view.components.edit_panels.EditCourseRegistrationSessionPanel;

import java.util.List;

public class CoursesPanel extends BaseTablePanel {

    final CourseDAO courseDAO = new CourseDAO();

    public CoursesPanel() {
        this.init();
    }

    @Override
    protected void onAddNew() {
        showEditPanel(new EditCoursePanel(null, this));
    }

    @Override
    protected void onDelete(String id) {
        // Update DB
        courseDAO.delete(id);
        // Update UI
        resetTableData();
    }

    @Override
    protected void onUpdate(String id) {
        final Course course = courseDAO.getById(id);
        showEditPanel(new EditCoursePanel(course, this));
    }

    @Override
    protected void onSearch() {

    }

    @Override
    protected void resetTableData() {
        model.setRowCount(0);
        model.setColumnCount(0);

        List<Course> courses = courseDAO.getAllCourseOfCurrentSemester();
        // Add column name
        for (String column : ColumnNameHelper.course) {
            model.addColumn(column);
        }
        // Add data
        for (Course course : courses) {
            model.addRow(course.toRow());
        }
    }
}
