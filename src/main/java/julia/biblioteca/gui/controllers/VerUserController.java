package julia.biblioteca.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.itens.CD;
import julia.biblioteca.classes.itens.Item;
import julia.biblioteca.classes.itens.Livro;
import julia.biblioteca.classes.itens.Revista;
import julia.biblioteca.classes.usuarios.SuperUsuario;
import julia.biblioteca.classes.usuarios.Usuario;
import julia.biblioteca.emprestimo.Emprestimo;
import julia.biblioteca.gui.DBUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VerUserController implements Initializable {
    @FXML
    private Label label_tipo;
    @FXML
    private Label label_nome;
    @FXML
    private Label label_matricula;
    @FXML
    private Label label_emprestimos;
    @FXML
    private Button button_prox;
    @FXML
    private Button button_sair;


    public static int i;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
        i = 0;
        if (i <= biblioteca.getUsuarios().size()) {
            setItem(biblioteca.getUsuarios().get(i));
        }
        button_prox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                i ++;
                if (i < biblioteca.getUsuarios().size()) {
                    setItem(biblioteca.getUsuarios().get(i));
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
    private void setItem(Usuario user) {
        label_tipo.setText(user.toString());
        label_nome.setText(user.getNome());
        label_matricula.setText(String.valueOf(user.getMatricula()));
        label_emprestimos.setText(contaEmprestimos(user));
    }

    private String contaEmprestimos (Usuario user) {
        int count = 0;
        for (Emprestimo e : user.getEmprestimo()) {
            if (!e.isDevolvido()) {
                count ++;
            }
        }
        return String.valueOf(count);
    }
}
