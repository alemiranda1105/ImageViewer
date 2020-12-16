package imageviewer.apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlockPanel extends JPanel implements ImageDisplay {
    private Image image;

    public BlockPanel() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image(), 0, 0, null);
    }

    private BufferedImage image() {
        try {
            return ImageIO.read(new File(image.getName()));
        } catch (IOException e) {
            System.out.println("Error leyendo la imagen");
            return null;
        }
    }

    @Override
    public void display(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    public Image currentImage() {
        return image;
    }
}
