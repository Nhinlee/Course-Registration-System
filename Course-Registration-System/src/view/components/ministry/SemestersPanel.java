package view.components.ministry;

import data.dao.SemesterDAO;
import data.model.Semester;
import utils.ColumnNameHelper;
import view.base.BaseTablePanel;
import view.components.ministry.edit_panels.EditSemesterPanel;

import java.util.List;

public class SemestersPanel extends BaseTablePanel {

    // Data Access Object
    private final SemesterDAO semesterDAO = new SemesterDAO();

    public SemestersPanel() {
        this.init();
    }

    @Override
    protected void onAddNew() {
        showEditPanel(new EditSemesterPanel(null, this));
    }

    @Override
    protected void onDelete(String id) {
        // Update to DB
        semesterDAO.delete(id);
        // Update UI
        resetTableData();
    }

    @Override
    protected void onUpdate(String id) {
        Semester semester = semesterDAO.getById(id);
        showEditPanel(new EditSemesterPanel(semester, this));
    }

    @Override
    protected void onSearch(String textSearch) {

    }

    @Override
    protected void resetTableData() {
        List<Semester> semesters = semesterDAO.getAll();
        model.setRowCount(0);
        model.setColumnCount(0);
        // Add column name
        for (String column : ColumnNameHelper.semester) {
            model.addColumn(column);
        }

        // Add data
        for (Semester semester : semesters) {
            model.addRow(semester.toRow());
        }
    }
}
