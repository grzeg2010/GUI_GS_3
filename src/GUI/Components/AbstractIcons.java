package GUI.Components;

import GUI.Colors.Breeze;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractIcons {
    public static BufferedImage generateCircleIcon(int width) {
        BufferedImage circleImg = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Breeze.ForegroundNormal);
        g2.setStroke(new BasicStroke(8));
        g2.drawOval(5, 5, width - 10, width - 10);
        g2.dispose();
        return circleImg;
    }

    public static BufferedImage generateCrossIcon(int width) {
        int val1 = 10;
        int val2 = width - 10;

        BufferedImage crossImg = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = crossImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Breeze.ForegroundNormal);
        g2.setStroke(new BasicStroke(8));
        g2.drawLine(val1, val1, val2, val2);
        g2.drawLine(val2, val1, val1, val2);
        g2.dispose();

        return crossImg;
    }

    public static BufferedImage generateDashIcon(int width) {
        BufferedImage dashImg = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dashImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Breeze.ForegroundNormal);
        g2.setStroke(new BasicStroke(8));
        g2.drawLine(10, width / 2, width - 10, width / 2);
        g2.dispose();

        return dashImg;
    }
}
