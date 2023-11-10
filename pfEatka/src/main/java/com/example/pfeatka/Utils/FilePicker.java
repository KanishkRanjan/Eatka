package com.example.pfeatka.Utils;

import com.example.pfeatka.Models.Model;
import javafx.stage.FileChooser;

public class FilePicker {
    FileChooser fileChooser = new FileChooser();
    public String selectedFile = "";
    public void showPopup() {
        fileChooser.setTitle("Select the picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG", "*.jpeg", "*.jpg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));


        selectedFile = fileChooser.showOpenDialog(Model.getInstance().getViewFactory().getScene().getWindow()).getAbsolutePath();
    }
}
