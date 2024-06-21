package GUI.Components;

import GUI.Cards.GameBoardCard;
import GUI.Colors.Breeze;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Section extends JPanel {
    private GameBoardCard gameBoard;
    private Map<Integer, GUI.Components.Field> fieldsMap;

    public Section(GameBoardCard gameBoard) {
        this.gameBoard = gameBoard;

        this.setLayout(new GridLayout(3, 3));
        this.setBackground(Breeze.BackgroundNormal);

        fieldsMap = new HashMap<>();

        for(int tableRow = 0; tableRow < 3; tableRow++) {
            for(int tableColumn = 0; tableColumn < 3; tableColumn++) {
                GUI.Components.Field newSection = new GUI.Components.Field(gameBoard, this);
                fieldsMap.put((tableRow * 3 + tableColumn), newSection);
                Section.this.add(newSection);
            }
        }

        this.drawBorders(fieldsMap);
    }

    public void drawBorders(Map<Integer, GUI.Components.Field> componentMap) {
        Color borderColor = Breeze.ForegroundInactive;
        int borderThickness = 2;

        componentMap.get(0).setBorder(BorderFactory.createMatteBorder(0, 0, borderThickness, borderThickness, borderColor));
        componentMap.get(1).setBorder(BorderFactory.createMatteBorder(0, borderThickness, borderThickness, borderThickness, borderColor));
        componentMap.get(2).setBorder(BorderFactory.createMatteBorder(0, borderThickness, borderThickness, 0, borderColor));

        componentMap.get(3).setBorder(BorderFactory.createMatteBorder(borderThickness, 0, borderThickness, borderThickness, borderColor));
        componentMap.get(4).setBorder(BorderFactory.createMatteBorder(borderThickness, borderThickness, borderThickness, borderThickness, borderColor));
        componentMap.get(5).setBorder(BorderFactory.createMatteBorder(borderThickness, borderThickness, borderThickness, 0, borderColor));

        componentMap.get(6).setBorder(BorderFactory.createMatteBorder(borderThickness, 0, 0, borderThickness, borderColor));
        componentMap.get(7).setBorder(BorderFactory.createMatteBorder(borderThickness, borderThickness, 0, borderThickness, borderColor));
        componentMap.get(8).setBorder(BorderFactory.createMatteBorder(borderThickness, borderThickness, 0, 0, borderColor));
    }

    public void setActive() {
        this.setBackground(Breeze.ForegroundVisited);
        this.getFieldsMap().forEach((integer, field) -> {
            if(field.isEnabled())
                field.setBackground(Breeze.ForegroundVisited);
        });
    }

    // GETTERS
    public Map<Integer, Field> getFieldsMap() { return fieldsMap; }
}
