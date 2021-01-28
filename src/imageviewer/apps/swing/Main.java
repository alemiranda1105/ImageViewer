package imageviewer.apps.swing;

import imageviewer.control.Command;
import imageviewer.presenter.ImagePresenter;
import imageviewer.control.NextImageCommand;
import imageviewer.control.PrevImageCommand;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Main extends JFrame {

    private ImageDisplay imageDisplay;
    private final HashMap<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        new Main().execute();
    }

    public Main() {
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
        this.add(toolBar(), BorderLayout.SOUTH);
    }

    private void execute() {
        List<Image> images = new FileImageLoader(new File("fotos")).load();
        this.imageDisplay.display(images.get(0));
        new ImagePresenter(images, imageDisplay);
        this.commands.put("<", new PrevImageCommand(images, imageDisplay));
        this.commands.put(">", new NextImageCommand(images, imageDisplay));
        this.setVisible(true);
    }

    private JPanel imagePanel() {
        BlockPanel blockPanel = new BlockPanel();
        this.imageDisplay = blockPanel;
        return blockPanel;
    }

    private JMenuBar toolBar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.add(button("<"));
        toolbar.add(button(">"));
        return toolbar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(e -> commands.get(name).execute());
        return button;
    }

}
