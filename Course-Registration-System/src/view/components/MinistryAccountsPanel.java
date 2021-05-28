package view.components;

import data.dao.MinistryAccountDAO;
import data.model.MinistryAccount;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;
import view.base.BaseTablePanel;
import view.base.EditMinistryAccountPanel;
import view.base.SaveEditingCallback;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MinistryAccountsPanel extends BaseTablePanel {

    // Data Access Object
    private final MinistryAccountDAO ministryAccountDAO = new MinistryAccountDAO();

    public MinistryAccountsPanel() {
        this.init();
    }

    @Override
    protected void onAddNew() {
        showEditPanel(new EditMinistryAccountPanel(null, this));
    }

    @Override
    protected void onDelete(String id) {
        // Update DB
        ministryAccountDAO.delete(id);
        // Update UI
        resetTableData();
    }

    @Override
    protected void onUpdate(String id) {
        final MinistryAccount account = ministryAccountDAO.getById(id);
        showEditPanel(new EditMinistryAccountPanel(account, this));
    }

    @Override
    protected void onSearch() {

    }

    @Override
    public void onSaved() {
        // Close edit frame
        editFrame.setVisible(false);
        // Update UI
        resetTableData();
    }

    @Override
    protected void resetTableData() {
        List<MinistryAccount> accounts = ministryAccountDAO.getAll();
        model.setRowCount(0);
        model.setColumnCount(0);
        // Add column name
        for (String column : ColumnNameHelper.ministryAccount) {
            model.addColumn(column);
        }
        // Add data
        for (MinistryAccount account : accounts) {
            model.addRow(account.toRow());
        }
    }
}