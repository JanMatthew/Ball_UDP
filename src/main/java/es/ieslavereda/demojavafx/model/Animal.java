package es.ieslavereda.demojavafx.model;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class Animal  {
    private String nombre;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;
    private MyTask task;

    public Animal(String nombre, ProgressBar progressBar, ProgressBar progressBar2) {
        this.nombre = nombre;
        this.progressBar = progressBar;
        this.progressBar2 = progressBar2;
    }

    public void correr(){
        task = new MyTask(nombre,progressBar,progressBar2);
        Thread thread = new Thread(task);
        thread.start();
    }


    private class MyTask extends Task<ProgressBar> {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        String nombre;


        public MyTask(String nombre,ProgressBar progressBar,ProgressBar progressBar2) {
            this.progressBar = progressBar;
            this.progressBar2 = progressBar2;
            this.nombre = nombre;
        }
        @Override
        protected ProgressBar call() throws Exception {
            Double res=0.0;
            Double newProgres = 0.0;
            while (true){
                synchronized (progressBar2) {
                    if (progressBar2.getProgress() >= 1.0) {
                        break;
                    }
                }
                synchronized (progressBar){

                        int ran = (int) (Math.random() * 101);
                        if (nombre.equals("Tortuga")) {
                            if (ran <= 50) {
                                res = 0.03;
                            } else if (ran <= 70) {
                                res = -0.06;
                            } else {
                                res = 0.01;
                            }
                        } else {
                            if (ran <= 20) {
                                res = 0.00;
                            } else if (ran <= 40) {
                                res = 0.09;
                            } else if (ran <= 50) {
                                res = -0.12;
                            } else if (ran <= 80) {
                                res = 0.01;
                            } else {
                                res = 0.02;
                            }
                        }
                        if (progressBar.getProgress() + res < 0) {
                            newProgres = 0.01;
                        } else {
                            newProgres = progressBar.getProgress() + res;
                        }
                        progressBar.setProgress(newProgres);
                }
                synchronized (progressBar2) {
                    if (progressBar2.getProgress() >= 1.0 && progressBar.getProgress() >= 1.0) {
                        System.out.println("EMPATE");
                        break;
                    }
                    else if (progressBar2.getProgress() >= 1.0) {
                        break;
                    }else if (progressBar.getProgress() >= 1.0) {
                        System.out.println("Gana: " + nombre);
                        break;
                    }
                }
                Thread.sleep(100);


            }
            return null;
        }
    }
}
