package imageviewer.apps.mock;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;


 class MockImageDisplay implements ImageDisplay {

    private Image image;

    @Override
    public void display(Image image) {
        this.image = image;
        System.out.println(image.name());
    }

    @Override
    public Image currentImage() {
        return this.image;
    }

     @Override
     public void on(Shift shift) {

     }

 }
