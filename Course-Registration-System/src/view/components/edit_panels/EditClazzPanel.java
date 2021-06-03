package view.components.edit_panels;

import data.dao.ClazzDAO;
import data.model.Clazz;
import utils.ColumnNameHelper;
import utils.UIDecoratorUtil;
import view.base.EditMode;
import view.base.SaveEditingCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utils.UIDecoratorUtil.createEditRow;

public class EditClazzPanel extends JPanel implements ActionListener {
    // Edit mode default is add new
    private final EditMode editMode;
    //
    private final String saveString = "Save";
    JTextField tfId;
    //
    SaveEditingCallback saveEditingCallback;

    public EditClazzPanel(Clazz oldClazz, SaveEditingCallback callback) {
        editMode = oldClazz == null ? EditMode.addNew : EditMode.update;
        saveEditingCallback = callback;
        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel editIdPanel;
        tfId = new JTextField(30);
        // Create components
        if (oldClazz == null) {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.clazz[0], "");
        } else {
            editIdPanel = createEditRow(tfId, ColumnNameHelper.clazz[0], oldClazz.getClassId());
        }

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        btnSave.setActionCommand(saveString);
        btnSave.setFont(UIDecoratorUtil.customFont());
        btnSave.setBorder(UIDecoratorUtil.customBorder());

        // Lay them out
        add(editIdPanel);
        add(btnSave);

        setBorder(UIDecoratorUtil.customBorder());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(saveString)) {
            final Clazz clazz = new Clazz();
            clazz.setClassId(tfId.getText());
            clazz.setFemaleStudents((short) 0);
            clazz.setMaleStudents((short) 0);
            // Begin transaction
            if (editMode == EditMode.addNew) {
                (new ClazzDAO()).insert(clazz);
            } else {
                (new ClazzDAO()).update(clazz);
            }

            // callback to parent
            saveEditingCallback.onSaved();
        }
    }
}
