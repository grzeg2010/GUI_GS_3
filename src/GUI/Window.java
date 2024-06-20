package GUI;

import GUI.Colors.Breeze;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Window extends JFrame {
    private JPanel contentPane;
    private CardLayout cardLayout;
    // private Map<String, JPanel> cardsSet;

    public Window() {
        // JFrame settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 900);
        this.setTitle("Ultimate Tik Tak Toe");

        // Content Pane settings
        contentPane = new JPanel();
        cardLayout = new CardLayout();
        // cardsSet = new HashMap<>();
        contentPane.setLayout(cardLayout);
        this.setBackground(Breeze.BackgroundNormal);
        this.setContentPane(contentPane);

        // Adding elements
        GUI.Cards.WelcomeCard welcomeCard = new GUI.Cards.WelcomeCard(contentPane, cardLayout);
        // cardsSet.put("welcome", welcomeCard);
        this.add(welcomeCard);

        GUI.Cards.MultiplayerCard multiplayerCard = new GUI.Cards.MultiplayerCard(contentPane, cardLayout);
        // cardsSet.put("multiplayer", multiplayerCard);
        this.add(multiplayerCard);

        GUI.Cards.GameBoardCard gameBoardCard = new GUI.Cards.GameBoardCard();
        // cardsSet.put("gameBoard", gameBoardCard);
        this.add(gameBoardCard);

        // cardsSet.forEach( (_, card) -> contentPane.add(card) );

        this.setVisible(true);
    }
}
