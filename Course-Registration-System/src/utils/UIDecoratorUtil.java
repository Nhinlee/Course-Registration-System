package utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UIDecoratorUtil {
    public static Border customBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 16, 10, 16)
        );
    }

    public static Font customFont() {
        return new Font("Aria", Font.PLAIN, 14);
    }

    public static JPanel createEditRow(JTextField tf, String labelText, String tfText) {
        //
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        //
        JLabel label = new JLabel(labelText);
        label.setFont(UIDecoratorUtil.customFont());
        label.setBorder(UIDecoratorUtil.customBorder());

        tf.setText(tfText);
        tf.setFont(UIDecoratorUtil.customFont());
        tf.setBorder(UIDecoratorUtil.customBorder());
        //
        panel.add(label);
        panel.add(tf);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        return panel;
    }
}
