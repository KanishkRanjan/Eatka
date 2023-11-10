package com.example.pfeatka.Element;

import com.example.pfeatka.Element.Image.ImageHolder;
import com.example.pfeatka.Element.Text.TextObj;
import com.example.pfeatka.Image.ImageBox;

import java.io.Serializable;
import java.util.LinkedList;

public class TemplateContainer implements Serializable {
    public String name = "Template";
    public LinkedList<ImageBox> imageHolders = new LinkedList<>();
    public LinkedList<TextObj> textObjs = new LinkedList<>();
}
