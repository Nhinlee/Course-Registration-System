package view.components.ministry;

import data.dao.SubjectDAO;
import data.model.Subject;
import utils.ColumnNameHelper;
import view.base.BaseTablePanel;
import view.components.ministry.edit_panels.EditSubjectPanel;

import java.util.List;

public class SubjectsPanel extends BaseTablePanel {

    private final SubjectDAO subjectDAO = new SubjectDAO();

    public SubjectsPanel() {
        this.init();
    }

    @Override
    protected void onAddNew() {
        showEditPanel(new EditSubjectPanel(null, this));
    }

    @Override
    protected void onDelete(String id) {
        // Update to DB
        subjectDAO.delete(id);
        // Update UI
        resetTableData();
    }

    @Override
    protected void onUpdate(String id) {
        Subject subject = subjectDAO.getById(id);
        showEditPanel(new EditSubjectPanel(subject, this));
    }

    @Override
    protected void onSearch() {

    }

    @Override
    protected void resetTableData() {
        List<Subject> subjects = subjectDAO.getAll();
        model.setRowCount(0);
        model.setColumnCount(0);
        // Add column name
        for (String column : ColumnNameHelper.subject) {
            model.addColumn(column);
        }

        // Add data
        for (Subject subject : subjects) {
            model.addRow(subject.toRow());
        }
    }
}
