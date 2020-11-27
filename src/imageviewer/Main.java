package imageviewer;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MockImageLoader imageLoader = new MockImageLoader();
        List<Image> imageList = imageLoader.load();
        int index = 0;
        
        while(true) {
            ImageDisplay imageDisplay = new MockImageDisplay();
            imageDisplay.display(imageList.get(index));
            
            String input = scanner.next();
            if(input.equalsIgnoreCase("q")) break;
            else if(input.equalsIgnoreCase("n")) {
                index++;
                if(index >= imageList.size()) index = 0;
            } else if(input.equalsIgnoreCase("p")) {
                index--;
                if(index < 0) index = imageList.size() - 1;
            }
        }
    }
    
}
