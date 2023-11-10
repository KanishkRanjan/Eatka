package com.example.pfeatka.Element;


import com.example.pfeatka.Element.Text.SpecialCharacter;
import com.example.pfeatka.Element.Text.TextObj;

import com.example.pfeatka.Utils.*;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;

import java.util.*;


public class Membrane extends DocumentElementBase {

    public double[] xPoints;
    public double[] yPoints;
    public Blinker blinker = new Blinker(this);
    public GraphicsContext gc;

    public MembraneData membraneData = new MembraneData();

    FileManager<MembraneData> fileManager  = new FileManager<>();
    public void saveData(String loc){
        for(int i = 0 ; i < membraneData.data.size() ;i++){
            System.out.println("that"+membraneData.data.get(i).getText());
        }
        fileManager.writeFile(loc , membraneData);

        System.out.println("save");
    }

    public void loadData(String loc){

        membraneData = fileManager.readFile(loc);
        for(int i = 0 ; i < membraneData.data.size() ;i++){
            System.out.println("this"+membraneData.data.get(i).getText());
        }
        blinker.selectedCharacterIndex = 1;
        rerender();
        System.out.println("load");
    }

    public TextObj createText(String c , SerializableFont font , boolean isUnderline , boolean isStrikeTrough)
    {
        TextObj tmp =  new TextObj(c);
        tmp.setFont(font);
        System.out.println("lussy: "+font.toFont().getSize());
        tmp.setUnderline(isUnderline);
        tmp.setStrikethrough(isStrikeTrough);
        if(c.charAt(0) == '&') {
            tmp.width = 0;
            tmp.height = 0;
        }
        return tmp;
    }
    public TextObj createText(String c , SerializableFont font )
    {
        return createText(c  , font , false , false);
    }

    public TextObj createText(String c ){
        return createText(c, new SerializableFont(0));
    }
    public void addCharacter(String c , SerializableFont font , boolean isUnderline , boolean isStrikeTrough)
    {
        System.out.println("mussy: "+font.toFont().getSize());
        TextObj temp = createText(c , font , isUnderline , isStrikeTrough);
        this.blinker.selectedCharacterIndex++;
        membraneData.data.add(blinker.getSelectedCharacterIndex() ,temp);

    }
    public void addCharacter(String c , SerializableFont font )
    {
        addCharacter(c , font , false , false);
    }
    public void addCharacter(String c )
    {
        addCharacter(c , new SerializableFont(0) , false , false);
    }
    public void addCharacters(String c){
        addCharacters(c , new SerializableFont(0));
    }
    public void addCharacters(String c ,SerializableFont font){
        for(int i = 0 ;i  < c.length() ; i++){
            addCharacter(c.charAt(i)+"" , font);
        }
    }
    public void createNewLine(){
        membraneData.data.add(++blinker.selectedCharacterIndex,createText(SpecialCharacter.lineStart.getCommand()));
    }

    public Membrane(double[] xPoints, double[] yPoints, GraphicsContext gc)
    {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.gc = gc;
        membraneData.data.add(blinker.selectedCharacterIndex++,createText(SpecialCharacter.lineStart.getCommand()));
        blinker.selectedCharacterIndex = 0;
        gc.strokePolygon(xPoints, yPoints ,4);
//        addImageBound("https://media.istockphoto.com/id/1322277517/photo/wild-grass-in-the-mountains-at-sunset.jpg?s=1024x1024&w=is&k=20&c=GfqUhZB1SIP5EUp7oLWySOaLD1PxoBsuqs4oonCxZWU=");
//        addInlineImageBound("https://media.istockphoto.com/id/1322277517/photo/wild-grass-in-the-mountains-at-sunset.jpg?s=1024x1024&w=is&k=20&c=GfqUhZB1SIP5EUp7oLWySOaLD1PxoBsuqs4oonCxZWU=");
    }

    @Override
    public boolean isColliding(MouseData mouseData) {
        return false;
    }

    @Override
    public void collisionAction(MouseData mouseData) {

    }


