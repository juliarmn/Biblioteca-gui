package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.excessoes.InformacoesInvalidas;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller para realizar acesso ao cadastro do super usuário (por segurança)
 */
public class SuperUserAcessoController implements Initializable {
    /**
     * TextField chave
     */
    @FXML
    private TextField tf_chave;
    /**
     * TextField senha
     */
    @FXML
    private PasswordField tf_senha;
    /**
     * Button para entrar e poder cadastrar
     */
    @FXML
    private Button button_entrar;
    /**
     * Button para sair
     */
    @FXML
    private Button button_sair;
    /**
     * alarme se houver erro
     */
    @FXML
    private Label alarme;

    /**
     * Verifica se tem campo vazio
     * Caso tenha, coloca um alerta
     * Caso não tenha, e consiga entrar, vai para o cadastro
     * Tem uma opção para sair
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_entrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_senha.getText().equals("") || tf_senha.getText().equals("")) {
                    alarme.setText("Campos vazios");
                } else {
                    try {
                        biblioteca.SuperUserAcessoCadastro(Integer.parseInt(tf_chave.getText()), tf_senha.getText());
                        DBUtils.changeScene(event, "CadastroSuperUsuario.fxml", "Cadastro");
                    } catch (InformacoesInvalidas e) {
                        alarme.setText(e.getMessage());
                    }
                }
            }
        });
        button_sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Login");
            }
        });
    }

}
