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
 * Histórico Controller para mostrar os itens que já foram devolvidos
 * Implementa a interface Initializable
 */
public class HistoricoController implements Initializable {
    /**
     * Label de item -> substituir por esse dado entrado pelo usuário
     */
    @FXML
    private Label tf_item;
    /**
     * Label para título -> substituir por esse dado entrado pelo usuário
     */
    @FXML
    private Label tf_titulo;
    /**
     * Button para sair
     */
    @FXML
    private Button button_sair;
    /**
     * Label para ID -> substituir por esse dado entrado pelo usuário
     */
    @FXML
    private Label tf_id;
    /**
     * Label para autor -> substituir por esse dado entrado pelo usuário
     */
    @FXML
    private Label tf_autor;
    /**
     * Label para data emprestada -> substituir por esse dado entrado pelo usuário
     */
    @FXML
    private Label data_emp;
    /**
     * Label para data real de devolução -> substituir por esse dado entrado pelo usuário
     */
    @FXML
    private Label data_real;
    /**
     * Button botão parea ver o próximo item
     */
    @FXML
    private Button button_prox;
    /**
     * inteiro para contar e passar para o próximo item -> tem que ser static
     */
    public static int i;

    /**
     * Sobrescreve o método initialize da interface Initializable
     * No botão próximo, conta enquanto está devolvido e mostra o empréstimo, no caso se não estiver devolvido só conta o i sem mostrar na tela
     * Botão sair volta ao menu -> como é um método de ambos os tipos de usuários, verifica qual é o tipo para voltar ao menu correto
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
            public void handle(ActionEvent actionEvent) {
                i ++;
                if (i < biblioteca.getContaLogada().getEmprestimo().size() && contaLogada.getEmprestimo().get(i).isDevolvido())
                    setEmprestimo(biblioteca.getContaLogada().getEmprestimo().get(i));
                else {
                    while (i  < contaLogada.getEmprestimo().size() && !contaLogada.getEmprestimo().get(i).isDevolvido()) {
                        i++;
                    }
                    if (i < biblioteca.getContaLogada().getEmprestimo().size() && contaLogada.getEmprestimo().get(i).isDevolvido())
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

    /**
     * Se não for devolvido só conta o i
     * Se for devolvido, envia o empréstimo para printar as informações do empréstimo
     * @param contaLogada Usuario
     */
    private void iniciar(Usuario contaLogada) {
        i = 0;
        while (i < contaLogada.getEmprestimo().size() && !contaLogada.getEmprestimo().get(i).isDevolvido()) {
            i++;
        }
        if (i < contaLogada.getEmprestimo().size() && contaLogada.getEmprestimo().get(i).isDevolvido())
            setEmprestimo(contaLogada.getEmprestimo().get(i));
    }

    /**
     * Imprime os dados do empréstimo que já foi devolvido.
     * @param emprestimo Emprestimo
     */
    private void setEmprestimo(Emprestimo emprestimo) {
        tf_item.setText(emprestimo.getItem().toString());
        tf_id.setText(String.valueOf(emprestimo.getItem().getId()));
        tf_titulo.setText(emprestimo.getItem().getTitulo());
        tf_autor.setText(emprestimo.getItem().getAutor());

        data_emp.setText(Validacoes.printarData(emprestimo.getDataEmprestimo()));
        data_real.setText(Validacoes.printarData(emprestimo.getDataDevolucaoReal()));
    }
}
