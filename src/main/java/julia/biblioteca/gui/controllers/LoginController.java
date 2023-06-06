package julia.biblioteca.gui.controllers;

import javafx.scene.control.Label;
import julia.biblioteca.classes.DisplayBiblioteca;
import julia.biblioteca.excessoes.InformacoesInvalidas;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller do login que implementa a interface Initializable
 */
public class LoginController implements Initializable {
    /**
     * Nome do usuário -> TextField
     */
    @FXML
    private TextField tf_nome;
    /**
     * CPF do usuário -> TextField
     */
    @FXML
    private TextField tf_cpf;
    /**
     * Senha do Usuário -> TextField
     */
    @FXML
    private PasswordField tf_password;
    /**
     * Button botão para efetuar o login e entrar no menu
     */
    @FXML
    private Button button_login;
    /**
     * Button botão para ir para a área de cadastro
     */
    @FXML
    private Button button_cadastro;
    /**
     * Mensagem de erro
     */
    @FXML
    private Label erro;

    /**
     * Com botão login verifica se tem campos vazios
     * Faz login e entra em um menu
     * Case aperte o botão cadastro vai para um template de opção de cadastro
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplay();
                if(!tf_nome.getText().equals("") && !tf_cpf.getText().equals("") && !tf_password.getText().equals("")) {
                    try {
                        displayBiblioteca.loginGui(event, tf_nome.getText(), tf_cpf.getText(), tf_password.getText());
                    } catch (InformacoesInvalidas e) {
                        erro.setText(e.getMessage());
                    }

                } else {
                    erro.setText("Campos vazios");
                }
            }
        });
    button_cadastro.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            DBUtils.changeScene(event, "OpcaoSignUp.fxml", "Opção Escolha");
        }
    });
    }

}
