package GUI.Components;

import Data.MovesHistoryData;
import GUI.Cards.GameBoardCard;
import GUI.Colors.Breeze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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

        BufferedImage circleImg = new BufferedImage(70, 70, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Breeze.ForegroundNormal);
        g2.setStroke(new BasicStroke(8));
        g2.drawOval(5, 5, 60, 60);
        g2.dispose();
        this.setIcon(new ImageIcon(circleImg));
    }

    public void setX() {
        this.selectedBy = MovesHistoryData.Player.x;
        this.setBackground(Breeze.ForegroundNegative);
        int val1 = 10;
        int val2 = 60;

        BufferedImage crossImg = new BufferedImage(70, 70, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = crossImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Breeze.ForegroundNormal);
        g2.setStroke(new BasicStroke(8));
        g2.drawLine(val1, val1, val2, val2);
        g2.drawLine(val2, val1, val1, val2);
        g2.dispose();
        this.setIcon(new ImageIcon(crossImg));
    }

    // GETTERS
    public int getIndex() { return index; }
    public Section getParentSection() { return parentSection; }
    public MovesHistoryData.Player getSelectedBy() { return selectedBy; }
}
