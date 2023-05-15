package gui.controllers;

import classes.Biblioteca;
import excessoes.InformacoesInvalidas;
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

public class SuperUserAcessoController implements Initializable {
    @FXML
    private TextField tf_chave;
    @FXML
    private PasswordField tf_senha;
    @FXML
    private Button button_entrar;
    @FXML
    private Label alarme;

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
    }

}
