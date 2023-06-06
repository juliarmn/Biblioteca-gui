package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.usuarios.SuperUsuario;
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
 * Cadastro do super usuário -> Controller para o cadastro do super usuário
 * Implementa a interface Initializable
 */
public class CadastroSuperController implements Initializable {
    /**
     * TextField para pegar o nome do usuário
     */
    @FXML
    private TextField tf_nome;
    /**
     * TextField para pegar a matrícula do usuário
     */
    @FXML
    private TextField tf_matricula;
    /**
     * TextField para pegar o CPF do usuário
     */
    @FXML
    private TextField tf_cpf;
    /**
     * PassWordField para pegar a senha do usuário
     */
    @FXML
    private PasswordField tf_password;
    /**
     * TextField para pegar o código do usuário
     */
    @FXML
    private TextField tf_cod;
    /**
     * Botão do tipo Button que cadastra o usuário
     */
    @FXML
    private Button button_cadastro;
    /**
     * Alarme para colocar mensagem de erro
     */
    @FXML
    private Label alarme;

    /**
     * Método que sobrescreve o método da Interface
     * Se não estiver nada em nenhum campo e clicar em cadastrar manda um alerta
     * Chama um método para cadastrar Super Usuário
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
                            tf_password.getText().equals("") || tf_cod.getText().equals("")) {
                        alarme.setText("Todos os campos devem ser preenchidos");
                    } else {
                        try {
                            biblioteca.adicionarSuper(new SuperUsuario(tf_nome.getText(), Integer.parseInt(tf_matricula.getText()),
                                    tf_cpf.getText(), tf_password.getText(), Integer.parseInt(tf_cod.getText())));
                        } catch (Exception e) {
                            alarme.setText(e.getMessage());
                        }
                        DBUtils.changeScene(event, "sample.fxml", "Login");
                    }

                }
            });
        }

}
