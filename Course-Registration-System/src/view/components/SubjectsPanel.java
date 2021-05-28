package view.components;

import data.dao.SubjectDAO;
import data.model.Subject;
import utils.ColumnNameHelper;
import view.base.BaseTablePanel;

import java.util.List;

public class SubjectsPanel extends BaseTablePanel {

    private final SubjectDAO subjectDAO = new SubjectDAO();

    public SubjectsPanel() {
        this.init();
    }

    @Override
    protected void onAddNew() {

    }

    @Override
    protected void onDelete(String id) {

    }

    @Override
    protected void onUpdate(String id) {

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

    @Override
    public void onSaved() {

    }
}
