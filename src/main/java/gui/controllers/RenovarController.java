package gui.controllers;


import classes.Biblioteca;
import classes.DisplayBiblioteca;
import classes.Emprestimo;
import excessoes.ItemNaoEmprestado;
import gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class RenovarController implements Initializable {

    @FXML
    private Label label_mensagem;

    @FXML
    private Button button_voltar;

    @FXML
    private Label alerta;
    public static int i;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
        if (biblioteca.getContaLogada().getMultaGUI().equals("0")) {
            alerta.setText("Há multas pendentes");
        } else {
            try {
                biblioteca.renovarTodosGUI();
                label_mensagem.setText("Empréstimos renovados com sucesso, foram adicionado 10 dias.");
            } catch (ItemNaoEmprestado e) {
                alerta.setText(e.getMessage());
            }
        }
        button_voltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "MenuUser.fxml", "Menu");
            }
        });
    }
}
