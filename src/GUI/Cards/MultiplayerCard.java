package GUI.Cards;

import Data.MovesHistoryData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerCard extends JPanel {
    private JButton bStartingPlayer, bStartingComputer;

    public MultiplayerCard(GUI.Cards.GameBoardCard gameBoardCard, JPanel mainWindowContentP, CardLayout mainWindowCardL) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel lDifficulty = new JLabel("Poziom trudności: ");
        this.add(lDifficulty);

        JPanel pDifficulty = new JPanel();
        JRadioButton rEasy = new JRadioButton("Łatwy", true);
        pDifficulty.add(rEasy);
        JRadioButton rMedium = new JRadioButton("Średni");
        rMedium.setEnabled(false);
        pDifficulty.add(rMedium);
        JRadioButton rHard = new JRadioButton("Trudny");
        rHard.setEnabled(false);
        pDifficulty.add(rHard);
        this.add(pDifficulty);

        JLabel lWhoStarts = new JLabel("Kto zaczyna?");
        this.add(lWhoStarts);

        JPanel pWhoStartsButtons = new JPanel();
        bStartingPlayer = new JButton("Gracz");
        bStartingPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBoardCard.activateComputerPlayer(MovesHistoryData.Player.x);
                mainWindowCardL.last(mainWindowContentP);
            }
        });
        pWhoStartsButtons.add(bStartingPlayer);

        bStartingComputer = new JButton("Komputer");
        bStartingComputer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBoardCard.activateComputerPlayer(MovesHistoryData.Player.o);
                mainWindowCardL.last(mainWindowContentP);
            }
        });
        pWhoStartsButtons.add(bStartingComputer);
        this.add(pWhoStartsButtons);
    }
}
