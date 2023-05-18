package julia.biblioteca.gui.controllers;

import javafx.scene.control.Label;
import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.Validacoes;
import julia.biblioteca.emprestimo.Emprestimo;
import julia.biblioteca.excessoes.ItemNaoEmprestado;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DevolverItemController implements Initializable {
    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_dia;
    @FXML
    private TextField tf_mes;
    @FXML
    private TextField tf_ano;
    @FXML
    private Button button_devolver;
    @FXML
    private Button button_sair;
    @FXML
    private Label alerta;
//todo: erro ao tentar devolver item que não existe ou não tem emprestado
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_devolver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_nome.getText().equals("") && tf_dia.getText().equals("") && tf_mes.getText().equals("") && tf_ano.getText().equals("")) {
                    alerta.setText("Preencha todos os campos");
                } else {
                    if (!Validacoes.validaAno(Integer.parseInt(tf_ano.getText())) || !Validacoes.validaMes(
                            Integer.parseInt(tf_mes.getText())) || !Validacoes.validaDia(Integer.parseInt(tf_dia.getText()), Integer.parseInt(tf_mes.getText()), Integer.parseInt(tf_ano.getText()))) {
                        tf_ano.setText("");
                        tf_dia.setText("");
                        tf_mes.setText("");
                        tf_nome.setText("");
                        alerta.setText("Data Inávlida");
                    } else {
                        try {
                            Emprestimo emprestimo = biblioteca.procurarEmprestimo(tf_nome.getText());
                            biblioteca.devolverGUI(emprestimo, tf_dia.getText(), tf_mes.getText(), tf_ano.getText());
                            tf_ano.setText("");
                            tf_dia.setText("");
                            tf_mes.setText("");
                            tf_nome.setText("");
                            alerta.setText("Devolvido");
                        } catch (ItemNaoEmprestado e) {
                            alerta.setText(e.getMessage());
                        }
                    }
                }
            }
        });
        button_sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "MenuUser.fxml", "Menu");
            }
        });
    }
}
