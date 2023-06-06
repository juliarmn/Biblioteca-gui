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

/**
 * Devolve o item, um controller para devolver o item por meio dos TextFields e botões
 * Implementa a interface Initializable
 */
public class DevolverItemController implements Initializable {
    /**
     * TextField nome do item
     */
    @FXML
    private TextField tf_nome;
    /**
     * TextField que pega o valor do dia
     */
    @FXML
    private TextField tf_dia;
    /**
     * TextField que pega o valor do mês
     */
    @FXML
    private TextField tf_mes;
    /**
     * TextField que pega o valor do ano
     */
    @FXML
    private TextField tf_ano;
    /**
     * Button para devolver o item
     */
    @FXML
    private Button button_devolver;
    /**
     * Button para sair da tela e retornar para o menu
     */
    @FXML
    private Button button_sair;
    /**
     * Lança uma mensagem de alerta na tela se houver algum problema
     */
    @FXML
    private Label alerta;

    /**
     * Se clicar em devolver verifica se o que está sendo passado está vazio
     * Apaga o texto por questão estética ao clicar em devolver, verifica a data também
     * Chama o método para calcular a multa
     * Botão sair que volta ao menu
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_devolver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_nome.getText().equals("") || tf_dia.getText().equals("") || tf_mes.getText().equals("") || tf_ano.getText().equals("")) {
                    alerta.setText("Preencha todos os campos");
                } else {
                    try {
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
                    } catch(Exception e) {
                        alerta.setText("erro ao ler a data");
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