    @Override
    public void onSelect() {

    }

//gc.strokePolygon(
//new double[]{currentPos.first , currentPos.first + columnWidth.get(columnNumber).second,  currentPos.first + columnWidth.get(columnNumber).second ,  currentPos.first} ,
//new double[]{currentPos.second , currentPos.second , currentPos.second + currentHeight, currentPos.second + currentHeight} , 4);



    ArrayList<Pair<Pair<Integer , Integer>, double[]>> rectBound = new ArrayList<>();

    public int getCollisionIndex(Pair<Double ,Double> mouseLocation){
        for(int i = 0 ;i < membraneData.data.size() ; i++)
        {
            if(membraneData.data.get(i).isColliding(mouseLocation)) {
                return i;
            }
        }

//        for(int i = 0 ; i < membraneData.imageStorage.size() ;  i++){
////            if(membraneData.imageStorage.get(i).isColliding(mouseLocation)) {
////                System.out.println("image haa");
////                return i;
////            }
//            System.out.println("---------------");
//            System.out.println(membraneData.imageStorage.get(i).isColliding(mouseLocation));
//            System.out.println("---------------");
//
//        }

        return  -1;
    }


    public void rerender(){
        gc.setFill(Color.WHITE);
        gc.fillRect(0 , 0 , 1000 ,1000);
        gc.setFill(Color.BLACK);
        render();
    }

    public boolean removeSelected(){
        boolean wasSelected = false;
        for (int i = membraneData.data.size()- 1; i >= 0; i--) {
            if ( membraneData.data.get(i).selected) {
                blinker.selectedCharacterIndex = i-1;
                membraneData.data.remove(i);
                wasSelected = true;
            }
        }
        return wasSelected;
    }

    ArrayList<Integer> startingLine = new ArrayList<>();

//    private void addImageBound(String location){
//
//        loadImage(location);
//
//        addCharacter(SpecialCharacter.lineStart.getCommand());
//        addCharacter(SpecialCharacter.imageBlock.getCommand());
//        addCharacter(SpecialCharacter.lineStart.getCommand());
//
//        membraneData.data.getLast().height = membraneData.imageStorage.getLast().getHeight();
//        addCharacter("\n");
//        createNewLine();
//    }

//    private void addInlineImageBound(String location){
//
//        loadImage(location);
//        addCharacter(SpecialCharacter.imageBlock.getCommand());
//        membraneData.data.getLast().height = membraneData.imageStorage.getLast().getHeight();
//    }


