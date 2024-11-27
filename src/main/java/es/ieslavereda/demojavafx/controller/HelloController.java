package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.model.Animal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private Button btnStart;
    @FXML
    private ProgressBar pbTortuga;
    @FXML
    private ProgressBar pbLiebre;

    @FXML
    protected void onHelloButtonClick() {
        pbTortuga.setProgress(0);
        pbLiebre.setProgress(0);

        Animal conejo = new Animal("Conejo",pbLiebre,pbTortuga);
        Animal tortuga = new Animal("Tortuga",pbTortuga,pbLiebre);


        conejo.correr();
        tortuga.correr();







    }
}