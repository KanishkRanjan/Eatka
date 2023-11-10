package com.example.pfeatka.Table;

import com.example.pfeatka.Element.Membrane;
import com.example.pfeatka.Element.Text.SpecialCharacter;
import com.example.pfeatka.Models.Model;
import com.example.pfeatka.Utils.MouseData;
import com.example.pfeatka.Utils.Pair;
import com.example.pfeatka.Utils.SerializableFont;
import com.example.pfeatka.Utils.TextUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.util.ArrayList;


public class tableRender {
    public  double startXPosition = 100;
    public double startYPosition =100;
    public Membrane membrane;
    ArrayList<Double> columnWidth = new ArrayList<>();
    ArrayList<Double> rowHeight = new ArrayList<>();
    int rows ;
    int columns;
    GraphicsContext gc;

    public Color color = Color.BLACK;
    SerializableFont textFont = new SerializableFont(new Font("Times New Roman",16));

    public void setFont(SerializableFont newFont){
        this.textFont = newFont;
    }


    ArrayList<ArrayList<Rectangle>> tableContainer  = new ArrayList<>();
    public tableRender(GraphicsContext gc){
        this.gc = gc;
    }
    public void isCollision(MouseData mouseData){
        int offset = 5;
        for (int i = 0 ; i < rows;i++){
            if(Math.abs( tableContainer.get(i).get(0).yPos + rowHeight.get(i)- mouseData.first.second) < offset ){
                verticalAction(i , mouseData );
                break;
            }
        }

        for (int i = 0; i < columns ;i++){
            if(Math.abs( tableContainer.get(0).get(i).xPos + columnWidth.get(i)- mouseData.first.first) < offset ){
                horizontalAction(i , mouseData);
                break;
            }
        }

    }

    public void verticalAction(int index , MouseData mouseData){
        rowHeight.set(index , rowHeight.get(index) + (mouseData.current.second - mouseData.first.second) );
        mouseData.first.second = mouseData.current.second;
        render();
    }

    public void horizontalAction(int index , MouseData mouseData){
        columnWidth.set(index , columnWidth.get(index) + (mouseData.current.first - mouseData.first.first) );
        mouseData.first.first = mouseData.current.first;
        render();
    }


    public void setRowAndColumn(int rows , int columns){
        this.rows = rows;
        this.columns  = columns;
        double xPos = startXPosition;
        double yPos = startYPosition;
        for(int i = 0 ; i < columns ;i++) columnWidth.add(100d);
        for(int i = 0 ; i < rows ;i++){ rowHeight.add(50d);}

        for(int i = 0 ;i  < rows ; i++){
            ArrayList<Rectangle> columnsData = new ArrayList<>();
            for(int j =0  ; j < columns ; j++){
                columnsData.add(new Rectangle(xPos , yPos ));
                xPos += columnWidth.get(j);

            }
            tableContainer.add(columnsData);
            yPos +=  rowHeight.get(i);
            xPos = startXPosition;
        }
    }

    public void render(){
        gc.setFill(Color.WHITE);
        gc.fillRect(0 , 0 , 10000, 10000);
        gc.setFill(color);
        System.out.println(color);
        double xPos ;
        double yPos =startYPosition;
        for (int i  = 0 ;i  < rows ; i++ ){
            xPos = startXPosition;
            for (int j =0 ; j < columns ; j++){
                tableContainer.get(i).get(j).yPos = yPos;
                tableContainer.get(i).get(j).xPos = xPos;
                var tmp = renderDemoText( tableContainer.get(i).get(j) , xPos , yPos);
                columnWidth.set(j, Math.max(columnWidth.get(j) , tmp.first));
                rowHeight.set(i, Math.max(rowHeight.get(i) , tmp.second));
                gc.strokeRect(xPos, yPos , columnWidth.get(j) , rowHeight.get(i));
                xPos += columnWidth.get(j);
            }
            yPos +=  rowHeight.get(i);
        }
    }

    private Pair<Double ,Double> renderDemoText(Rectangle rectangle , double xPos , double yPos) {
        String data = rectangle.data;
        double textXpos = 0;
        double textYpos = TextUtil.getHeight(data.charAt(0)+"" , textFont);

        for(int i = 0 ; i < data.length() ;i++){

            gc.setFont(textFont.toFont());
            gc.fillText(data.charAt(i)+"" , xPos+textXpos, yPos+textYpos );
            textXpos+=TextUtil.getWidth(data.charAt(i)+"" , textFont);
        }
        return new Pair<>(textXpos , textYpos);
    }

