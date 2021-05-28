package view.base;

import utils.UIDecoratorUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTablePanel extends JPanel implements ActionListener, SaveEditingCallback {
    protected final String searchString = "Search";

    protected final String addNewString = "Add New";
    protected final String deleteString = "Delete";
    protected final String updateString = "Update";

    protected JTextField tfSearch;
    protected JButton btnSearch;
    protected List<JButton> btnFeatures = new ArrayList<>();
    protected JTable table;
    protected DefaultTableModel model;
    protected JFrame editFrame = new JFrame();

    public void init() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        final String command = e.getActionCommand();
        int selectedRow = table.getSelectedRow();

        // Add new
        if (command.equals(addNewString)) {
            onAddNew();
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
                onDelete(id);
            }
        }

        // Update
        if (command.equals(updateString)) {
            onUpdate(id);
        }

        // Search
        if (command.equals(searchString)) {
            onSearch();
        }
    }

    protected abstract void onAddNew();

    protected abstract void onDelete(String id);

    protected abstract void onUpdate(String id);

    protected abstract void onSearch();

    protected abstract void resetTableData();

    protected void showEditPanel(JPanel panel) {
        // set content pane
        editFrame.setContentPane(panel);
        // show edit frame
        editFrame.pack();
        editFrame.setVisible(true);
    }

    public void onSaved() {
        // Close edit frame
        editFrame.setVisible(false);
        // Update UI
        resetTableData();
    }
}
