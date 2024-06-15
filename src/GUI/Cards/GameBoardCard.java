package GUI.Cards;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameBoardCard extends JPanel {
    private Map<Integer, JPanel> sectionsMap;

    public GameBoardCard() {
        this.setLayout(new GridLayout(3, 3));

        sectionsMap = new HashMap<>();

        for(int tableRow = 0; tableRow < 3; tableRow++) {
            for(int tableColumn = 0; tableColumn < 3; tableColumn++) {
                GUI.Components.Section newSection = new GUI.Components.Section();
                sectionsMap.put((tableRow * 3 + tableColumn), newSection);
                GameBoardCard.this.add(newSection);
            }
        }
    }
}
