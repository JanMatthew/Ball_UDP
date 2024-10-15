package es.ieslavereda.demojavafx.model;

import com.sun.jdi.Value;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class Tenedor {
    private Label label;
    private int order;


    public Tenedor(Label label,int order) {
        this.label = label;
        this.order = order;
    }
    
    public Label getLabel(){
        return label;
    }
    public int getOrder(){
        return order;
    }
}