    private Pair<Double ,Double> renderTable(int staringIndex, int endingIndex , double xPos , double yPos , String borderColor) {
        ArrayList<Pair<Integer, Double>> columnWidth = new ArrayList<>();
        Pair<Double, Double> currentPos = new Pair<>(xPos, yPos);
        Pair<Double, Double> textPos = new Pair<>(0.0, 0.0);
        double currentHeight = 0;
        int columnNumber = 0;
        int rowIndex = 0;
        rectBound = new ArrayList<>();
        for (int i = staringIndex + 1; i < endingIndex ; i++) {

            if (Objects.equals(membraneData.data.get(i).getText(), SpecialCharacter.tableRow.getCommand())) {
                rowIndex = i;
                currentHeight = Double.parseDouble(membraneData.data.get(++i).getText());

            } else if (Objects.equals(membraneData.data.get(i).getText(), SpecialCharacter.tableRowEnd.getCommand())) {
                currentPos.second += currentHeight;
                currentPos.first = xPos;
                currentHeight = 0;
                columnNumber = 0;

            } else if (Objects.equals(membraneData.data.get(i).getText(), SpecialCharacter.tableHeadColumn.getCommand())) {
                columnWidth.add(new Pair<>(i, Double.valueOf(membraneData.data.get(++i).getText())));
                textPos.first = xPos;
                textPos.second = 0d;
            }
            else if
                (
                    Objects.equals(membraneData.data.get(i).getText(), SpecialCharacter.tableHeadColumnEnd.getCommand()) ||
                            Objects.equals(membraneData.data.get(i).getText(), SpecialCharacter.tableColumnEnd.getCommand())
                )
            {
                double[] xPoints = {currentPos.first , currentPos.first + columnWidth.get(columnNumber).second,  currentPos.first + columnWidth.get(columnNumber).second ,  currentPos.first} ;
                double[] yPoints = {currentPos.second , currentPos.second , currentPos.second + currentHeight, currentPos.second + currentHeight};
                rectBound.add(new Pair<>(new Pair<>(rowIndex , columnWidth.get(columnNumber).first),new double[]{xPoints[0] , xPoints[1] , yPoints[0] , yPoints[2]}));
                gc.setStroke(Color.web(borderColor));
                gc.strokePolygon(xPoints , yPoints , 4);
                gc.setStroke(Color.BLACK);
                currentPos.first += columnWidth.get(columnNumber).second;
                columnNumber++;
            } else if (Objects.equals(membraneData.data.get(i).getText(), SpecialCharacter.tableColumn.getCommand())) {
                textPos.first = xPos;
                textPos.second = 0d;
            }
            else if(Objects.equals(membraneData.data.get(i).getText(), SpecialCharacter.lineStart.getCommand())){
                    textPos.second += membraneData.data.get(i-1).getHeight();
                    textPos.first = xPos;
                    membraneData.data.set(rowIndex+1  , createText((Math.max(Double.parseDouble(membraneData.data.get(rowIndex+1).getText()),textPos.second+membraneData.data.get(i-1).getHeight()))+""));
//                System.out.println();
            }
            else {


                if(columnWidth.get(columnNumber).second <= textPos.first){

//                    System.out.println("blinker pos1: "+blinker.selectedCharacterIndex);
                    addCharacter(SpecialCharacter.lineStart.getCommand());
                    renderTable(staringIndex , endingIndex , xPos , yPos , borderColor);
                    break;
//                    textPos.second += membraneData.data.get(i-1).getHeight();
//                    System.out.println("blinker pos2: "+blinker.selectedCharacterIndex);
//
//                        textPos.first = xPos;
//    //                    membraneData.data.set(rowIndex+1  , createText((textPos.second+membraneData.data.get(i-1).getHeight())+""));
                }
                gc.setFont(membraneData.data.get(i).getFont().toFont());
                gc.fillText(membraneData.data.get(i).getText() , currentPos.first + textPos.first , currentPos.second +membraneData.data.get(i).height+textPos.second);
                membraneData.data.get(i).setxPosition(currentPos.first + textPos.first);
                membraneData.data.get(i).setyPosition(currentPos.second +membraneData.data.get(i).height + textPos.second);
                textPos.first += membraneData.data.get(i).getWidth();
            }
        }
        return currentPos;
    }
    @Override
    public void render(){
        render(xPoints , yPoints , 0 , membraneData.data.size());
    }

