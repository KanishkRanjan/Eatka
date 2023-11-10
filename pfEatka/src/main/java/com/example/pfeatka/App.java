package com.example.pfeatka;

import com.example.pfeatka.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showWindow();
//        Model.getInstance().getViewFactory().showImageEditor();
    }
    public static void main(String[] args) throws InternalError {
        launch(args);
    }
}
