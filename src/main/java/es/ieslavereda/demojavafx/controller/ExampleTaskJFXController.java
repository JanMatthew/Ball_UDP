package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.model.Filosofo;
import es.ieslavereda.demojavafx.model.RegionColor;
import es.ieslavereda.demojavafx.model.Tenedor;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ExampleTaskJFXController {
    @FXML
    private Label f1;
    @FXML
    private Label f2;
    @FXML
    private Label f3;
    @FXML
    private Label f4;
    @FXML
    private Label f5;
    @FXML
    private Label t1;
    @FXML
    private Label t2;
    @FXML
    private Label t3;
    @FXML
    private Label t4;
    @FXML
    private Label t5;


    public List<Filosofo> setFilosofos(){
        Tenedor tn1 = new Tenedor(t1,0);
        Tenedor tn2 = new Tenedor(t2,1);
        Tenedor tn3 = new Tenedor(t3,0);
        Tenedor tn4 = new Tenedor(t4,1);
        Tenedor tn5 = new Tenedor(t5,0);


        List<Filosofo> filosofos = new ArrayList<>();
        filosofos.add(new Filosofo(f1,tn1,tn5));
        filosofos.add(new Filosofo(f2,tn2,tn1));
        filosofos.add(new Filosofo(f3,tn3,tn2));
        filosofos.add(new Filosofo(f4,tn4,tn3));
        filosofos.add(new Filosofo(f5,tn5,tn4));
        return filosofos;
    }

    @FXML
    public void initialize() {

        List<Filosofo> filosofos = setFilosofos();

        for (Filosofo f: filosofos){
            f.comer();

        }
    }

}

