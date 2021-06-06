package view.components;

import data.dao.ClazzDAO;
import data.model.Clazz;
import utils.ColumnNameHelper;
import view.base.BaseTablePanel;
import view.components.edit_panels.EditClazzPanel;

import java.util.List;

public class ClazzPanel extends BaseTablePanel {

    // Data Access Object
    final ClazzDAO clazzDAO = new ClazzDAO();

    public ClazzPanel() {
        this.init();
    }

    @Override
    protected void onAddNew() {
        showEditPanel(new EditClazzPanel(null, this));
    }

    @Override
    protected void onDelete(String id) {
        // Update To DB
        clazzDAO.delete(id);
        // Update UI
        resetTableData();
    }

    @Override
    protected void onUpdate(String id) {
        final Clazz clazz = clazzDAO.getById(id);
        showEditPanel(new EditClazzPanel(clazz, this));
    }

    @Override
    protected void onSearch() {

    }

    @Override
    protected void resetTableData() {
        model.setRowCount(0);
        model.setColumnCount(0);

        List<Clazz> clazzes = clazzDAO.getAll();
        // Add column name
        for (String column : ColumnNameHelper.clazz) {
            model.addColumn(column);
        }
        // Add data
        for (Clazz account : clazzes) {
            model.addRow(account.toRow());
        }
    }
}
