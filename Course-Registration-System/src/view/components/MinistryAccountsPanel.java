package view.components;

import data.dao.MinistryAccountDAO;
import data.model.MinistryAccount;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;
import view.base.EditMinistryAccountPanel;
import view.base.SaveEditingCallback;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MinistryAccountsPanel extends JPanel implements ActionListener, SaveEditingCallback {

    private final String searchString = "Search";

    private final String addNewString = "Add New";
    private final String deleteString = "Delete";
    private final String updateString = "Update";
    private final String resetString = "Reset Password";

    private JTextField tfSearch;
    private JButton btnSearch;
    private List<JButton> btnFeatures = new ArrayList<>();
    private JTable table;
    private DefaultTableModel model;
    private JFrame editFrame = new JFrame();

    // Data Access Object
    private final MinistryAccountDAO ministryAccountDAO = new MinistryAccountDAO();


    public MinistryAccountsPanel() {
        // set layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // ------------------------------------------------------------------------------

        // create components
        // -- Search bar panel
        JPanel searchBarPanel = new JPanel(new FlowLayout());
        //
        tfSearch = new JTextField("search", 50);
        tfSearch.setBorder(UIDecoratorUtil.customBorder());
        tfSearch.setFont(UIDecoratorUtil.customFont());
        //
        btnSearch = new JButton(searchString);
        btnSearch.addActionListener(this);
        btnSearch.setActionCommand(searchString);
        btnSearch.setBorder(UIDecoratorUtil.customBorder());
        btnSearch.setFont(UIDecoratorUtil.customFont());
        //
        searchBarPanel.add(tfSearch);
        searchBarPanel.add(btnSearch);
        // ------------------------------------------------------------------------------

        // -- Feature button panel
        JPanel btnFeaturesPanel = new JPanel(new FlowLayout());
        //
        String[] btnTexts = {
                addNewString,
                updateString,
                resetString,
                deleteString,
        };
        for (String text : btnTexts) {
            //
            JButton button = new JButton(text);
            button.setActionCommand(text);
            button.addActionListener(this);
            button.setBorder(UIDecoratorUtil.customBorder());
            button.setFont(UIDecoratorUtil.customFont());
            //
            btnFeatures.add(button);
            //
            btnFeaturesPanel.add(button);
        }
        // ------------------------------------------------------------------------------

        // -- Table panel
        model = new DefaultTableModel();
        table = new JTable(model);
        resetTableData();
        // -- -- Logic interactive
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        // -- -- UI
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Aria", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Aria", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(0, 32));
        table.setRowHeight(32);
        //
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        // ------------------------------------------------------------------------------


        // lay them out
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(searchBarPanel, BorderLayout.LINE_START);
        headerPanel.add(btnFeaturesPanel, BorderLayout.LINE_END);

        add(headerPanel);
        add(tablePanel);

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setPreferredSize(new Dimension(1280, 720));
        // ------------------------------------------------------------------------------
    }


    private void resetTableData() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        final String command = e.getActionCommand();
        int selectedRow = table.getSelectedRow();

        // Add new
        if (command.equals(addNewString)) {
            showEditPanel(null);
        }

        if (selectedRow < 0) return;
        final String id = (String) table.getValueAt(selectedRow, 0);

        // Delete
        if (command.equals(deleteString)) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Do you wanna delete this account?",
                    "Delete!",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                // Update DB
                ministryAccountDAO.delete(id);
                // Update UI
                model.removeRow(selectedRow);
            }
        }

        // Update
        if (command.equals(updateString)) {
            showEditPanel(ministryAccountDAO.getById(id));
        }
    }

    private void showEditPanel(MinistryAccount account) {
        // set content pane
        editFrame.setContentPane(new EditMinistryAccountPanel(account, this));
        // show edit frame
        editFrame.pack();
        editFrame.setVisible(true);
    }

    @Override
    public void onSaved() {
        // Close edit frame
        editFrame.setVisible(false);
        // Update UI
        resetTableData();
    }
}