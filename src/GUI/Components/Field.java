package GUI.Components;

import GUI.Colors.Breeze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Field extends JButton {
    public Field() {
        this.setBackground(Breeze.BackgroundNormal);
        this.setOpaque(true);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Kliknieto " + this);
            }
        });
    }

    public void setO() {
        this.setEnabled(false);
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
        this.setEnabled(false);
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
}
