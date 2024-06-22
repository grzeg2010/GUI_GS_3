package GUI.Cards;

import Data.MovesHistoryData;
import GUI.Colors.Breeze;
import Data.MovesTableModel;
import GUI.Components.Section;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GameBoardCard extends JPanel {
    private JPanel gameBoardPanel, sidePanel;
    private MovesTableModel movesTableModel;
    private JTable movesTable;

    private JFrame mainWindow;

    private MovesHistoryData.Player currentPlayer;

    private Map<Integer, GUI.Components.Section> sectionsMap;

    public GameBoardCard(JFrame mainWindow) {
        generateLayout();
        this.currentPlayer = MovesHistoryData.Player.o;
        this.mainWindow = mainWindow;
    }

    private void generateLayout() {
        this.setLayout(new FlowLayout());

        // Game Board
        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new GridLayout(3, 3));
        gameBoardPanel.setPreferredSize(new Dimension(800, 800));

        sectionsMap = new HashMap<>();

        for(int tableRow = 0; tableRow < 3; tableRow++) {
            for(int tableColumn = 0; tableColumn < 3; tableColumn++) {
                int sectionIndex = tableRow * 3 + tableColumn;
                GUI.Components.Section newSection = new GUI.Components.Section(sectionIndex,this);
                sectionsMap.put(sectionIndex, newSection);
                gameBoardPanel.add(newSection);
            }
        }

        this.drawBorders(sectionsMap);

        // History and controls - side panel
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        movesTableModel = new MovesTableModel();
        movesTable = new JTable(movesTableModel);
        JScrollPane gameHistory = new JScrollPane(movesTable);
        sidePanel.setPreferredSize(new Dimension(300,500));

        sidePanel.add(gameHistory);

        JButton bNewGame = new JButton("Nowa gra");
        bNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                new Window();
            }
        });
        sidePanel.add(bNewGame);

        this.add(gameBoardPanel, BorderLayout.CENTER);
        this.add(sidePanel, BorderLayout.EAST);
    }

    public void saveMove(GUI.Components.Field field) {
        movesTableModel.newMove(currentPlayer, field.getParentSection().getIndex(), field.getIndex());
    }

    public void activateAllSections() {
        sectionsMap.forEach( (integer, section) -> section.setActive() );
    }

    public void deactivateAllSections() {
        sectionsMap.forEach( (integer, section) -> section.setInactive() );
    }

    public void changePlayer() {
        if(currentPlayer == MovesHistoryData.Player.o)
            setCurrentPlayer(MovesHistoryData.Player.x);
        else
            setCurrentPlayer(MovesHistoryData.Player.o);
    }

    // GETTERS
    public MovesHistoryData.Player getCurrentPlayer() { return currentPlayer; }
    public Section getSection(int index) { return sectionsMap.get(index); }

    // SETTERS
    public void setCurrentPlayer(MovesHistoryData.Player newPlayer) {
        currentPlayer = newPlayer;
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
}
