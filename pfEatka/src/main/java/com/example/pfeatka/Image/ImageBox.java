package com.example.pfeatka.Image;

import com.example.pfeatka.Utils.Pair;
import javafx.scene.image.Image;
import java.io.Serializable;


public class ImageBox implements Serializable {
    public double xPosition = 0 ;
    public double yPosition = 0;
    public double height = 0;
    public double width  = 0;

    public String location ;

    transient public Image image ;

    public void  loadImage(){
        try {
            image = new Image(location);
            System.out.println(location);
            System.out.println("imgebox: " + image.getWidth() +"x"+ image.getHeight());
        } catch (Exception e){
            image = new Image(String.valueOf(getClass().getResource("/Image/imageNotFound.png")));
        }
        System.out.println("Image loaded pussy ");
    }

    public boolean isColliding(Pair<Double , Double> mouseLocation)
    {
        return (mouseLocation.first >= xPosition && mouseLocation.first <= xPosition + width && mouseLocation.second >= yPosition  && mouseLocation.second <= yPosition + height);
    }
    public ImageBox(double xPosition , double yPosition , double width , double height , String location){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.location = location;
        loadImage();
    }

}
