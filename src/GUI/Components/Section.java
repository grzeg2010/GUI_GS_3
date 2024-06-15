package GUI.Components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Section extends JPanel {
    private Map<Integer, JPanel> fieldsMap;

    public Section() {
        this.setLayout(new GridLayout(3, 3));
        this.setBackground(Color.red);
        Border border = new LineBorder(Color.black, 10);
        this.setBorder(border);

        fieldsMap = new HashMap<>();

        for(int tableRow = 0; tableRow < 3; tableRow++) {
            for(int tableColumn = 0; tableColumn < 3; tableColumn++) {
                GUI.Components.Field newSection = new GUI.Components.Field();
                fieldsMap.put((tableRow * 3 + tableColumn), newSection);
                Section.this.add(newSection);
            }
        }
    }
}
