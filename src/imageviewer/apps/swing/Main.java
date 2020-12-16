package imageviewer.apps.swing;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        new Main().execute();
    }

    public Main() {
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
    }

    private JPanel imagePanel() {
        BlockPanel blockPanel = new BlockPanel();
        return blockPanel;
    }

    private void execute() {
        this.setVisible(true);
    }

}
