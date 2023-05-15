package gui.controllers;

import classes.*;
import gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class VerItensController implements Initializable {

    @FXML
    private Label tf_item;

    @FXML
    private Label tf_titulo;

    @FXML
    private Button button_sair;

//    @FXML
//    private ImageView imageView_logo;

    @FXML
    private Label tf_id;

    @FXML
    private Label tf_autor;


    @FXML
    private Label data_emp;

    @FXML
    private Label data_dev;

    @FXML
    private Button button_prox;
    public static int i;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
        i = 0;
        if (i <= biblioteca.getContaLogada().getEmprestimo().size()) {
            setEmprestimo(biblioteca.getContaLogada().getEmprestimo().get(i));
        }
        button_prox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                do {
                    i ++;
                } while (biblioteca.getContaLogada().getEmprestimo().get(i).isDevolvido());

                if (i <= biblioteca.getContaLogada().getEmprestimo().size()) {
                    setEmprestimo(biblioteca.getContaLogada().getEmprestimo().get(i));
                }
            }
        });

        button_sair.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (biblioteca.getSuperLogado() != null) {
                    DBUtils.changeScene(event, "MenuSuper.fxml", "MenuSuper");
                } else {
                    DBUtils.changeScene(event, "MenuUser.fxml", "Menu");
                }
            }
        });

    }
    private void setEmprestimo(Emprestimo emprestimo) {
        tf_item.setText(emprestimo.getItem().toString());
        tf_titulo.setText(emprestimo.getItem().getTitulo());
        tf_autor.setText(emprestimo.getItem().getAutor());
        data_emp.setText(String.valueOf(emprestimo.getDataEmprestimo()));
        data_dev.setText(String.valueOf(emprestimo.getDataDevolucaoPrevista()));
    }
}
