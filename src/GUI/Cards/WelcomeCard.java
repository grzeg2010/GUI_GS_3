package GUI.Cards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeCard extends JPanel {
    private JButton bSingleplayer, bMultiplayer;

    public WelcomeCard(JPanel mainWindowContentP, CardLayout mainWindowCardL) {
        this.setLayout(new FlowLayout());

        bSingleplayer = new JButton("Singleplayer");
        bSingleplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindowCardL.last(mainWindowContentP);
            }
        });
        this.add(bSingleplayer);

        bMultiplayer = new JButton("Multiplayer");
        bMultiplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindowCardL.next(mainWindowContentP);
            }
        });
        this.add(bMultiplayer);
    }
}
