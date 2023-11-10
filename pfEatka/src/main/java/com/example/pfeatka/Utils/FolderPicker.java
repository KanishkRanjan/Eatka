package com.example.pfeatka.Utils;

import com.example.pfeatka.Models.Model;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;


public class FolderPicker {
    DirectoryChooser directoryChooser = new DirectoryChooser();

    public String selectedFile = "";
    public void showPopup(String fileName){
        directoryChooser.setTitle("Choose a folder");
        File selectedDirectory = directoryChooser.showDialog(Model.getInstance().getViewFactory().getScene().getWindow());
        selectedFile = selectedDirectory + "\\"+ fileName+".eat";
    }
}

//    FileChooser fileChooser = new FileChooser();
//    public String selectedFile = "";
//    public void showPopup() {
//        fileChooser.setTitle("Select the picture");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG", "*.jpeg", "*.jpg"));
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
//
//
//        selectedFile = fileChooser.showOpenDialog(Model.getInstance().getViewFactory().getScene().getWindow()).getAbsolutePath();
//    }