package com.example.pfeatka.Element;

import com.example.pfeatka.Utils.MouseData;
import javafx.scene.canvas.GraphicsContext;

public abstract class  DocumentElementBase {
    public double xPoints[] ;
    public double yPoints[];

    private GraphicsContext gc;

    public abstract boolean isColliding(MouseData mouseData);

    public abstract void collisionAction(MouseData mouseData);

    public abstract void onSelect();

    public abstract void render();


}
