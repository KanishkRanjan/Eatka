package com.example.pfeatka.Controllers;


import com.example.pfeatka.Element.BlueBox;
import com.example.pfeatka.Element.TemplateContainer;
import com.example.pfeatka.Element.Text.SpecialCharacter;
import com.example.pfeatka.Element.Text.TextObj;
import com.example.pfeatka.Models.Model;
import com.example.pfeatka.Utils.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable , Serializable {
    @FXML
    public ScrollPane scrollPane;
    @FXML
    private ChoiceBox<Integer> zoomPercent;

    @FXML
    private TextField fontSize;
    @FXML
    private TextField fileName;
    @FXML
    public VBox ScrollPaneContainer;
    @FXML
    public Button addPage;
    public ChoiceBox<String> fontFamilyBtn;

    @FXML
    private Button increaseFontBtn;
    @FXML
    private Button decreaseFontBtn;
    @FXML
    public Button addTableBtn;
    @FXML
    private Button italicBtn;
    @FXML
    private Button boldBtn;
    @FXML
    private Button strikeThroughBtn;
    @FXML
    private Button underlineBtn;
    @FXML
    private Button openBtn;
    @FXML
    private HBox templateStore;
    @FXML
    private Button saveAsBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button addImageBtn;

    @FXML
    private Button templateAddBtn ;
    @FXML
    private Button addButn;

    boolean isUnderline = false;

    boolean isStrikeThrough = false;

    PageContainer pageContainers =null;
    LinkedList<MembraneData> membraneDataLinkedList = new LinkedList<>();
    String fileLocation ="";

    String fileSaveName = "";
    public void mouseClickedOnPage(double x , double y)
    {


        Page page = pageContainers.pages.get(showingPage);
        page.mouseData.isMouseDown = true;
        page.mouseData.first = new Pair<>(x , y);
        int i = page.getMembrane().getCollisionIndex(page.mouseData.first);

        if(i == -1 || !page.getMembrane().membraneData.data.get(i).selected){
            if(i != -1) {
                page.getMembrane().blinker.setBlinkerPosition(i);
            }
            for(var d : page.getMembrane().membraneData.data){
                d.selected = false;
            }
        }
        blueBox.xPoints[0] = page.mouseData.first.first;
        blueBox.yPoints[0] = page.mouseData.first.second;
        page.getMembrane().rerender();
    }


    BlueBox blueBox  =  new BlueBox();

    private void mouseMoveOnPage(double x , double y) {
        Page page = pageContainers.pages.get(showingPage);
        page.mouseData.current = new Pair<>(x, y);
        if(page.mouseData.isMouseDown) {
            int i = page.getMembrane().getCollisionIndex(page.mouseData.first);

            int j = page.getMembrane().getCollisionIndex(page.mouseData.current);
            if(i == -1) {
                page.getMembrane().rerender();
                page.getMembrane().gc.setFill(Color.rgb(51, 153, 255, .5));
                blueBox.xPoints[1] = page.mouseData.current.first;
                blueBox.yPoints[1] = page.mouseData.current.second ;
                blueBox.actable = true;
                page.getMembrane().gc.fillPolygon(
                        new double[]{
                                blueBox.xPoints[0] , blueBox.xPoints[1] ,blueBox.xPoints[1] ,  blueBox.xPoints[0]} ,
                        new double[]{
                                blueBox.yPoints[0],  blueBox.yPoints[0], blueBox.yPoints[1], blueBox.yPoints[1]} ,
                        4
                );

                page.getMembrane().gc.setFill(Color.BLACK);

                return;
            }
            for(; i <= j  ; i++) {
                page.getMembrane().membraneData.data.get(i).selected = true;
            }
            
            page.getMembrane().blinker.setBlinkerPosition(i-1);
            page.getMembrane().rerender();
        }
    }

    private void mouseReleaseOnPage(double x , double y) {
        System.out.println(pageContainers.pages.get(showingPage).canvas.getId());
        Page page = pageContainers.pages.get(showingPage);
        page.mouseData.isMouseDown =  false;
        page.mouseData.release.first =x;
        page.mouseData.release.second =y;
        if(blueBox.actable){
            for(int i =  0 ; i <  page.getMembrane().membraneData.data.size() ; i++){
                var v = page.getMembrane().membraneData.data.get(i);
//                if((Objects.equals(v.getText(), SpecialCharacter.imageBlock.getCommand())))
                if(false)
                {
                    System.out.println("image block");
                    System.out.println(
                            (v.getxPosition() +v.width > Math.min(page.mouseData.first.first, page.mouseData.release.first))+ "&& "+
                            (v.getxPosition()  < Math.max(page.mouseData.first.first, page.mouseData.release.first)) +"&&"+
                            (v.getyPosition() + v.height > Math.min(page.mouseData.first.second, page.mouseData.release.second))+ "&&"+
                            (v.getyPosition()  < Math.max(page.mouseData.first.second, page.mouseData.release.second))
                    );
                }
                else
                {
                    if (
                            v.getxPosition() + v.width  > Math.min(page.mouseData.first.first, page.mouseData.release.first) &&
                            v.getxPosition() < Math.max(page.mouseData.first.first, page.mouseData.release.first) &&
                            v.getyPosition()  + v.height > Math.min(page.mouseData.first.second, page.mouseData.release.second) &&
                            v.getyPosition() < Math.max(page.mouseData.first.second, page.mouseData.release.second)
                    ) {
                        v.selected = true;
                    }
                }
            }
        }
        blueBox.actable = false;
        page.getMembrane().rerender();
//        Page page = pageContainers.pages.get(showingPage);
//        page.mouseData.current = new Pair<>(x, y);
//        if(page.mouseData.isMouseDown) {
//            int i = page.getMembrane().getCollisionIndex(page.mouseData);
//            if(i == -1) return;
//            page.getMembrane().membraneData.data.get(i).selected = true;
//            page.getMembrane().blinker.setBlinkerPosition(i);
//            page.getMembrane().rerender();
//        }

//                int j = page.getMembrane().getCollisionIndex(page.mouseData.current);
    }
    int showingPage = 0;
    int pageNumber = 0;


    public void addPageInPlayGround()
    {
        pageContainers.addPageOnNumber(sheet , pageNumber );
        showingPage = pageNumber;
        pageContainers.pages.get(showingPage).canvas.setId(String.valueOf(showingPage));
        pageContainers.pages.get(showingPage).canvas.setOnMouseReleased(e -> mouseReleaseOnPage(e.getX() ,e.getY()));
        pageContainers.pages.get(showingPage).canvas.setOnMousePressed(e-> mouseClickedOnPage(e.getX() , e.getY()));
        pageContainers.pages.get(showingPage).canvas.setOnMouseMoved(e-> mouseMoveOnPage(e.getX() ,e.getY()));
        pageContainers.pages.get(showingPage).canvas.setOnMouseDragged(e -> mouseMoveOnPage(e.getX() ,e.getY()));
        pageContainers.pages.get(showingPage).getMembrane().render();
        ScrollPaneContainer.getChildren().add(pageContainers.pages.get(showingPage).canvas);
        pageNumber++;
    }


//    public void loadPages(String loc){
//        pageContainers.loadFile(loc);
//        for(int i = 0; i < pageContainers.pages.size() ; i++){
//            ScrollPaneContainer.getChildren().add(pageContainers.pages.get(i).canvas);
//        }
//    }
//
//    public void savePages(String loc){
//        pageContainers.saveFile(loc);
//        System.out.println("saved");
//    }
//


    public void saveAs(){
        FolderPicker folderPicker = new FolderPicker();
        folderPicker.showPopup(fileName.getText());
        fileLocation = folderPicker.selectedFile;
        save();
//        FilePicker f = new FilePicker();
//        f.showPopup();
//        fileLocation = f.selectedFile;
//        save();
    }

    public void save(){
        if(fileLocation == ""){
            saveAs();
        } else {
            for (int i = 0; i < pageContainers.pages.size(); i++) {
                membraneDataLinkedList.add(pageContainers.pages.get(i).getMembrane().membraneData);
            }
            FileManager<LinkedList<MembraneData>> fileManager = new FileManager<>();
            fileManager.writeFile(fileLocation,membraneDataLinkedList);
        }
    }

    public MainController(String loc){
        FileManager<LinkedList<MembraneData>> fileManager = new FileManager<LinkedList<MembraneData>>();
        membraneDataLinkedList = fileManager.readFile(loc);
        File sfile = new File(loc);
        fileSaveName = sfile.getName().substring(0 , sfile.getName().length()-4);
        fileLocation = loc;

    }
    public MainController(){};
    private void setFontFamily(ActionEvent event) {
        currFont = new SerializableFont(Font.font(fontFamilyBtn.getValue() , currFont.toFont().getSize()));
    }
    Sheet sheet = Sheet.a4;
    SerializableFont currFont ;
    TemplateContainer templateData = new TemplateContainer();
    LinkedList<TemplateContainer> templateContainers = new LinkedList<>();
    Scene thisScene ;
    public void testing(String name){
        System.out.println(name);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //KeyCombination
//        thisScene = Model.getInstance().getViewFactory().editorWindow.getScene();
//        thisScene.addEventFilter(KeyEvent.KEY_PRESSED , event -> {
//            if(event.getCode() == KeyCode.S && event.isControlDown()){
//                save();
//            }
//            else if()
//        });

        if(fileSaveName != "")
        {
            fileName.setText(fileSaveName);
        }
        fileName.setOnAction(e->{
            fileSaveName = fileName.getText();
        });
        scrollPane.setContent(ScrollPaneContainer);
        currFont = new SerializableFont(new  Font("Times New Roman" , Double.parseDouble(fontSize.getText())));
        ScrollPaneContainer.setAlignment(Pos.TOP_CENTER);
        ScrollPaneContainer.setPadding(new Insets(10 , 0 , 0 , 10));
        fontFamilyBtn.getItems().addAll(Font.getFamilies());
        fontFamilyBtn.setValue(currFont.toFont().getFamily());
        fontFamilyBtn.setOnAction(this::setFontFamily);
        templateAddBtn.setOnAction(e -> {
            templateData = new TemplateContainer();
            templateData.textObjs.add(pageContainers.pages.get(showingPage).getMembrane().createText(SpecialCharacter.lineStart.getCommand()));
            double maxHeight = 0;
            for (var d : pageContainers.pages.get(showingPage).getMembrane().membraneData.data){
                if(d.selected){
                    templateData.textObjs.add(d);
                    if((Objects.equals(d.getText(), SpecialCharacter.imageBlock.getCommand()))){
                        templateData.imageHolders.add(pageContainers.pages.get(showingPage).getMembrane().membraneData.imageStorage.get(d.imgIndex));
                    }
                    maxHeight = Math.max(maxHeight , d.height) ;
                }
            }
            templateData.textObjs.add(pageContainers.pages.get(showingPage).getMembrane().createText(SpecialCharacter.lineStart.getCommand()));
            templateData.name = templateData.textObjs.get(3).data;
            templateData.textObjs.getFirst().height = maxHeight;
        });
        addButn.setOnAction(e ->{
            int imgIndex = 0;
            pageContainers.pages.get(showingPage).getMembrane().addCharacter(SpecialCharacter.lineStart.getCommand());
            for(var b : templateData.textObjs){

                if((Objects.equals(b.getText(), SpecialCharacter.imageBlock.getCommand()))) {
                    pageContainers.pages.get(showingPage).getMembrane().membraneData.imageStorage.add(
                            templateData.imageHolders.get(imgIndex++));
                }
                pageContainers.pages.get(showingPage).getMembrane().membraneData.data.add(new TextObj(b));
            }
            pageContainers.pages.get(showingPage).getMembrane().blinker.setBlinkerPosition(pageContainers.pages.get(showingPage).getMembrane().membraneData.data.size()-1);
            pageContainers.pages.get(showingPage).getMembrane().rerender();
            Button k = new Button();
            k.setText(templateData.name);
            k.setOnAction(ed ->{
                testing(templateData.name);
            });
            templateStore.getChildren().add(k);
        });


        saveBtn.setOnAction(e -> save());
        saveAsBtn.setOnAction(e -> saveAs());
        openBtn.setOnAction(e->{
            if(fileName.getText() == "") return;
            FilePicker f = new FilePicker();
            f.showPopup();
            Model.getInstance().getViewFactory().showWindow(f.selectedFile);
        });
        addTableBtn.setOnAction(e-> Model.getInstance().getViewFactory().showTableEditor(pageContainers.pages.get(showingPage).getMembrane()));
        fontSize.textProperty().addListener(e-> currFont = new SerializableFont(Font.font(Double.parseDouble(fontSize.getText()))));
        addImageBtn.setOnAction(e -> Model.getInstance().getViewFactory().showImageEditor(pageContainers.pages.get(showingPage).getMembrane() ));
        zoomPercent.getItems().addAll(50 , 75 , 90 , 100 ,125 , 150,200);
        zoomPercent.setOnAction(this::zoomed);
        zoomPercent.setValue(100);
        increaseFontBtn.setOnAction(this::increaseFontSize);
        decreaseFontBtn.setOnAction(this::decreaseFontSize);
        boldBtn.setOnAction(this::setFontStyleBold);
        italicBtn.setOnAction(this::setFontStyleItalic);
        underlineBtn.setOnAction(this::setStyleUnderline);
        strikeThroughBtn.setOnAction(this::setStyleStrikeThrough);
        scrollPane.widthProperty().addListener((observableValue, oldValue, newValue) -> ScrollPaneContainer.setPrefWidth(newValue.intValue()));
        scrollPane.heightProperty().addListener((observableValue, oldValue, newValue) -> ScrollPaneContainer.setPrefHeight(newValue.intValue()));
        scrollPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(pageContainers.pages.isEmpty()) return;
            if (event.getCode() == javafx.scene.input.KeyCode.SPACE) {
                pageContainers.pages.get(showingPage).getKeywordManager().resisterKeyPress(event, currFont , isUnderline ,isStrikeThrough);
                event.consume();
            }
            else if(event.getCode() == KeyCode.HOME) {
                scrollPane.setVvalue(0);
            }
            else if(event.getCode() == KeyCode.END) {
                scrollPane.setVvalue(1);
            } else {
//                if(pageContainers.pages.get(showingPage).getMembrane().data.get(pageContainers.pages.get(showingPage).getMembrane().blinker.selectedCharacterIndex))
                pageContainers.pages.get(showingPage).getKeywordManager().resisterKeyPress(event, currFont , isUnderline ,isStrikeThrough);
                pageContainers.pages.get(showingPage).getMembrane().rerender();
            }
            event.consume();
        });
        scrollPane.addEventFilter(KeyEvent.KEY_RELEASED, event ->{
            if(pageContainers.pages.isEmpty()) return;
            if(event.getCode() == KeyCode.SHIFT)
            {
                pageContainers.pages.get(showingPage).getKeywordManager().registerKeyRelease(event);
            }
        });

        pageContainers = new PageContainer(ScrollPaneContainer);
        if(membraneDataLinkedList.isEmpty())
        {
            addPageInPlayGround();
        }
        else {
            for(int i = 0 ;i < membraneDataLinkedList.size() ; i++){
                addPageInPlayGround();
                pageContainers.pages.get(i).getMembrane().membraneData = membraneDataLinkedList.get(i);
                pageContainers.pages.get(i).getMembrane().blinker.selectedCharacterIndex = 0 ;
                for (var image : pageContainers.pages.get(i).getMembrane().membraneData.imageStorage){
                    image.loadImage();
                }
                pageContainers.pages.get(i).getMembrane().rerender();
            }
        }
        addPage.setOnAction(e -> addPageInPlayGround());
    }

    Stage popup = null;
    askNameController askNameControl = null;
