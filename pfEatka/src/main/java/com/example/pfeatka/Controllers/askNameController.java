package com.example.pfeatka.Controllers;

import com.example.pfeatka.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class askNameController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button okayBtn;

    @FXML
    private TextField templateName;

    public Stage pop ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            cancelBtn.setOnAction(e->{
               pop.close();
//               Model.getInstance().getViewFactory().
            });
            okayBtn.setOnAction(e->{
                pop.close();
            });
    }
    public askNameController(Stage pop){
        this.pop = pop;
    }
    public String getTemplateName(){
        return templateName.getText();
    }
}
