package view.components.student;

import data.dao.CourseDAO;
import data.model.Course;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegisterCoursePanel extends JPanel implements ActionListener {

    // Student Id
    final String studentId = "1712640";

    final String strRegisterButton = "Register This Course";

    // DAO
    final CourseDAO courseDAO = new CourseDAO();

    // Components
    private DefaultTableModel registeredCourseModel;
    private DefaultTableModel coursesModel;
    private JTable coursesTable;
    private JTable registeredCoursesTable;
    private JButton registerButton;

    public RegisterCoursePanel() {
        // Layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create Components

        // Title
        JLabel titleLabel = new JLabel("Register Course");
        titleLabel.setFont(new Font("Aria", Font.BOLD, 28));
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.PAGE_START);
        // ------------------------------------------------------------------------

        // Label
        JLabel registeredCourseLabel = new JLabel("Registered Course");
        registeredCourseLabel.setFont(new Font("Aria", Font.BOLD, 18));
        registeredCourseLabel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        // Course Table
        registeredCourseModel = new DefaultTableModel();
        registeredCoursesTable = new JTable(registeredCourseModel);
        // -- -- Logic interactive
        registeredCoursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        registeredCoursesTable.setRowSelectionAllowed(true);
        // -- -- UI
        registeredCoursesTable.setFillsViewportHeight(true);
        registeredCoursesTable.setFont(UIDecoratorUtil.customFont());
        registeredCoursesTable.getTableHeader().setFont(UIDecoratorUtil.customFont());
        registeredCoursesTable.getTableHeader().setPreferredSize(new Dimension(0, 32));
        registeredCoursesTable.setRowHeight(32);
        //
        JPanel registeredCoursePanel = new JPanel(new BorderLayout());
        registeredCoursePanel.add(registeredCourseLabel, BorderLayout.PAGE_START);
        registeredCoursePanel.add(new JScrollPane(registeredCoursesTable), BorderLayout.CENTER);
        registeredCoursePanel.setPreferredSize(new Dimension(1280, 200));
        // ------------------------------------------------------------------------------

        // Label
        JLabel courseLabel = new JLabel("Courses");
        courseLabel.setFont(new Font("Aria", Font.BOLD, 18));
        courseLabel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        // Course Table
        coursesModel = new DefaultTableModel();
        coursesTable = new JTable(coursesModel);
        resetCourseTableData();
        // -- -- Logic interactive
        coursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        coursesTable.setRowSelectionAllowed(true);
        // -- -- UI
        coursesTable.setFillsViewportHeight(true);
        coursesTable.setFont(UIDecoratorUtil.customFont());
        coursesTable.getTableHeader().setFont(UIDecoratorUtil.customFont());
        coursesTable.getTableHeader().setPreferredSize(new Dimension(0, 32));
        coursesTable.setRowHeight(32);
        //
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(courseLabel, BorderLayout.PAGE_START);
        tablePanel.add(new JScrollPane(coursesTable), BorderLayout.CENTER);
        // ------------------------------------------------------------------------------

        // Register Button
        registerButton = new JButton(strRegisterButton);
        registerButton.setActionCommand(strRegisterButton);
        registerButton.addActionListener(this);
        registerButton.setBorder(UIDecoratorUtil.customBorder());
        registerButton.setFont(UIDecoratorUtil.customFont());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(16,16,16,0));
        buttonPanel.add(registerButton, BorderLayout.LINE_END);

        // Lay them out
        add(titlePanel);
        add(registeredCoursePanel);
        add(tablePanel);
        add(buttonPanel);

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setPreferredSize(new Dimension(1280, 900));
        // -------------------------------------------------------------------------------
    }

    private void resetCourseTableData() {
        coursesModel.setRowCount(0);
        coursesModel.setColumnCount(0);

        List<Course> courses = courseDAO.getAllCourseRemainByStudentId(this.studentId);
        // Add column name
        for (String column : ColumnNameHelper.courseStudentView) {
            coursesModel.addColumn(column);
        }
        // Add data
        for (Course course : courses) {
            coursesModel.addRow(course.toStudentViewRow());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