    public ArrayList<Integer> render(double[] xPoints, double[] yPoints, int startingPos , int endingPos) {
        double xPosition =xPoints[0];
        double yPosition =yPoints[0];
        double lineHeight = 0;
        double lineSpacing = 0;
        double characterSpacing  = 0 ;
        startingLine = new ArrayList<>();
        int imageIndex = 0;
        for (int i =startingPos ;i < endingPos ; i++)
        {
            TextObj temp = membraneData.data.get(i);
//            System.out.println("bussy: "+membraneData.data.get(i).font.toFont().getSize());
//            System.out.println("uTssy: "+temp.font.toFont().getSize());

            if(temp.getText().charAt(0) == '&'){
                if(Objects.equals(temp.getText(), SpecialCharacter.lineStart.getCommand())){
                    startingLine.add(i);
                    lineHeight = temp.getHeight();
                    yPosition += lineHeight + lineSpacing;
                    xPosition = xPoints[0];
                    temp.setyPosition(yPosition);
                }
                else if(Objects.equals(temp.getText(), SpecialCharacter.imageBlock.getCommand())){
                    gc.drawImage(membraneData.imageStorage.get(imageIndex).image , xPosition , yPosition ,membraneData.imageStorage.get(imageIndex).width ,membraneData.imageStorage.get(imageIndex).height );
                    temp.imgIndex = imageIndex;
                    membraneData.imageStorage.get(imageIndex).xPosition = xPosition;
                    membraneData.imageStorage.get(imageIndex).yPosition = yPosition;
                    temp.setyPosition(yPosition);
                    temp.width = membraneData.imageStorage.get(imageIndex).width ;
                    temp.height = membraneData.imageStorage.get(imageIndex).height;
                    if(temp.selected){
                        gc.setFill(Color.rgb(51, 153, 255, .5));
                        gc.fillRect(xPosition, yPosition , temp.getWidth() ,  temp.getHeight());
                        gc.setFill(Color.BLACK);
//                        gc.fillRect(0 , 0 , 500 , 500);
                    }
                    yPosition += membraneData.imageStorage.get(imageIndex).height;
                    lineHeight = Math.max(lineHeight ,  membraneData.imageStorage.get(imageIndex).height);
                    imageIndex++;
                }
                else if(Objects.equals(temp.getText(), SpecialCharacter.table.getCommand())){

                    String borderColor = membraneData.data.get(i+1).getText();
                    int j = i;
                    for(;j < membraneData.data.size() ;j++){
                        if(Objects.equals(membraneData.data.get(j).getText(), SpecialCharacter.tableEnd.getCommand())) break;
                    }
                    Pair<Double , Double> d = renderTable(i+1 , j , xPosition ,yPosition ,borderColor );
                    i = j;
                    yPosition = d.second;
                    xPosition = xPoints[0];

                }
                temp.setxPosition(xPosition);
                xPosition+= temp.width + temp.width*characterSpacing;

            }
            else
            {
                int staringLinePos = startingLine.get(TextUtil.lower_bound(startingLine.toArray() , blinker.selectedCharacterIndex)-1);
//                System.out.println("lussy3: "+ temp.font.toFont().getSize());
                if( (membraneData.data.get(staringLinePos).height <  membraneData.data.get(i).getHeight())){
                    membraneData.data.get(staringLinePos).height =  membraneData.data.get(i).getHeight();
                    rerender();
                    break;
                }

                if(xPosition > xPoints[1] - membraneData.data.get(i).getWidth()){
                    xPosition = xPoints[0];
                    yPosition += membraneData.data.get(i).getHeight();

//                    temp.setyPosition(yPosition+lineHeight);
//                    temp.setxPosition(xPoints[0]);
////                    createNewLine();
                    System.out.println(membraneData.data.get(i).getText());
//                    membraneData.data.add(i , createText(SpecialCharacter.lineStart.getCommand()));
//                    blinker.selectedCharacterIndex = i+1;
//                    rerender();
//                    break;
//                    System.out.println("out bounding: " + temp.getText());
                }
                gc.setFill(Color.BLACK);
                gc.setFont(temp.getFont().toFont());
//                System.out.println("kussy: "+temp.font.toFont().getSize());
                System.out.println(temp.getFont().getSize());
                gc.fillText(temp.getText(), xPosition,yPosition);


                //                System.out.println("font size: "+temp.getFont().getSize());
                if(temp.selected){
                    gc.setFill(Color.rgb(51, 153, 255, .5));
                    gc.fillRect(temp.getxPosition(), temp.getyPosition() - temp.getHeight(), temp.getWidth() + .1, temp.getHeight());
                    gc.setFill(Color.BLACK);
                }
                if(temp.isUnderline())
                {
                    gc.strokeLine(temp.getxPosition() , yPosition   ,xPosition+temp.getWidth()  , yPosition );
                }
                else if(temp.isStrikethrough())
                {
                    gc.strokeLine(xPosition , yPosition  - .5*(temp.getHeight()),xPosition+temp.getWidth()  , yPosition -.5*(temp.getHeight())  );
                }


                temp.setyPosition(yPosition);
                temp.setxPosition(xPosition);
                xPosition+= temp.width + temp.width*characterSpacing;

            }


        }
        blinker.rerender();
//        System.out.println("++++++++++++++++++++++++++++++++");
//        for (var d : membraneData.data) System.out.println(d.getText());
//        System.out.println("++++++++++++++++++++++++++++++++");
        return startingLine;
    }


