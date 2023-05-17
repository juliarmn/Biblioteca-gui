package julia.biblioteca.gui.controllers;

import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class OpcaoController implements Initializable {
    @FXML
    private Button button_aluno;
    @FXML
    private Button button_professor;
    @FXML
    private Button button_acessor;
    @FXML
    private Button button_super;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
             button_aluno.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {

                     DBUtils.changeScene(event, "AlunoSignUp.fxml", "Cadastro");
                 }
             });
             button_professor.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                     DBUtils.changeScene(event, "ProfessorCadastro.fxml", "Cadastro");
                 }
             });
             button_acessor.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                     DBUtils.changeScene(event, "AcessorCadastro.fxml", "Cadastro");
                 }
             });
             button_super.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                     DBUtils.changeScene(event, "SuperUserAcesso.fxml", "Cadastro");
                 }
             });
    }
}
