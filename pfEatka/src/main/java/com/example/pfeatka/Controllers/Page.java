package com.example.pfeatka.Controllers;

import com.example.pfeatka.Element.Membrane;
import com.example.pfeatka.Utils.KeywordManager;
import com.example.pfeatka.Utils.MouseData;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.io.Serializable;


public class Page implements Serializable {
    Canvas canvas = new Canvas();
    public MouseData mouseData = new MouseData();


    public KeywordManager keywordManager = new KeywordManager(this);
    double offset = 0;

    public KeywordManager getKeywordManager() {
        return keywordManager;
    }

    private Membrane membrane ;

    public Membrane getMembrane() {
        return membrane;
    }
    public void setMembrane(Membrane membrane){
        this.membrane = membrane;
    }



    Page(double width , double height )
    {
        this.canvas.setHeight(height);
        this.canvas.setWidth(width);
        this.canvas.getGraphicsContext2D().setFill(Color.WHITE);
        this.canvas.getGraphicsContext2D().fillRect(0 , 0 , width , height);

    }

}