    public void removeCharFront() {
        if(blinker.selectedCharacterIndex <= 0 || removeSelected() || (membraneData.data.get(blinker.selectedCharacterIndex).getText().charAt(0) == '&' && membraneData.data.get(blinker.selectedCharacterIndex).getText().charAt(1) == 't')) return;
        membraneData.data.remove(blinker.selectedCharacterIndex--);
        rerender();
    }
    public void removeCharBack() {

        if(blinker.selectedCharacterIndex+1>= membraneData.data.size() || (membraneData.data.get(blinker.selectedCharacterIndex).getText().charAt(0) == '&' && membraneData.data.get(blinker.selectedCharacterIndex).getText().charAt(1) == 't') ) return;
        membraneData.data.remove(blinker.selectedCharacterIndex+1);
        rerender();
    }

    public void moveTheBlinkerHorizontally(int i) {
        if(blinker.selectedCharacterIndex + i >= membraneData.data.size()
                || blinker.selectedCharacterIndex <= -i ) return;
        if(Objects.equals(membraneData.data.get(blinker.selectedCharacterIndex + i).getText(), " ") || Objects.equals(membraneData.data.get(blinker.selectedCharacterIndex + i).getText(), SpecialCharacter.lineStart.getCommand())) {
            i*=3;
        }
        blinker.selectedCharacterIndex += i;
    }

    public void moveTheBlinkerVertically(int i) {
        if(startingLine.size() <= 1)return;
        int linPointing = TextUtil.lower_bound(startingLine.toArray(), blinker.selectedCharacterIndex) - 1;
        if(i == 1) {
            if (linPointing == 0) return;
            blinker.selectedCharacterIndex = startingLine.get(linPointing - 1) + blinker.selectedCharacterIndex - startingLine.get(linPointing) + i -1;
        }
        else {
            if(Objects.equals(startingLine.getLast(), startingLine.get(linPointing))) return;
            if(startingLine.get(linPointing+1) + blinker.selectedCharacterIndex - startingLine.get(linPointing) + i +1 >= membraneData.data.size()) {
                blinker.selectedCharacterIndex = membraneData.data.size() - 1;
                return;
            }
            blinker.selectedCharacterIndex = startingLine.get(linPointing+1) + blinker.selectedCharacterIndex - startingLine.get(linPointing) + i +1;

        }

    }

//    public void addTable() {
//        addCharacter(SpecialCharacter.lineStart.getCommand());
//        addCharacter(SpecialCharacter.table.getCommand());
//        addCharacter(SpecialCharacter.tableRow.getCommand());
//        addCharacter("150");
//        addCharacter(SpecialCharacter.tableHeadColumn.getCommand());
//        addCharacter("145");
//        addCharacter("H" , new Font(16));
//        addCharacter("e" , new Font(16));
//        addCharacter("a" , new Font(16));
//        addCharacter("d" , new Font(16));
//        addCharacter(SpecialCharacter.tableHeadColumnEnd.getCommand());
//        addCharacter(SpecialCharacter.tableHeadColumn.getCommand());
//        addCharacter("145");
//        addCharacter("H" , new Font(16));
//        addCharacter("e" , new Font(16));
//        addCharacter("a" , new Font(16));
//        addCharacter("d" , new Font(16));
//        addCharacter("2" , new Font(16));
//        addCharacter(SpecialCharacter.tableHeadColumnEnd.getCommand());
//        addCharacter(SpecialCharacter.tableRowEnd.getCommand());
//        addCharacter(SpecialCharacter.tableRow.getCommand());
//        addCharacter("50");
//        addCharacter(SpecialCharacter.tableColumn.getCommand());
//        addCharacter("H" , new Font(16));
//        addCharacter("e" , new Font(16));
//        addCharacter("a" , new Font(16));
//        addCharacter("d" , new Font(16));
//        addCharacter("2" , new Font(16));
//        addCharacter(SpecialCharacter.tableColumnEnd.getCommand());
//        addCharacter(SpecialCharacter.tableColumn.getCommand());
//        addCharacter("H" , new Font(16));
//        addCharacter("e" , new Font(16));
//        addCharacter("a" , new Font(16));
//        addCharacter("d" , new Font(16));
//        addCharacter("2" , new Font(16));
//        addCharacter(SpecialCharacter.tableColumnEnd.getCommand());
//        addCharacter(SpecialCharacter.tableRowEnd.getCommand());
//        addCharacter(SpecialCharacter.tableEnd.getCommand());
//        addCharacter(SpecialCharacter.lineStart.getCommand());
//
//
//    }
}
