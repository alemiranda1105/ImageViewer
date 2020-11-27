package imageviewer;

import imageviewer.control.Command;
import imageviewer.control.ExitCommand;
import imageviewer.control.LoadCommand;
import imageviewer.control.NextImageCommand;
import imageviewer.control.NullCommand;
import imageviewer.control.PrevImageCommand;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Command> commands = initCommands(createImageDisplay());
        commands.get("l").execute();
        while(true) {
            commands.getOrDefault(scanner.next(), new NullCommand()).execute();
        }
    }

    private static Map<String, Command> initCommands(ImageDisplay imageDisplay) {
        List<Image> imageList = new ArrayList<>();
        ImageLoader imageLoader = new MockImageLoader();
        Map<String, Command> commands = new HashMap<>();
        commands.put("l", new LoadCommand(imageLoader, imageList, imageDisplay));
        commands.put("q", new ExitCommand());
        commands.put("p", new PrevImageCommand(imageList, imageDisplay));
        commands.put("n", new NextImageCommand(imageList, imageDisplay));
        return commands;
    }

    private static ImageDisplay createImageDisplay() {
        ImageDisplay imageDisplay = new MockImageDisplay();
        return imageDisplay;
    }
}
