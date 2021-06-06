package utils;

public class ColumnNameHelper {
    public static final String[] ministryAccount = new String[]{"Ministry ID", "Username", "Password"};
    public static final String[] subject = new String[]{"Subject ID", "Subject Name", "Number Of Credits"};
    public static final String[] semester = new String[]{"Semester ID", "Semester Name", "School Year", "Start Date", "End Date", "Is Current"};
    public static final String[] clazz = new String[]{"Class ID", "Number Of Male Students", "Number Of Female Students"};
    public static final String[] student = new String[]{"Student ID", "Username", "Password", "Full Name", "Birthday", "Address", "Class ID"};
    public static final String[] courseRegisSession = new String[]{"Session ID", "Start Date", "End Date", "Semester ID"};
    public static final String[] course = new String[]{
            "Course ID",
            "Course Name",
            "Subject",
            "Lecturers Name",
            "Max Slot",
            "Room Name",
            "Day Of Week",
            "Part Of Day",
            "Semester",
    };
    public static final String[] courseStudentView = new String[]{"Course ID", "Course Name", "Max Slot", "Day Of Week", "Part Of Day",};
}
