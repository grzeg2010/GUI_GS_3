package GUI.Components;

import Data.MovesHistoryData;
import GUI.Cards.GameBoardCard;
import GUI.Colors.Breeze;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Field extends JButton {
    private final int index;
    private GameBoardCard gameBoard;
    private Section parentSection;
    private MovesHistoryData.Player selectedBy;

    public Field(int index, GameBoardCard gameBoard, Section parentSection) {
        this.index = index;
        this.gameBoard = gameBoard;
        this.parentSection = parentSection;
        this.selectedBy = null;

        this.setBackground(Breeze.BackgroundNormal);
        this.setOpaque(true);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClicked();
            }
        });
    }

    public void setClicked() {
        this.setEnabled(false);
        gameBoard.saveMove(this);

        switch (gameBoard.getCurrentPlayer()) {
            case o -> setO();
            case x -> setX();
        }

        parentSection.validateScore(this.selectedBy);
        parentSection.getGameBoard().validateScore(this.selectedBy);
        gameBoard.changePlayer();

        if (!parentSection.getIsFocused()) {
            gameBoard.deactivateAllSections();
        }
        else {
            parentSection.setInactive();
        }

        if (gameBoard.getSection(index).getWinner() == null)
            gameBoard.getSection(index).setFocused();
        else
            gameBoard.activateAllSections();
    }

    public void setO() {
        this.selectedBy = MovesHistoryData.Player.o;
        this.setBackground(Breeze.ForegroundLink);

        this.setIcon(new ImageIcon(GUI.Components.AbstractIcons.generateCircleIcon(70)));
    }

    public void setX() {
        this.selectedBy = MovesHistoryData.Player.x;
        this.setBackground(Breeze.ForegroundNegative);


        this.setIcon(new ImageIcon(GUI.Components.AbstractIcons.generateCrossIcon(70)));
    }

    // GETTERS
    public int getIndex() { return index; }
    public Section getParentSection() { return parentSection; }
    public MovesHistoryData.Player getSelectedBy() { return selectedBy; }
}
