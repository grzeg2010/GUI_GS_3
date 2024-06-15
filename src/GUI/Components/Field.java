package GUI.Components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Field extends JPanel {
    public Field() {
        this.setBackground(Color.red);
        Border border = new LineBorder(Color.white, 10);
        this.setBorder(border);
    }
}
