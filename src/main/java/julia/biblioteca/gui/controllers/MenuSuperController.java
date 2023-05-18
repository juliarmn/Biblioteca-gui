package julia.biblioteca.gui.controllers;

import javafx.scene.control.Label;
import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuSuperController implements Initializable {
    @FXML
    private Button button_op1;
    @FXML
    private Button button_op2;
    @FXML
    private Button button_op3;
    @FXML
    private Button button_op4;
    @FXML
    private Button button_deslogin;
    @FXML
    private Label alarme;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_op1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "AdicionarItem.fxml", "Adicionar Item");
            }
        });
        button_op2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (biblioteca.getUsuarios().size() > 0) {
                    DBUtils.changeScene(event, "VerUsers.fxml", "Usuários");
                } else {
                    alarme.setText("Sem usuário");
                }
            }
        });
        button_op3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "verItensBiblioteca.fxml", "Ver itens");
            }
        });
        button_op4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "PesquisarItem.fxml", "Busca");
            }
        });
        button_deslogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Login");
            }
        });
    }
}