//    private void showAskTemplateName() {
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Membrane/askName.fxml"));
//            askNameControl = new askNameController(popup);
//            loader.setController(askNameControl);
//            try {
//                Scene scene = new Scene(loader.load());
//                popup  =new Stage();
//                askNameControl.pop = popup;
//                popup.initOwner(Model.getInstance().getViewFactory().editorWindow);
//                popup.setTitle("Enter template name");
//                popup.setScene(scene);
//                popup.initModality(Modality.APPLICATION_MODAL);
//                System.out.println("nigga: " +askNameControl.getTemplateName());
//                popup.show();
//                popup.setOnCloseRequest(e->{popClosed();});
//
//            }catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//    }


    private void zoomed(ActionEvent actionEvent) {

        System.out.println(zoomPercent.getValue());

        double increaseHeight = 0;
        if(pageContainers == null) return;


        for(int i= 0 ; i < pageContainers.pages.size() ; i++) {
            pageContainers.pages.get(i).canvas.setScaleY(pageContainers.pages.get(i).canvas.getScaleY() + .1);
            pageContainers.pages.get(i).canvas.setScaleX(pageContainers.pages.get(i).canvas.getScaleX() + .1);

            pageContainers.pages.get(i).canvas.setTranslateY((i+1)*(pageContainers.pages.get(i).canvas.getBoundsInParent().getHeight()- pageContainers.pages.get(i).canvas.getHeight() ));
            System.out.println(pageContainers.pages.get(i).canvas.getBoundsInParent().getHeight()- pageContainers.pages.get(i).canvas.getHeight() );
            increaseHeight += pageContainers.pages.get(i).canvas.getBoundsInParent().getHeight() + (pageContainers.pages.get(i).canvas.getBoundsInParent().getHeight()- pageContainers.pages.get(i).canvas.getHeight() );
            ScrollPaneContainer.setPrefWidth(Math.max(scrollPane.getWidth(),(pageContainers.pages.get(i).canvas.getWidth() + 50) * pageContainers.pages.get(i).canvas.getScaleX()));
        }
        ScrollPaneContainer.setPrefHeight(increaseHeight);
    }

    private void setStyleUnderline(ActionEvent actionEvent) {
        isUnderline = !isUnderline;
    }
    private void setStyleStrikeThrough(ActionEvent actionEvent) {
        isStrikeThrough = !isStrikeThrough;
    }

    private void setFontStyleItalic(ActionEvent actionEvent) {
        if (currFont.getStyle().contains("Italic")) {
            currFont = new SerializableFont(Font.font(currFont.toFont().getFamily(), FontPosture.REGULAR, currFont.toFont().getSize()));
        } else {
            currFont = new SerializableFont(Font.font(currFont.toFont().getFamily(), FontPosture.ITALIC, currFont.toFont().getSize()));
        }
    }

    private void setFontStyleBold(ActionEvent actionEvent) {
        if (currFont.getStyle().contains("Bold")) {
            currFont = new SerializableFont(Font.font(currFont.toFont().getFamily(), FontWeight.NORMAL, currFont.toFont().getSize()));

        } else {
            currFont = new SerializableFont(Font.font(currFont.toFont().getFamily(), FontWeight.BOLD, currFont.toFont().getSize()));
        }
    }

    private void decreaseFontSize(ActionEvent actionEvent) {
        currFont = new SerializableFont(Font.font(currFont.toFont().getSize()-2));
    }

    private void increaseFontSize(ActionEvent actionEvent) {
        currFont = new SerializableFont(Font.font(currFont.toFont().getSize()+2));
        System.out.println("current font: "+currFont.getSize());
    }

}
