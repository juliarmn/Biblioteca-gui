package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.*;
import julia.biblioteca.classes.usuarios.Usuario;
import julia.biblioteca.emprestimo.Emprestimo;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller para ver os itens emprestados
 */
public class VerItensController implements Initializable {
    /**
     * Label item -> substitui pelo dado do item da biblioteca
     */
    @FXML
    private Label tf_item;
    /**
     * Label título -> substitui pelo dado do item da biblioteca
     */
    @FXML
    private Label tf_titulo;
    /**
     * Button sair
     */
    @FXML
    private Button button_sair;
    /**
     * Label ID -> substitui pelo dado do item da biblioteca
     */
    @FXML
    private Label tf_id;
    /**
     * Label autor -> substitui pelo dado do item da biblioteca
     */
    @FXML
    private Label tf_autor;
    /**
     * Label data de empréstimos -> substitui pelo dado do item da biblioteca
     */
    @FXML
    private Label data_emp;
    /**
     * Label data de devolução -> substitui pelo dado do item da biblioteca
     */
    @FXML
    private Label data_dev;
    /**
     * Button próximo para ver o próximo item
     */
    @FXML
    private Button button_prox;
    /**
     * int i para contar a lista de itens em empréstimo
     */
    public static int i;

    /**
     * Se clicar sair retorna ao menu
     * Se clicar em próximo faz uma verificação e contagem do índice
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
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

    /**
     * Verifica o item na lista de contas logadas, se cumprir os requisitos de estar emprestado, chama um método para ver os itens
     * @param contaLogada
     */
    private void iniciar(Usuario contaLogada) {
        i = 0;
        while (i < contaLogada.getEmprestimo().size() && contaLogada.getEmprestimo().get(i).isDevolvido()) {
            i++;
        }
        if (i < contaLogada.getEmprestimo().size() && !contaLogada.getEmprestimo().get(i).isDevolvido())
            setEmprestimo(contaLogada.getEmprestimo().get(i));
    }

    /**
     * MOstra os dados do empréstimo
     * @param emprestimo Emprestimo
     */
    private void setEmprestimo(Emprestimo emprestimo) {
        tf_item.setText(emprestimo.getItem().toString());
        tf_id.setText(String.valueOf(emprestimo.getItem().getId()));
        tf_titulo.setText(emprestimo.getItem().getTitulo());
        tf_autor.setText(emprestimo.getItem().getAutor());
        data_emp.setText(Validacoes.printarData(emprestimo.getDataEmprestimo()));
        data_dev.setText(Validacoes.printarData(emprestimo.getDataDevolucaoPrevista()));
    }
}
