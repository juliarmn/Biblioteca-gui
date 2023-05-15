package gui.controllers;
import classes.Biblioteca;
import classes.Item;
import excessoes.InformacoesInvalidas;
import gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EmprestimoController implements Initializable {
    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_dia;
    @FXML
    private TextField tf_mes;
    @FXML
    private TextField tf_ano;
    @FXML
    private Button button_emprestar;
    @FXML
    private Button button_sair;
    @FXML
    private TextField alerta;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_emprestar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_nome.getText().equals("") && tf_dia.getText().equals("") && tf_mes.getText().equals("") && tf_ano.getText().equals("")) {
                    alerta.setText("Preencha todos os campos");
                } else {
                    Item item = biblioteca.buscarItemGui(tf_nome.getText());
                    if (item == null) {
                        alerta.setText("Preencha não existe");
                    } else {
                        if (item.getQuantidadeDisponivel() != 0) {
                            try {
                                biblioteca.adicionarEmprestimo(item, tf_dia.getText(), tf_mes.getText(), tf_ano.getText());
                            } catch (InformacoesInvalidas e) {
                                alerta.setText(e.getMessage());
                            }
                        }
                    }
                }
            }
        });
        button_sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (biblioteca.getSuperLogado() != null) {
                    DBUtils.changeScene(event, "MenuSuper.fxml", "MenuSuper");
                } else {
                    DBUtils.changeScene(event, "MenuUser.fxml", "Menu");
                }
            }
        });
    }
}
