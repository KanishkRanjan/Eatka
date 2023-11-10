package com.example.pfeatka.Models;

import com.example.pfeatka.Views.ViewFactory;

public class Model {
    private static Model model;

    private final ViewFactory viewFactory;

    Model(){
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance()
    {
        if( model == null) model =new Model();
        return model;
    }
    public ViewFactory getViewFactory() { return  viewFactory ;}

}
