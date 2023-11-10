package com.example.pfeatka.Utils;

import com.example.pfeatka.Element.Text.TextObj;
import com.example.pfeatka.Image.ImageBox;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.LinkedList;

public class MembraneData implements Serializable {
    public LinkedList<TextObj> data = new LinkedList<>();
    public LinkedList<ImageBox> imageStorage = new LinkedList<>();
}
