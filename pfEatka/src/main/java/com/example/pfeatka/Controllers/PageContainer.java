package com.example.pfeatka.Controllers;


import com.example.pfeatka.Element.Membrane;
import com.example.pfeatka.Utils.Pair;
import com.example.pfeatka.Utils.Sheet;
import com.example.pfeatka.Utils.FileManager;
import javafx.scene.layout.Pane;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class PageContainer implements Serializable {
    public LinkedList<Page> pages = new LinkedList<>();
    FileManager<LinkedList<Page>> fileManager = new FileManager();
    public Pane parent;

    Pair<Double, Double> getPageDimension(Sheet sheet){
        Pair<Double, Double> dimension = new Pair<>(0.0, 0.0);
        if (Objects.requireNonNull(sheet) == Sheet.a4) {
            dimension.first = 8.27 * 72;
            dimension.second = 11.69 * 72;
        }

        return dimension;
    }

    PageContainer(Pane parent){ this.parent = parent ; }
    void applyTemplate(Page page){
        //addcode
//        page.getMembrane().getFirst().gc
//        page.setMembrane(new Membrane(new double[]{0 , page.}))
    }


    void addPageOnNumber(Sheet sheet , int pageNumber  )
    {
        Pair<Double, Double> dimension = getPageDimension(sheet);
        var page = new Page(dimension.first, dimension.second  );
        applyTemplate(page);
        page.setMembrane(new Membrane(new double[]{0 , dimension.first , dimension.first , 0} ,
                new  double[]{ 0 , 0 , dimension.second , dimension.second} , page.canvas.getGraphicsContext2D()));
        pages.add(pageNumber, page);


    }



}
