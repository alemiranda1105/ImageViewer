package imageviewer.apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlockPanel extends JPanel implements ImageDisplay {
    private Image image;
    private Image image2;
    private BufferedImage bufferedImage;
    private Shift shift;
    private int x;

    private boolean sliding = false;

    public BlockPanel() {
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    @Override
    public void paint(Graphics g) {
        if(sliding) {
            if (this.image == null) {
                return;
            }
            BufferedImage image = load(this.image.name());
            assert image != null;
            Box box = new Box(image);
            g.drawImage(image, box.x, box.y, box.width, box.height, null);

            if (x == 0) return;

            BufferedImage image2 = load(this.image2.name());
            int offset;
            assert image2 != null;
            if (x > 0) {
                offset = -(image2.getWidth() - x);
            } else {
                offset = x + image.getWidth();
            }
            box = new Box(image2);
            g.drawImage(image2, box.x+offset, box.y, box.width, box.height, null);

        } else {
            Box box = new Box(bufferedImage);
            g.drawImage(bufferedImage, box.x, box.y, box.width, box.height, null);
        }
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            System.out.println("Error leyendo la imagen");
            return null;
        }
    }

    @Override
    public void display(Image image) {
        this.image = image;
        this.bufferedImage = load(image.name());
        repaint();
    }

    @Override
    public Image currentImage() {
        return image;
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    private class Box {
        final int x;
        final int y;
        final int width;
        final int height;
        private final int px;
        private final int py;

        public Box(BufferedImage image) {
            this.px = getWidth();
            this.py = getHeight();
            this.width = calculateWidth(image.getWidth(), image.getHeight());
            this.height = calculateHeight(image.getWidth(), image.getHeight());
            this.x = (px - width) / 2;
            this.y = (py - height) / 2;
        }

        private int calculateWidth(double ix, double iy) {
            double pr = (double) px / py;
            double ir = ix / iy;
            return ir > pr ? px: (int) (ix * py / iy);
        }

        private int calculateHeight(double ix, double iy) {
            double pr = (double) px / py;
            double ir = ix / iy;
            return ir > pr ? (int) (iy * px / ix): py;
        }

    }

    private class MouseHandler implements MouseListener, MouseMotionListener {
        private int initial;

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.initial = e.getX();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (Math.abs(e.getX() - initial) > getWidth() / 2) image = image2;
            x = 0;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            sliding = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            sliding = true;
            x = e.getX() - initial;
            repaint();
            if (x > 0) image2 = shift.right();
            if (x < 0) image2 = shift.left();
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

    }
}
