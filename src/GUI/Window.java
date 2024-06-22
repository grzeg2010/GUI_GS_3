package GUI;

import GUI.Colors.Breeze;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel contentPane;
    private CardLayout cardLayout;

    public Window() {
        // JFrame settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // this.setSize(new Dimension(400, 400));
        this.setSize(new Dimension(1200, 900));
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Ultimate Tik Tak Toe");

        // Content Pane settings
        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        this.setBackground(Breeze.BackgroundNormal);
        this.setContentPane(contentPane);

        // Adding elements
        GUI.Cards.WelcomeCard welcomeCard = new GUI.Cards.WelcomeCard(this, contentPane, cardLayout);
        this.add(welcomeCard);

        GUI.Cards.MultiplayerCard multiplayerCard = new GUI.Cards.MultiplayerCard(contentPane, cardLayout);
        this.add(multiplayerCard);

        GUI.Cards.GameBoardCard gameBoardCard = new GUI.Cards.GameBoardCard(this);
        this.add(gameBoardCard);

        this.setVisible(true);
    }
}
