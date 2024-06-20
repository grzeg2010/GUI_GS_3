package GUI.Cards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerCard extends JPanel {
    private JButton bStartingPlayer, bStartingComputer;

    public MultiplayerCard(JPanel mainWindowContentP, CardLayout mainWindowCardL) {
        bStartingPlayer = new JButton("Gracz");
        bStartingPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindowCardL.last(mainWindowContentP);
            }
        });
        this.add(bStartingPlayer);

        bStartingComputer = new JButton("Komputer");
        bStartingComputer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindowCardL.last(mainWindowContentP);
            }
        });
        this.add(bStartingComputer);
    }
}
