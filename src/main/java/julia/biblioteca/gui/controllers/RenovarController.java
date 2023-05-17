package julia.biblioteca.gui.controllers;


import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.excessoes.ItemNaoEmprestado;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RenovarController implements Initializable {

    @FXML
    private Label label_mensagem;

    @FXML
    private Button button_voltar;

    public static int i;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
        if (biblioteca.getContaLogada().getMulta() != 0) {
            label_mensagem.setText("Há multas pendentes");
        } else {
            try {
                biblioteca.renovarTodosGUI();
                label_mensagem.setText("Foram adicionado 10 dias.");
            } catch (ItemNaoEmprestado e) {
                label_mensagem.setText(e.getMessage());
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
