package gui.controllers;

import classes.Aluno;
import classes.Biblioteca;
import gui.DBUtils;
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

public class CadastroAlunoController implements Initializable {
    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_matricula;
    @FXML
    private TextField tf_cpf;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_curso;
    @FXML
    private TextField tf_periodo;

    @FXML
    private Button button_cadastro;
    @FXML
    private Label alarme;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            button_cadastro.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                    if (tf_nome.getText().equals("") || tf_matricula.getText().equals("") || tf_cpf.getText().equals("") ||
                            tf_password.getText().equals("") || tf_curso.getText().equals("") || tf_periodo.getText().equals("")) {
                        alarme.setText("Todos os campos devem ser preenchidos");
                    } else {
                        try {
                            biblioteca.adicionarUser(new Aluno(tf_nome.getText(), Integer.parseInt(tf_matricula.getText()),
                                    tf_cpf.getText(), tf_password.getText(), tf_curso.getText(), tf_periodo.getText()));
                        } catch (Exception e) {
                            alarme.setText(e.getMessage());
                        }
                        DBUtils.changeScene(event, "sample.fxml", "Login");
                    }
                }
            });

    }
}
