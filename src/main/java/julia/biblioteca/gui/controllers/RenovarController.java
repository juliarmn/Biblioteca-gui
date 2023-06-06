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

/**
 * Renovar os itens
 */
public class RenovarController implements Initializable {
    /**
     * Label para a mensagem que indica se conseguiu renovar
     */
    @FXML
    private Label label_mensagem;
    /**
     * Botão para voltar
     */
    @FXML
    private Button button_voltar;
    /**
     * Variável estática para contar os itens a serem renovados
     */
    public static int i;

    /**
     * Verifica se tem multa, caso não tenha, chama um método que renova todos os itens
     * Tem um voltar que volta para o menu
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
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
