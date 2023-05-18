package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.usuarios.AcessorTecnico;
import julia.biblioteca.classes.Biblioteca;
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
 * Classe para cadastrar um Acessor técnico, conectando com FXML
 * Initializable é implementado
 */
public class CadastroAcessorTecnicoController implements Initializable {
    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_matricula;
    @FXML
    private TextField tf_cpf;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_secao;

    @FXML
    private Button button_cadastro;
    @FXML
    private Label alarme;

    /**
     * Sobrescreve o método da interface
     * Quando aperta em cadastro, cadastra o usuário -> chama um método de biblioteca para adicionar em uma lista de usuário
     * Quando aperta em sair vai para a página de login novamente.
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            button_cadastro.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                    if (tf_nome.getText().equals("") || tf_matricula.getText().equals("") || tf_cpf.getText().equals("") ||
                            tf_password.getText().equals("") || tf_secao.getText().equals("")) {
                        // Alerta na tela de que o usuário tentou cadastrar sem nenhuma informação ou faltando alguma
                        alarme.setText("Campos vazios");
                    } else {
                        try {
                            // Tenta adicionar usuário
                            biblioteca.adicionarUser(new AcessorTecnico(tf_nome.getText(), Integer.parseInt(tf_matricula.getText()),
                                    tf_cpf.getText(), tf_password.getText(), tf_secao.getText()));
                        } catch (Exception e) {
                            alarme.setText(e.getMessage());
                        }
                        DBUtils.changeScene(event, "sample.fxml", "Login");
                    }
                }
            });

    }
}
