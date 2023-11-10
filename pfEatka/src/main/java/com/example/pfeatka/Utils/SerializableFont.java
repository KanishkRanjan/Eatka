package com.example.pfeatka.Utils;

import javafx.scene.text.Font;

import java.io.Serializable;

public class SerializableFont implements Serializable {
    private String fontName;
    private double fontSize;
    private String fontStyle;

    public SerializableFont(Font font) {
        this.fontName = font.getName();
        this.fontSize = font.getSize();
        this.fontStyle = font.getStyle();
    }
    public String getStyle(){
        return fontStyle;
    }
    public SerializableFont(int fontSize){
        this.fontSize = 0;
        this.fontName = "Times New Roman";
    }

    public Font toFont() {
        return Font.font(fontName, fontSize);
//        return new Font(16);
    }

    public double getSize() {
        return fontSize;
    }
}
