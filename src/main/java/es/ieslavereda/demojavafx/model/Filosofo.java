package es.ieslavereda.demojavafx.model;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Filosofo {
    private Tenedor tizq;
    private Tenedor tder;
    private Label label;
    private MyTask task;

    public Filosofo(Label label,Tenedor tizq,Tenedor tder) {
        this.label = label;
        this.tizq = tizq;
        this.tder = tder;

    }

    public void comer(){
        task = new MyTask();
        Thread t = new Thread(task);

        t.start();
    }
    private class MyTask extends Task<RegionColor> {


        public MyTask(){

            valueProperty().addListener((observableValue, regionColor, newValue) -> {
                newValue.getRegion().setBackground(new Background(new BackgroundFill(newValue.getColor(),new CornerRadii(5),new Insets(-5))));
            });

        }
        @Override
        protected RegionColor call() throws Exception {
            while (true){
                Tenedor tiz;
                Tenedor tdr;
                if (tizq.getOrder()>= tder.getOrder()){
                    tiz = tizq;
                    tdr = tder;
                }else {
                    tiz = tder;
                    tdr = tizq;
                }
                synchronized (tiz){
                    updateValue(new RegionColor(tiz.getLabel(),Color.YELLOW));
                    updateValue(new RegionColor(label, Color.YELLOW));
                    synchronized (tdr){
                        updateValue(new RegionColor(tdr.getLabel(), Color.GREEN));
                        updateValue(new RegionColor(tiz.getLabel(), Color.GREEN));
                        updateValue(new RegionColor(label, Color.GREEN));
                        Thread.sleep(2000);
                    }
                    updateValue(new RegionColor(tdr.getLabel(), Color.YELLOW));
                    updateValue(new RegionColor(label, Color.YELLOW));
                }
                updateValue(new RegionColor(tdr.getLabel(),Color.WHITE));
                updateValue(new RegionColor(tiz.getLabel(), Color.WHITE));
                updateValue(new RegionColor(label, Color.RED));

                Thread.sleep(2000);
                updateValue(new RegionColor(label, Color.WHITE));
            }
        }
    }
}
