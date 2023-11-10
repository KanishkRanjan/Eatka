package com.example.pfeatka.Controllers;

import com.example.pfeatka.Element.Membrane;
import com.example.pfeatka.Element.Text.SpecialCharacter;
import com.example.pfeatka.Image.ImageBox;
import com.example.pfeatka.Image.ImageRender;

import com.example.pfeatka.Models.Model;
import com.example.pfeatka.Utils.FilePicker;
import com.example.pfeatka.Utils.MouseData;

import com.example.pfeatka.Utils.Pair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ImageEditorController implements Initializable {

    @FXML
    private Button drawBtn;

    @FXML
    private Button eraseBtn;

    @FXML
    private Canvas playGround;

    @FXML
    private Button saveBtn;

    @FXML
    private Button loadImageBtn;
    @FXML
    private AnchorPane parentAnchor;

    private final Membrane membrane;


    public GraphicsContext gc ;

    public ImageRender imageRender;
    MouseData mouseData = new MouseData();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        drawBtn.setOnAction(e-> gc.setFill(Color.BLACK));

        eraseBtn.setOnAction(e-> gc.setFill(Color.WHITE));
        loadImageBtn.setOnAction(this::loadImage);

        this.gc = playGround.getGraphicsContext2D();
        playGround.setOnMouseMoved(e ->{
            mouseData.current.first = e.getX();
            mouseData.current.second = e.getY();

        });
        playGround.setOnMouseDragged(e->{
            mouseData.current.first = e.getX();
            mouseData.current.second = e.getY();
            if(imageRender != null) {

                imageRender.manageCollision(mouseData);
            }
        });
        playGround.setOnMousePressed(e->{
            mouseData.first.first = e.getX();
            mouseData.first.second = e.getY();
        });
        playGround.setOnMouseReleased(e->{
            mouseData.release.first = e.getX();
            mouseData.release.second = e.getY();
        });
        saveBtn.setOnAction(e-> addImageToMembrane());

//        parentAnchor.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
//                playGround.setWidth(newValue.intValue());
//                if(imageRender != null) imageRender.rerender();
//            }
//        });
////
//
//        parentAnchor.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
//                playGround.setHeight(newValue.intValue());
//                if(imageRender != null) imageRender.rerender();
//            }
//        });

    }

    private void loadImage(ActionEvent actionEvent) {
        FilePicker f = new FilePicker();
        f.showPopup();
        imageRender = new ImageRender(f.selectedFile , gc);
        imageRender.render();
    }

    public void addImageToMembrane(){
        membrane.addCharacter(SpecialCharacter.imageBlock.getCommand());
        membrane.membraneData.imageStorage.add(new ImageBox( 0 , 0 , imageRender.imageWidth  , imageRender.imageHeight , "file:"+ imageRender.location));
        Model.getInstance().getViewFactory().closeImageEditor();
    }
    public ImageEditorController(Membrane membrane){
        this.membrane = membrane;
    }
}

