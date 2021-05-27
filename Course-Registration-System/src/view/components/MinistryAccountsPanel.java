package view.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MinistryAccountsPanel extends JPanel implements ActionListener {

    private final String searchString = "Search";

    private final String addNewString = "Add New";
    private final String deleteString = "Delete";
    private final String editString = "Edit";
    private final String resetString = "Reset Password";
    private final String[] btnTexts = {
            addNewString,
            editString,
            resetString,
            deleteString,
    };

    private String[] columnNames = {
            "id",
            "First Name",
            "Last Name",
            "Password",
    };

    private Object[][] data = {
            {"1", "Nhin", "Le Chi", 123456,},
            {"2", "Nhu", "Le Chi", 123456,},
            {"3", "Cao", "Le Chi", 123456,},
            {"4", "Nhat", "Le Chi", 123456,},
    };


    private JTextField tfSearch;
    private JButton btnSearch;
    private List<JButton> btnFeatures = new ArrayList<>();
    private JTable table;


    public MinistryAccountsPanel() {
        // set layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // ------------------------------------------------------------------------------

        // create components
        // -- Search bar panel
        JPanel searchBarPanel = new JPanel(new FlowLayout());
        //
        tfSearch = new JTextField("search", 50);
        tfSearch.setBorder(myButtonBorder());
        tfSearch.setFont(myFont());
        //
        btnSearch = new JButton(searchString);
        btnSearch.addActionListener(this);
        btnSearch.setActionCommand(searchString);
        btnSearch.setBorder(myButtonBorder());
        btnSearch.setFont(myFont());
        //
        searchBarPanel.add(tfSearch);
        searchBarPanel.add(btnSearch);
        // ------------------------------------------------------------------------------

        // -- Feature button panel
        JPanel btnFeaturesPanel = new JPanel(new FlowLayout());
        //
        for (String text : btnTexts) {
            //
            JButton button = new JButton(text);
            button.setActionCommand(text);
            button.addActionListener(this);
            button.setBorder(myButtonBorder());
            button.setFont(myFont());
            //
            btnFeatures.add(button);
            //
            btnFeaturesPanel.add(button);
        }
        // ------------------------------------------------------------------------------

        // -- Table panel
        table = new JTable(getTableModel());
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



    private DefaultTableModel getTableModel() {
        DefaultTableModel model = new DefaultTableModel();

        // Add column name
        for (String column : columnNames) {
            model.addColumn(column);
        }

        // Add data
        for (Object[] row : data) {
            model.addRow(row);
        }

        return model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String command = e.getActionCommand();
        int column = 0;
        int row = table.getSelectedRow();

        if (command == deleteString) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Do you wanna delete this account?",
                    "Delete!",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION && row >= 0) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(row);
            }
        }
    }

    // Decorate

    private Border myButtonBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 16, 10, 16)
        );
    }

    private Font myFont() {
        return new Font("Aria", Font.PLAIN, 14);
    }
}