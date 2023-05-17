package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.Professor;
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

public class CadastroProfessorController implements Initializable {
    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_matricula;
    @FXML
    private TextField tf_cpf;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_dep;
    @FXML
    private TextField tf_titulo;

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

