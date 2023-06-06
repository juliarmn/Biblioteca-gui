package julia.biblioteca.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.usuarios.Usuario;
import julia.biblioteca.emprestimo.Emprestimo;
import julia.biblioteca.gui.DBUtils;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Verifica os usuários presentes na biblioteca
 */
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

    /**
     * Se clicar próximo vai para o próximo item
     * Se clicar sair vai para o menu do usuário logado (Super ou comum)
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
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

    /**
     * Mostra os dados do item
     * @param user Usuario
     */
    private void setItem(Usuario user) {
        label_tipo.setText(user.toString());
        label_nome.setText(user.getNome());
        label_matricula.setText(String.valueOf(user.getMatricula()));
        label_emprestimos.setText(contaEmprestimos(user));
    }

    /**
     * Conta quantos empréstimos o usuário tem
     * @param user Usuario
     * @return valor do contador
     */
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
