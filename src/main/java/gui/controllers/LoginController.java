package gui.controllers;

import classes.DisplayBiblioteca;
import gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField tf_nome;

    @FXML
    private TextField tf_cpf;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Button button_login;

    @FXML
    private Button button_cadastro;
    @FXML
    private TextField erro;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplay();
                if(tf_nome.getText() != "" && tf_cpf.getText() != "" && tf_password.getText() != "") {
                    try {
                        displayBiblioteca.loginGui(event, tf_nome.getText(), tf_cpf.getText(), tf_password.getText());
                    } catch (Exception e) {
                        erro.setText(e.getMessage());
                    }

                } else {
                    erro.setText("Precisa preencher todos os campos!");
                }
            }
        });

    }

}
