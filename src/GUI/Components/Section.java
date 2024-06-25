package GUI.Components;

import Data.MovesHistoryData;
import GUI.Cards.GameBoardCard;
import GUI.Colors.Breeze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Section extends JPanel  {
    private final int index;
    private GameBoardCard gameBoard;
    private Map<Integer, GUI.Components.Field> fieldsMap;

    private boolean isFocused;
    private MovesHistoryData.Player wonBy;

    private JPanel nineFieldsCard, winnerCard;
    private CardLayout cardLayout;

    public Section(int index, GameBoardCard gameBoard) {
        this.index = index;
        this.gameBoard = gameBoard;
        this.isFocused = false;
        this.wonBy = null;
        this.cardLayout = new CardLayout();
        this.nineFieldsCard = new JPanel();
        this.winnerCard = new JPanel();

        this.setLayout(cardLayout);

        nineFieldsCard.setLayout(new GridLayout(3, 3));
        nineFieldsCard.setBackground(Breeze.BackgroundNormal);

        this.add(nineFieldsCard);

        winnerCard.setLayout(null);
        this.add(winnerCard);

        fieldsMap = new HashMap<>();

        for(int tableRow = 0; tableRow < 3; tableRow++) {
            for(int tableColumn = 0; tableColumn < 3; tableColumn++) {
                int fieldIndex = tableRow * 3 + tableColumn;
                GUI.Components.Field newField = new GUI.Components.Field(fieldIndex, gameBoard, this);
                fieldsMap.put(fieldIndex, newField);
                nineFieldsCard.add(newField);
            }
        }

        this.drawBorders(fieldsMap);
    }

    public void setFocused() {
        if(this.wonBy == null) {
            this.isFocused = true;
            this.gameBoard.setFocusedSection(this);
            this.getFieldsMap().forEach((integer, field) -> {
                if (field.getSelectedBy() == null) {
                    field.setBackground(Breeze.ForegroundVisited);
                    field.setEnabled(true);
                }
            });
        }
    }

    public void setInactive() {
        this.isFocused = false;
        this.gameBoard.setFocusedSection(null);
        this.getFieldsMap().forEach((integer, field) -> {
            if(field.getSelectedBy() == null) {
                field.setBackground(Breeze.BackgroundNormal);
                field.setEnabled(false);
            }
        });
    }

    public void setActive() {
        if(this.wonBy == null) {
            this.getFieldsMap().forEach((integer, field) -> {
                if (field.getSelectedBy() == null) {
                    field.setBackground(Breeze.ForegroundPositive);
                    field.setEnabled(true);
                }
            });
        }
    }

    public void validateScore(MovesHistoryData.Player currentPlayer) {
        if (
            // ROWS
                fieldsMap.get(0).getSelectedBy() == currentPlayer && fieldsMap.get(1).getSelectedBy() == currentPlayer && fieldsMap.get(2).getSelectedBy() == currentPlayer ||
                        fieldsMap.get(3).getSelectedBy() == currentPlayer && fieldsMap.get(4).getSelectedBy() == currentPlayer && fieldsMap.get(5).getSelectedBy() == currentPlayer ||
                        fieldsMap.get(6).getSelectedBy() == currentPlayer && fieldsMap.get(7).getSelectedBy() == currentPlayer && fieldsMap.get(8).getSelectedBy() == currentPlayer ||
                        // COLUMNS
                        fieldsMap.get(0).getSelectedBy() == currentPlayer && fieldsMap.get(3).getSelectedBy() == currentPlayer && fieldsMap.get(6).getSelectedBy() == currentPlayer ||
                        fieldsMap.get(1).getSelectedBy() == currentPlayer && fieldsMap.get(4).getSelectedBy() == currentPlayer && fieldsMap.get(7).getSelectedBy() == currentPlayer ||
                        fieldsMap.get(2).getSelectedBy() == currentPlayer && fieldsMap.get(5).getSelectedBy() == currentPlayer && fieldsMap.get(8).getSelectedBy() == currentPlayer ||
                        // SLANT
                        fieldsMap.get(0).getSelectedBy() == currentPlayer && fieldsMap.get(4).getSelectedBy() == currentPlayer && fieldsMap.get(8).getSelectedBy() == currentPlayer ||
                        fieldsMap.get(2).getSelectedBy() == currentPlayer && fieldsMap.get(4).getSelectedBy() == currentPlayer && fieldsMap.get(6).getSelectedBy() == currentPlayer
        ) {
            this.wonBy = currentPlayer;
            JLabel winnerIcon = new JLabel();
            if(this.wonBy == MovesHistoryData.Player.o) {
                winnerCard.setBackground(Breeze.ForegroundLink);
                winnerIcon.setIcon(new ImageIcon(GUI.Components.AbstractIcons.generateCircleIcon(100)));
            }
            else {
                winnerCard.setBackground(Breeze.ForegroundNegative);
                winnerIcon.setIcon(new ImageIcon(GUI.Components.AbstractIcons.generateCrossIcon(100)));
            }
            Insets insets = winnerCard.getInsets();
            Dimension size = winnerIcon.getPreferredSize();
            winnerIcon.setBounds(75 + insets.left, 75 + insets.top,
                    size.width, size.height);
            winnerCard.add(winnerIcon);
            cardLayout.last(this);
            this.addMouseListener(hoverAction);
        } else if (
                fieldsMap.get(0).getSelectedBy() != null && fieldsMap.get(1).getSelectedBy() != null && fieldsMap.get(2).getSelectedBy() != null &&
                        fieldsMap.get(3).getSelectedBy() != null && fieldsMap.get(4).getSelectedBy() != null && fieldsMap.get(5).getSelectedBy() != null &&
                        fieldsMap.get(6).getSelectedBy() != null && fieldsMap.get(7).getSelectedBy() != null && fieldsMap.get(8).getSelectedBy() != null
        ) {
            this.wonBy = MovesHistoryData.Player.Draw;
            JLabel winnerIcon = new JLabel();
            winnerCard.setBackground(Breeze.ForegroundNeutral);
            winnerIcon.setIcon(new ImageIcon(GUI.Components.AbstractIcons.generateDashIcon(100)));
            Insets insets = winnerCard.getInsets();
            Dimension size = winnerIcon.getPreferredSize();
            winnerIcon.setBounds(75 + insets.left, 75 + insets.top,
                    size.width, size.height);
            winnerCard.add(winnerIcon);
            cardLayout.last(this);
            this.addMouseListener(hoverAction);
        }
    }

    MouseAdapter hoverAction = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent me) {
            cardLayout.first(Section.this);
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            cardLayout.last(Section.this);
        }
    };

    // GETTERS
    public int getIndex() { return index; }
    public Map<Integer, Field> getFieldsMap() { return fieldsMap; }
    public MovesHistoryData.Player getWinner() { return wonBy; }
    public boolean getIsFocused() { return isFocused; }
    public GameBoardCard getGameBoard() { return gameBoard; }

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
}
