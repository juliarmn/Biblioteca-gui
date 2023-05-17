package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PagarMultaController implements Initializable {
    @FXML
    private Label label_multa;
    @FXML
    private TextField tf_senha;
    @FXML
    private Button button_pagar;
    @FXML
    private Button button_voltar;
    @FXML
    private Label alerta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
        label_multa.setText(String.valueOf(biblioteca.getContaLogada().getMulta()));
        button_pagar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_senha.getText().equals("")) {
                    alerta.setText("Todos os campos devem ser preenchidos");
                } else {
                    if (biblioteca.getContaLogada().verificarSenha(tf_senha.getText()))  {

                        biblioteca.getContaLogada().setMulta(0);
                        label_multa.setText("0");
                        alerta.setText("Multa paga");
                    } else {
                        alerta.setText("Senha inválida");
                    }
                }
            }
        });
        button_voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "MenuUser.fxml", "Menu");
            }
        });
    }
}
