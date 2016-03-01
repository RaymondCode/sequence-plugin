package org.intellij.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ryker.zhang on 2016/3/1.
 */
public class ImageJPanel extends JPanel {
    private static final long serialVersionUID = 5393078889894496172L;
    private BufferedImage image;

    public ImageJPanel() {
        try {
            image = ImageIO.read(new File("D:\\test\\test.png"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
    }
}
