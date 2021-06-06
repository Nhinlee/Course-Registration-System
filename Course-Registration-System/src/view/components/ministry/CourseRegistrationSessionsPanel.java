package view.components.ministry;

import data.dao.CourseRegistrationSessionDAO;
import data.model.CourseRegistrationSession;
import utils.ColumnNameHelper;
import view.base.BaseTablePanel;
import view.components.ministry.edit_panels.EditCourseRegistrationSessionPanel;

import java.util.List;

public class CourseRegistrationSessionsPanel extends BaseTablePanel {

    final CourseRegistrationSessionDAO sessionDAO = new CourseRegistrationSessionDAO();


    public CourseRegistrationSessionsPanel() {
        this.init();
    }

    @Override
    protected void onAddNew() {
        showEditPanel(new EditCourseRegistrationSessionPanel(null, this));
    }

    @Override
    protected void onDelete(String id) {
        // Update DB
        sessionDAO.delete(id);
        // Update UI
        resetTableData();
    }

    @Override
    protected void onUpdate(String id) {
        final CourseRegistrationSession session = sessionDAO.getById(id);
        showEditPanel(new EditCourseRegistrationSessionPanel(session, this));
    }

    @Override
    protected void onSearch() {

    }

    @Override
    protected void resetTableData() {
        model.setRowCount(0);
        model.setColumnCount(0);

        List<CourseRegistrationSession> sessions = sessionDAO.getAll();
        // Add column name
        for (String column : ColumnNameHelper.courseRegisSession) {
            model.addColumn(column);
        }
        // Add data
        for (CourseRegistrationSession session : sessions) {
            model.addRow(session.toRow());
        }
    }
}
