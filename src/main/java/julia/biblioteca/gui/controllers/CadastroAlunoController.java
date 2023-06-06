package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.usuarios.Aluno;
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
 * Controller para o FXML de cadastro de aluno
 * Implementa a interface Initializable
 */
public class CadastroAlunoController implements Initializable {
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
     * TextField para pegar a senha do usuário
     */
    @FXML
    private PasswordField tf_password;
    /**
     * PassWordField para pegar o curso do aluno
     */
    @FXML
    private TextField tf_curso;
    /**
     * TextField para pegar o período do aluno
     */
    @FXML
    private TextField tf_periodo;
    /**
     * Botão para cadastrar (Button)
     */
    @FXML
    private Button button_cadastro;
    /**
     * Alarme para caso haja algum erro
     */
    @FXML
    private Label alarme;

    /**
     * Mpetodo sobrescrito da interface Initializable
     * Se apertar em cadastro, adiciona o aluno
     * Logo depois retorna à tela inicial
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
                            tf_password.getText().equals("") || tf_curso.getText().equals("") || tf_periodo.getText().equals("")) {
                        // Tentou cadastrar com algum campo pelo menos faltando
                        alarme.setText("Campos vazios");
                    } else {
                        try {
                            biblioteca.adicionarUser(new Aluno(tf_nome.getText(), Integer.parseInt(tf_matricula.getText()),
                                    tf_cpf.getText(), tf_password.getText(), tf_curso.getText(), tf_periodo.getText()));
                        } catch (Exception e) {
                            // Alerta na página
                            alarme.setText(e.getMessage());
                        }
                        // Retorna a tela inicial
                        DBUtils.changeScene(event, "sample.fxml", "Login");
                    }
                }
            });

    }
}
