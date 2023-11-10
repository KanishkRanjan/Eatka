package com.example.pfeatka.Image;

import com.example.pfeatka.Utils.MouseData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class ImageRender {
        public Image image ;
        public String location;
        GraphicsContext gc ;

        public double imageWidth ;
        public double imageHeight ;

        double xPos = 100 ;
        double yPos = 100;
        public ImageRender(String location , GraphicsContext gc){
                this.gc = gc;
                System.out.println(gc);
                this.location = location;
                image = new Image("file:"+this.location);
                imageWidth = image.getWidth();
                imageHeight = image.getHeight();
        }

        public void manageCollision(MouseData mouseData){
                int offset = 10;
                if(Math.abs(mouseData.first.first - (xPos+imageWidth)) < offset){
                        imageWidth += (mouseData.current.first - mouseData.first.first);
//                        System.out.println(imageWidth);
                }
                else if(Math.abs(mouseData.first.second - (yPos+imageHeight)) < offset){
                        imageHeight += (mouseData.current.second - mouseData.first.second);
                }
                else {
                        return;
                }

                mouseData.first.second = mouseData.current.second;
                mouseData.first.first = mouseData.current.first;
                rerender();
//                gc.strokeRect(xPos ,yPos , imageWidth , imageHeight);
//                        rowHeight.set(index , rowHeight.get(index) + (mouseData.current.second - mouseData.first.second) );
//                mouseData.first.second = mouseData.current.second;
//                render();
        }

        public void rerender(){
                gc.setFill(Color.WHITE);
                gc.fillRect(0 ,0 , 5000 , 5000);
                gc.setFill(Color.BLACK);
                render();
        }

        public void render(){
                gc.drawImage(image , xPos, yPos, imageWidth , imageHeight);
        }




}
