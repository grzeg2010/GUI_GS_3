package GUI;

import GUI.Cards.GameBoardCard;
import GUI.Colors.Breeze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    private JPanel contentPane;
    private CardLayout cardLayout;

    public Window() {
        // JFrame settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(1200, 900));
        this.setTitle("Ultimate Tik Tak Toe");

        // Content Pane settings
        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        this.setBackground(Breeze.BackgroundNormal);
        this.setContentPane(contentPane);

        // Adding elements


        GUI.Cards.WelcomeCard welcomeCard = new GUI.Cards.WelcomeCard(contentPane, cardLayout);
        GUI.Cards.GameBoardCard gameBoardCard = new GUI.Cards.GameBoardCard(this);
        GUI.Cards.MultiplayerCard multiplayerCard = new GUI.Cards.MultiplayerCard(gameBoardCard, contentPane, cardLayout);

        this.add(welcomeCard);
        this.add(multiplayerCard);
        this.add(gameBoardCard);

        this.setVisible(true);
    }
}
