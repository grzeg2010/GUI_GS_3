package GUI.Cards;

import GUI.Colors.Breeze;
import GUI.Components.Field;
import GUI.Components.Section;

import javax.swing.*;
import javax.swing.text.html.ListView;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameBoardCard extends JPanel {
    private JPanel gameBoardPanel, sidePanel;
    private JList<String> movesList;

    private Map<Integer, GUI.Components.Section> sectionsMap;

    public GameBoardCard() {
        generateLayout();

        sectionsMap.get(4).getFieldsMap().get(4).setX();
        sectionsMap.get(4).getFieldsMap().get(5).setO();
        sectionsMap.get(4).setActive();

        sectionsMap.get(7).getFieldsMap().get(2).setO();
        sectionsMap.get(2).getFieldsMap().get(6).setX();
    }

    private void generateLayout() {
        this.setLayout(new BorderLayout());

        // Game Board
        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new GridLayout(3, 3));
        gameBoardPanel.setSize(800 , 800);
        gameBoardPanel.setPreferredSize(new Dimension(800, 800));

        sectionsMap = new HashMap<>();

        for(int tableRow = 0; tableRow < 3; tableRow++) {
            for(int tableColumn = 0; tableColumn < 3; tableColumn++) {
                GUI.Components.Section newSection = new GUI.Components.Section(this);
                sectionsMap.put((tableRow * 3 + tableColumn), newSection);
                gameBoardPanel.add(newSection);
            }
        }

        this.drawBorders(sectionsMap);

        // History and controls - side panel
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        movesList = new JList<>();
        JScrollPane gameHistory = new JScrollPane(movesList);
        gameHistory.setSize(300,800);

        sidePanel.add(gameHistory);

        this.add(gameBoardPanel, BorderLayout.CENTER);
        this.add(sidePanel, BorderLayout.EAST);
    }

    private void drawBorders(Map<Integer, GUI.Components.Section> componentMap) {
        Color borderColor = Breeze.BackgroundAlternate;
        int borderThickness = 6;

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

    public void saveMove(GUI.Components.Field field) {
        movesList.add(field);
        movesList.repaint();
    }
}
