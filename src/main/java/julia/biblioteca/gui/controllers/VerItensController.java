package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.*;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        Usuario contaLogada = biblioteca.getContaLogada();
        iniciar(contaLogada);
        button_prox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                i ++;
                if (i < biblioteca.getContaLogada().getEmprestimo().size() && !contaLogada.getEmprestimo().get(i).isDevolvido())
                    setEmprestimo(biblioteca.getContaLogada().getEmprestimo().get(i));
                else {
                    while (i < contaLogada.getEmprestimo().size() && contaLogada.getEmprestimo().get(i).isDevolvido()) {
                        i++;
                    }
                    if (i < biblioteca.getContaLogada().getEmprestimo().size() && !contaLogada.getEmprestimo().get(i).isDevolvido())
                        setEmprestimo(biblioteca.getContaLogada().getEmprestimo().get(i));
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

    private void iniciar(Usuario contaLogada) {
        i = 0;
        while (i < contaLogada.getEmprestimo().size() && contaLogada.getEmprestimo().get(i).isDevolvido()) {
            i++;
        }
        if (i < contaLogada.getEmprestimo().size() && !contaLogada.getEmprestimo().get(i).isDevolvido())
            setEmprestimo(contaLogada.getEmprestimo().get(i));
    }


    private void setEmprestimo(Emprestimo emprestimo) {
        tf_item.setText(emprestimo.getItem().toString());
        tf_id.setText(String.valueOf(emprestimo.getItem().getId()));
        tf_titulo.setText(emprestimo.getItem().getTitulo());
        tf_autor.setText(emprestimo.getItem().getAutor());
        data_emp.setText(String.valueOf(emprestimo.getDataEmprestimo()));
        data_dev.setText(String.valueOf(emprestimo.getDataDevolucaoPrevista()));
    }
}
