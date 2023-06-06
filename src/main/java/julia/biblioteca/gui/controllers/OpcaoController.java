package julia.biblioteca.gui.controllers;

import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Mostra as opções de cadastro e implementa a interface Initializable
 */
public class OpcaoController implements Initializable {
    /**
     * Button para escolher aluno
     */
    @FXML
    private Button button_aluno;
    /**
     * Button para escolher professor
     */
    @FXML
    private Button button_professor;
    /**
     * Button para escolher Acessor Técnico
     */
    @FXML
    private Button button_acessor;
    /**
     * Button para escolher SUper usuário
     */
    @FXML
    private Button button_super;

    /**
     * Em aluno manda para a página de cadastro de aluno
     * Em professor manda para a página de cadastro de professor
     * Em acessor manda para a página de cadastro de professor
     * Em super usuário manda para a página de verificação para poder cadastrar super
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
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
