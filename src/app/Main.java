package app;

import app.core.Window;
import app.sendbox.Example;

import java.awt.*;

public class Main {

    public static void main(String[] args){

        Window window = new Window("Window", 650, 450);
        window.addLayer(new Example("Layer 1", Color.BLUE));
        window.addLayer(new Example("Layer 2", Color.RED));
        window.addLayer(new Example("Layer 3", Color.GREEN));

    }
}
