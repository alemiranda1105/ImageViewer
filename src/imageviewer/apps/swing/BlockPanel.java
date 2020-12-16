package imageviewer.apps.swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlockPanel extends JPanel {
    public BlockPanel() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image(), 0, 0, null);
    }

    private BufferedImage image() {
        try {
            return ImageIO.read(new File("fotos/coche-1.jpg"));
        } catch (IOException e) {
            System.out.println("Error leyendo la imagen");
            return null;
        }
    }
}
