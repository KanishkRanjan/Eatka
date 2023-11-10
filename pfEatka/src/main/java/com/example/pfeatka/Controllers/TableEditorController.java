package com.example.pfeatka.Controllers;

import com.example.pfeatka.Element.Membrane;
import com.example.pfeatka.Table.tableRender;
import com.example.pfeatka.Utils.MouseData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.ResourceBundle;

public class TableEditorController implements Initializable {
    @FXML
    private AnchorPane parentAnchor;
    @FXML
    private ColorPicker colorPickerBtn;

    @FXML
    private Button  generateTableBtn;

    @FXML
    private TextField numberOfColumnField;

    @FXML
    private TextField numberOfRowsField;

    @FXML
    private Canvas playGround;
    @FXML
    private Button saveBtn;

    @FXML
    private Button zoomNegative;

    @FXML
    private Button zoomPositive;

    MouseData mouseData = new MouseData();
    tableRender tb = null;
    GraphicsContext gc = null;

    Membrane membrane;

    public TableEditorController(Membrane membrane){
        this.membrane = membrane;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = playGround.getGraphicsContext2D();
        generateTableBtn.setOnAction(this::generateTable);
        gc.setFill(Color.WHITE);
        gc.fillRect(0 , 0 , playGround.getWidth() , playGround.getHeight());
        gc.setFill(Color.BLACK);
        saveBtn.setOnAction(e->{
            if(tb == null) return;
            tb.addTableToMembrane();
        });
        parentAnchor.widthProperty().addListener((observableValue, oldValue, newValue) -> {
            playGround.setWidth(newValue.intValue());
            gc.setFill(Color.WHITE);
            gc.fillRect(0 , 0 , playGround.getWidth() , playGround.getHeight());
            gc.setFill(Color.BLACK);
            if(tb != null) tb.render();
        });
//

        parentAnchor.heightProperty().addListener((observableValue, oldValue, newValue) -> {
            playGround.setHeight(newValue.intValue());
            gc.setFill(Color.WHITE);
            gc.fillRect(0 , 0 , playGround.getWidth() , playGround.getHeight());
            gc.setFill(Color.BLACK);
            if(tb != null) tb.render();
        });

        zoomPositive.setOnAction(e->{
            playGround.setScaleX(playGround.getScaleX()+.1);
            playGround.setScaleY(playGround.getScaleY()+.1);
        });
        zoomNegative.setOnAction(e->{
            playGround.setScaleX(playGround.getScaleX()-.1);
            playGround.setScaleY(playGround.getScaleY()-.1);
        });
        playGround.setOnMouseMoved(e ->{
            mouseData.current.first = e.getX();
            mouseData.current.second = e.getY();

        });
        playGround.setOnMouseDragged(e->{
            mouseData.current.first = e.getX();
            mouseData.current.second = e.getY();
            if(tb != null) {

                tb.isCollision(mouseData);
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
        colorPickerBtn.setOnAction(e->{
            if(tb != null) {
                System.out.println(colorPickerBtn.getValue());
                tb.color = colorPickerBtn.getValue();
            }
        });

    }

    private void generateTable(ActionEvent actionEvent) {

        tb = new tableRender(gc);
        tb.membrane = this.membrane;
        tb.setRowAndColumn(Integer.parseInt(numberOfRowsField.getText()), Integer.parseInt(numberOfColumnField.getText()));
        tb.render();
    }
}
