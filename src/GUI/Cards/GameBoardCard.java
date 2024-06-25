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
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class GameBoardCard extends JPanel {
    private JPanel gameBoardPanel, sidePanel;
    private MovesTableModel movesTableModel;
    private JTable movesTable;

    private JFrame mainWindow;
    private JLabel lWinner;

    private MovesHistoryData.Player currentPlayer;
    private MovesHistoryData.Player computerPlayer;

    private Map<Integer, GUI.Components.Section> sectionsMap;

    private boolean isFinished;
    private Section focusedSection;

    public GameBoardCard(JFrame mainWindow) {
        generateLayout();
        this.currentPlayer = MovesHistoryData.Player.o;
        this.computerPlayer = null;
        this.mainWindow = mainWindow;
        this.isFinished = false;
        this.focusedSection = null;
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

        lWinner = new JLabel("Zwycięzca:");
        sidePanel.add(lWinner);

        JButton bNewGame = new JButton("Nowa gra");
        bNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.dispose();
                new Window();
            }
        });
        sidePanel.add(bNewGame);

        InputMap inputMap = this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "press1");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "press2");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "press3");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "press4");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "press5");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "press6");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "press7");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "press8");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), "press9");

        ActionMap actionMap = this.getActionMap();
        actionMap.put("press1", new NumberPressedAction(1, this));
        actionMap.put("press2", new NumberPressedAction(2, this));
        actionMap.put("press3", new NumberPressedAction(3, this));
        actionMap.put("press4", new NumberPressedAction(4, this));
        actionMap.put("press5", new NumberPressedAction(5, this));
        actionMap.put("press6", new NumberPressedAction(6, this));
        actionMap.put("press7", new NumberPressedAction(7, this));
        actionMap.put("press8", new NumberPressedAction(8, this));
        actionMap.put("press9", new NumberPressedAction(9, this));

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
    public MovesHistoryData.Player getComputerPlayer() { return computerPlayer; }
    public Section getSection(int index) { return sectionsMap.get(index); }
    public Section getFocusedSection() { return focusedSection; }

    // SETTERS
    public void setCurrentPlayer(MovesHistoryData.Player newPlayer) { currentPlayer = newPlayer; }
    public void setComputerPlayer(MovesHistoryData.Player newComputerPlayer) { this.computerPlayer = newComputerPlayer; }
    public void setFocusedSection(Section newSection) { focusedSection = newSection; }
    public void setLabelWinner(MovesHistoryData.Player winnerPlayer) {
        switch (winnerPlayer) {
            case o -> {
                lWinner.setText("Zwycięzca: O");
                lWinner.setForeground(Breeze.ForegroundLink);
            }
            case x -> {
                lWinner.setText("Zwycięzca: X");
                lWinner.setForeground(Breeze.ForegroundNegative);
            }
            default -> {
                lWinner.setText("Remis");
                lWinner.setForeground(Breeze.ForegroundNeutral);
            }
        }
    }

    public void activateComputerPlayer(MovesHistoryData.Player newComputerPlayer) {
        setComputerPlayer(newComputerPlayer);
        if(computerPlayer == currentPlayer) {
            computerMove();
        }
    }

    public void computerMove() {
        int randomNumber = (int)(Math.random() * 9);
        // System.out.println(randomNumber);
        if(focusedSection != null) {
            if(this.getFocusedSection().getFieldsMap().get(randomNumber).getSelectedBy() == null)
                this.getFocusedSection().getFieldsMap().get(randomNumber).setClicked();
            else
                computerMove();
        } else {
            if(this.getSection(randomNumber).getWinner() == null) {
                this.deactivateAllSections();
                this.getSection(randomNumber).setFocused();
                computerMove();
            }
            else
                computerMove();
        }
    }

    public void validateScore(MovesHistoryData.Player currentPlayer) {
        if(!isFinished) {
            if (
                // ROWS
                    sectionsMap.get(0).getWinner() == currentPlayer && sectionsMap.get(1).getWinner() == currentPlayer && sectionsMap.get(2).getWinner() == currentPlayer ||
                            sectionsMap.get(3).getWinner() == currentPlayer && sectionsMap.get(4).getWinner() == currentPlayer && sectionsMap.get(5).getWinner() == currentPlayer ||
                            sectionsMap.get(6).getWinner() == currentPlayer && sectionsMap.get(7).getWinner() == currentPlayer && sectionsMap.get(8).getWinner() == currentPlayer ||
                            // COLUMNS
                            sectionsMap.get(0).getWinner() == currentPlayer && sectionsMap.get(3).getWinner() == currentPlayer && sectionsMap.get(6).getWinner() == currentPlayer ||
                            sectionsMap.get(1).getWinner() == currentPlayer && sectionsMap.get(4).getWinner() == currentPlayer && sectionsMap.get(7).getWinner() == currentPlayer ||
                            sectionsMap.get(2).getWinner() == currentPlayer && sectionsMap.get(5).getWinner() == currentPlayer && sectionsMap.get(8).getWinner() == currentPlayer ||
                            // SLANT
                            sectionsMap.get(0).getWinner() == currentPlayer && sectionsMap.get(4).getWinner() == currentPlayer && sectionsMap.get(8).getWinner() == currentPlayer ||
                            sectionsMap.get(2).getWinner() == currentPlayer && sectionsMap.get(4).getWinner() == currentPlayer && sectionsMap.get(6).getWinner() == currentPlayer
            ) {
                this.setLabelWinner(currentPlayer);
                JDialog winnerDialog = new JDialog();
                winnerDialog.setSize(new Dimension(400, 400));
                winnerDialog.add(new JLabel("Zwycięzca: " + currentPlayer));
                winnerDialog.setVisible(true);
                this.isFinished = true;
            } else if (
                    sectionsMap.get(0).getWinner() != null && sectionsMap.get(1).getWinner() != null && sectionsMap.get(2).getWinner() != null &&
                            sectionsMap.get(3).getWinner() != null && sectionsMap.get(4).getWinner() != null && sectionsMap.get(5).getWinner() != null &&
                            sectionsMap.get(6).getWinner() != null && sectionsMap.get(7).getWinner() != null && sectionsMap.get(8).getWinner() != null
            ) {
                this.setLabelWinner(MovesHistoryData.Player.Draw);
                JDialog winnerDialog = new JDialog();
                winnerDialog.setSize(new Dimension(400, 400));
                winnerDialog.add(new JLabel("Remis"));
                winnerDialog.setVisible(true);
                this.isFinished = true;
            }
        }
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

class NumberPressedAction extends AbstractAction {
    private int pressedNumber;
    private GameBoardCard gameBoardCard;

    public NumberPressedAction(int pressedNumber, GameBoardCard gameBoardCard) {
        this.pressedNumber = pressedNumber;
        this.gameBoardCard = gameBoardCard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameBoardCard.getFocusedSection() != null) {
            gameBoardCard.getFocusedSection().getFieldsMap().get(pressedNumber-1).setClicked();
        }
        else {
            if(gameBoardCard.getSection(pressedNumber-1).getWinner() == null) {
                gameBoardCard.deactivateAllSections();
                gameBoardCard.getSection(pressedNumber - 1).setFocused();
            }
        }
    }
}
