package com.example.pfeatka.Element.Text;

import com.example.pfeatka.Utils.MouseData;
import com.example.pfeatka.Utils.Pair;
import com.example.pfeatka.Utils.SerializableFont;
import com.example.pfeatka.Utils.TextUtil;
import javafx.scene.text.Font;

import java.io.Serializable;


public class TextObj implements Serializable {
    public SerializableFont font;
    public String data;
    public double width;
    public double height;
    public int imgIndex = 0;
    double xPosition =0 ;
    double yPosition = 0;
    boolean strikethrough = false;
    public boolean selected = false;
    boolean underline = false;
    public double getxPosition() {
        return xPosition;
    }
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }
    public double getyPosition() {
        return yPosition;
    }
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }
    public boolean isColliding(Pair<Double , Double> mouseLocation)
    {

        return (mouseLocation.first >= xPosition && mouseLocation.first <= xPosition + width && mouseLocation.second >= yPosition - height && mouseLocation.second <= yPosition);
    }
    public TextObj(String data)
    {
        this.data = data;
        setFont();
    }
    public TextObj(TextObj copy){
        this.width = copy.width;
        this.height = copy.height;
        this.data = copy.data;
        this.imgIndex = copy.imgIndex;
        this.xPosition = copy.xPosition;
        this.yPosition = copy.yPosition ;
        this.font = copy.font;

    }
    public void setFont(){
        setFont(new SerializableFont(0));
    }

    public void setFont(SerializableFont newFont)
    {
        this.font = newFont;
        this.width = TextUtil.getWidth(data, newFont);
        this.height = TextUtil.getHeight(data, newFont);
    }

    public double getHeight() {

        return this.height;
    }
    public double getWidth() {
        return this.width;
    }
    public String getText() {
        return this.data;
    }
    public SerializableFont getFont() { return font; }
    public boolean isUnderline() {
        return underline;
    }
    public void setUnderline(boolean underline) {
        this.underline = underline;
    }
    public boolean isStrikethrough() {
        return this.strikethrough;
    }
    public void setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
    }
}
