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
}
