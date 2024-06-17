package GUI;

import GUI.Colors.Breeze;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private CardLayout cardLayout = new CardLayout();

    public Window() {
        // JFrame settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 950);
        this.setTitle("Ultimate Tik Tak Toe");

        // Content Pane settings
        JPanel contentPane = new JPanel();
        contentPane.setLayout(cardLayout);
        this.setBackground(Breeze.BackgroundNormal);
        this.setContentPane(contentPane);

        // Adding elements
        GUI.Cards.GameBoardCard gameBoardCard = new GUI.Cards.GameBoardCard();
        contentPane.add(gameBoardCard);

        this.setVisible(true);
    }
}
