package com.example.pfeatka.Views;

import com.example.pfeatka.Controllers.ImageEditorController;

import com.example.pfeatka.Controllers.MainController;
import com.example.pfeatka.Controllers.TableEditorController;
import com.example.pfeatka.Controllers.askNameController;
import com.example.pfeatka.Element.Membrane;

import com.example.pfeatka.Element.TemplateContainer;
import com.example.pfeatka.Models.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;


public class ViewFactory {


    private Scene scene = null;

    public  Scene getScene()
    {
        return scene;
    }
    private static final Stage stage = new Stage();
    public static Stage getStage()
    {
        return stage;
    }

    public Stage editorWindow = null;
    public void showWindow()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Main.fxml"));
        loader.setController(new MainController());
        try {
            scene = new Scene(loader.load());
            editorWindow  =new Stage();
            editorWindow.setScene(scene);
            editorWindow.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void showWindow(String loc)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Main.fxml"));
        loader.setController(new MainController(loc));
        try {
            scene = new Scene(loader.load());
            editorWindow  =new Stage();
            editorWindow.setScene(scene);
            editorWindow.show();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Stage popup;


    private Stage tableStage = null;
    public void showTableEditor(Membrane membrane)  {
        FXMLLoader loader ;
        loader = new FXMLLoader(getClass().getResource("/Fxml/Membrane/tableEditor.fxml"));
        loader.setController(new TableEditorController(membrane));
        try {
            scene = new Scene(loader.load());
            tableStage  =new Stage();
            tableStage.setScene(scene);
            tableStage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public  void closeTableEditor(){
        closeStage(tableStage);
    }

    public void showImageEditor( Membrane membrane)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Membrane/imageEditor.fxml"));
        loader.setController(new ImageEditorController(membrane));
        try {
            scene = new Scene(loader.load());
            tableStage = new Stage();
            tableStage.setScene(scene);
            tableStage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public  void closeImageEditor(){
        closeStage(tableStage);
    }

    private void createStage(FXMLLoader loader)
    {

        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Image/Icon/hexagon.png"))));
        stage.setScene(scene);
        stage.setTitle("Eatka");

        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }

    AnchorPane emptyView;
    ///Adding stages
    //location of resources getClass().getResource("/Fxml/Client/empty.fxml")
    public AnchorPane getEmptyView()
    {
        if(emptyView == null)
        {
            try {
                emptyView = new FXMLLoader().load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return emptyView;
    }


}
