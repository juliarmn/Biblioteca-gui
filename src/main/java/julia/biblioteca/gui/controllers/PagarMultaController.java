package julia.biblioteca.gui.controllers;

import javafx.scene.control.PasswordField;
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

/**
 * Controller para o pagamento da multa que implementa o Initializable
 */
public class PagarMultaController implements Initializable {
    /**
     * Label multa -> substituído pela multa do usuário
     */
    @FXML
    private Label label_multa;
    /**
     * TextField da senha
     */
    @FXML
    private PasswordField tf_senha;
    /**
     * BUtton para pagar -> chama método que seta a multa como 0
     */
    @FXML
    private Button button_pagar;
    /**
     * Button para retornar ao menu
     */
    @FXML
    private Button button_voltar;
    /**
     * Alerta se houver erro
     */
    @FXML
    private Label alerta;

    /**
     * Botão pagar chama um método que seta a multa como 0
     * Botão sair retorna ao menu
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
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
