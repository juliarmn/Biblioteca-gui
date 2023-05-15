package gui.controllers;

import classes.Biblioteca;
import gui.DBUtils;
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
    private Label alarme;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
        label_multa.setText(biblioteca.getContaLogada().getMultaGUI());
        if (biblioteca.getContaLogada().getMultaGUI().equals("0.00000")) {
            alarme.setText("Não tem multa");
        }
        button_pagar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_senha.getText().equals("")) {
                    alarme.setText("Todos os campos devem ser preenchidos");
                } else {
                    if (biblioteca.getContaLogada().verificarSenha(tf_senha.getText()))  {
                        alarme.setText("");
                        biblioteca.getContaLogada().setMulta(0);
                    } else {
                        alarme.setText("Senha inválida");
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