    public void addTableToMembrane(){
        if(membrane == null) return;
        membrane.addCharacter(SpecialCharacter.lineStart.getCommand());
        membrane.addCharacter(SpecialCharacter.table.getCommand());
        membrane.addCharacter(String.valueOf(color));
        membrane.addCharacter(SpecialCharacter.tableRow.getCommand());
        membrane.addCharacter(String.valueOf(rowHeight.get(0)));
        for(int j = 0 ; j <columns ; j++){
            membrane.addCharacter(SpecialCharacter.tableHeadColumn.getCommand());
            membrane.addCharacter(String.valueOf(columnWidth.get(j)))  ;
            membrane.addCharacters(" Demo text" , textFont);
            membrane.addCharacter(SpecialCharacter.tableHeadColumnEnd.getCommand());
        }
        membrane.addCharacter(SpecialCharacter.tableRowEnd.getCommand());

        for(int i =1 ; i < rows ; i++){
            membrane.addCharacter(SpecialCharacter.tableRow.getCommand());
            membrane.addCharacter(String.valueOf(rowHeight.get(i)));

            for(int j = 0 ; j <columns ; j++){
                membrane.addCharacter(SpecialCharacter.tableColumn.getCommand());
                membrane.addCharacters(" Demo text" , textFont);
                membrane.addCharacter(SpecialCharacter.tableColumnEnd.getCommand());
            }

            membrane.addCharacter(SpecialCharacter.tableRowEnd.getCommand());
        }

        membrane.addCharacter(SpecialCharacter.tableEnd.getCommand());
        membrane.addCharacter(SpecialCharacter.lineStart.getCommand());
        membrane.rerender();
        Model.getInstance().getViewFactory().closeTableEditor();
    }

//    ?ArrayList<Pair<Pair<Integer , Integer> , double[]>> rectBound = new ArrayList<>();


//    private Pair<Double ,Double> renderTable(int staringIndex, int endingIndex , double xPos , double yPos) {
//
//        System.out.println("------------");
//        System.out.println(staringIndex +" " + endingIndex);
//        for(int i = staringIndex+1 ; i < endingIndex ; i++){
//            System.out.println("element: "+ data.get(i).getText());
//        }
//        ArrayList<Pair<Integer, Double>> columnWidth = new ArrayList<>();
//        Pair<Double, Double> currentPos = new Pair<>(xPos, yPos);
//        Pair<Double, Double> textPos = new Pair<>(0.0, 0.0);
//        double currentHeight = 0;
//        int columnNumber = 0;
//        int rowIndex = 0;
//        rectBound = new ArrayList<>();
//        for (int i = staringIndex + 1; i < endingIndex ; i++) {
//
//            if (data.get(i).getText() == SpecialCharacter.tableRow.getCommand()) {
//                rowIndex = i;
//                currentHeight = Double.parseDouble(data.get(++i).getText());
//
//            } else if (data.get(i).getText() == SpecialCharacter.tableRowEnd.getCommand()) {
//                System.out.println(currentPos.first);
//                currentPos.second += currentHeight;
//                currentPos.first = xPos;
//                currentHeight = 0;
//                columnNumber = 0;
//
//            } else if (data.get(i).getText() == SpecialCharacter.tableHeadColumn.getCommand()) {
//                columnWidth.add(new Pair<>(i, Double.valueOf(data.get(++i).getText())));
//                textPos.first = xPos;
//                textPos.second = 0d;
//            }
//            else if
//            (
//                    data.get(i).getText() == SpecialCharacter.tableHeadColumnEnd.getCommand() ||
//                            data.get(i).getText() == SpecialCharacter.tableColumnEnd.getCommand()
//            )
//            {
//                double xPoints[] = {currentPos.first , currentPos.first + columnWidth.get(columnNumber).second,  currentPos.first + columnWidth.get(columnNumber).second ,  currentPos.first} ;
//                double yPoints[] = {currentPos.second , currentPos.second , currentPos.second + currentHeight, currentPos.second + currentHeight};
//                rectBound.add(new Pair(new Pair<>(rowIndex , columnWidth.get(columnNumber).first),new double[]{xPoints[0] , xPoints[1] , yPoints[0] , yPoints[2]}));
//                gc.strokePolygon(xPoints , yPoints , 4);
//
//                currentPos.first += columnWidth.get(columnNumber).second;
//                columnNumber++;
//            } else if (data.get(i).getText() == SpecialCharacter.tableColumn.getCommand()) {
//                textPos.first = xPos;
//                textPos.second = 0d;
//            }
//            else if(data.get(i).getText() == SpecialCharacter.lineStart.getCommand()){
//                textPos.second += data.get(i-1).getHeight();
//                textPos.first = xPos;
//            }
//            else {
//                gc.setFont(data.get(i).getFont());
//                gc.fillText(data.get(i).getText() , currentPos.first + textPos.first , currentPos.second +data.get(i).height+textPos.second);
//                data.get(i).setxPosition(currentPos.first + textPos.first);
//                data.get(i).setyPosition(currentPos.second +data.get(i).height + textPos.second);
//                textPos.first += data.get(i).getWidth();
//            }
//        }
//        return currentPos;
//    }
}


