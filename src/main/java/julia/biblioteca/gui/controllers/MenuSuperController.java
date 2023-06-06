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

/**
 * Menu do super usuário que implementa a interface Initializable
 */
public class MenuSuperController implements Initializable {
    /**
     * Button para opção 1
     */
    @FXML
    private Button button_op1;
    /**
     * Button para opção 2
     */
    @FXML
    private Button button_op2;
    /**
     * Button para opção 3
     */
    @FXML
    private Button button_op3;
    /**
     * Button para opção 4
     */
    @FXML
    private Button button_op4;
    /**
     * Button para deslogar
     */
    @FXML
    private Button button_deslogin;
    /**
     * Label para alerta se tiver algum erro
     */
    @FXML
    private Label alarme;

    /**
     * Checa as opções e chama o template adequado
     * 1) Adicionar Itens
     * 2) Verifica se tem usuário e se tiver mostra os usuários
     * 3) Ver os itens da biblioteca
     * 4) Pesquisar o item
     * 5) Deslogar
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
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
