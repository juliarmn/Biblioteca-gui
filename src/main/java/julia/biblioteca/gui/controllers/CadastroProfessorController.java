package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.usuarios.Professor;
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
 * Controller para o FXML de cadastro de professor Initializable
 * Implementa a interface
 */
public class CadastroProfessorController implements Initializable {
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
     * TextField para pegar o departamento do professor
     */
    @FXML
    private TextField tf_dep;
    /**
     * TextField para pegar a titulação do professor
     */
    @FXML
    private TextField tf_titulo;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        button_cadastro.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_nome.getText().equals("") || tf_matricula.getText().equals("") || tf_cpf.getText().equals("") ||
                        tf_password.getText().equals("") || tf_dep.getText().equals("") || tf_titulo.getText().equals("")) {
                    alarme.setText("Campos vazios");
                } else {
                    try {
                        biblioteca.adicionarUser(new Professor(tf_nome.getText(), Integer.parseInt(tf_matricula.getText()),
                                tf_cpf.getText(), tf_password.getText(), tf_dep.getText(), tf_titulo.getText()));
                    } catch (Exception e) {
                        alarme.setText(e.getMessage());
                    }
                    DBUtils.changeScene(event, "sample.fxml", "Login");
                }
            }
        });
    }
}

