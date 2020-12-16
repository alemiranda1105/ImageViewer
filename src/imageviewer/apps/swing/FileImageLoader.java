package imageviewer.apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileImageLoader implements ImageLoader {
    private final File root;

    public FileImageLoader(File root) {
        this.root = root;
    }

    @Override
    public List<Image> load() {
        List<Image> list = new ArrayList<>();
        for(File file: root.listFiles(imageFilter())) {
            list.add(new Image(file.getAbsolutePath()));
        }
        return list;
    }

    private static final String[] imageExtensions = new String[] {".jpg", ".png", ".jpeg"};
    private FilenameFilter imageFilter() {
        return (dir, name) -> {
            for(String extension: imageExtensions) if(name.endsWith(extension)) return true;
            return false;
        };
    }
}
