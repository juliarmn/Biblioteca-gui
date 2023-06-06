package julia.biblioteca.gui.controllers;

import javafx.scene.control.Label;
import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.Validacoes;
import julia.biblioteca.classes.itens.Item;
import julia.biblioteca.excessoes.InformacoesInvalidas;
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
 * Controller para fazer o empréstimo
 * implementa a interface Initializable
 */
public class EmprestimoController implements Initializable {
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
     * Button para emprestar o item
     */
    @FXML
    private Button button_emprestar;
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
     * Se tiver algum erro, ou campos vazios, manda uma mensagem de erro
     * Caso não haja erro, se clicar em emprestar e não houver erro chama um método de biblioteca para emprestar
     * Apaga o texto por questões visuais
     * No botão de sair, retorna ao menu
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_emprestar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_nome.getText().equals("") || tf_dia.getText().equals("") || tf_mes.getText().equals("") || tf_ano.getText().equals("")) {
                    alerta.setText("Preencha todos os campos");
                } else {
                    Item item = biblioteca.buscarItemGui(tf_nome.getText());
                    if (item == null) {
                        alerta.setText("Não existe");
                    } else {
                        if (item.getQuantidadeDisponivel() == 0) {
                            alerta.setText("Indisponível");
                        }
                        if (biblioteca.getContaLogada().getMulta() != 0) {
                            alerta.setText("Multas pendentes, erro.");
                        }
                        if (item.getQuantidadeDisponivel() != 0 && biblioteca.getContaLogada().getMulta() == 0) {
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
                                        biblioteca.adicionarEmprestimo(item, tf_dia.getText(), tf_mes.getText(), tf_ano.getText());
                                        alerta.setText("Emprestado");
                                        tf_ano.setText("");
                                        tf_dia.setText("");
                                        tf_mes.setText("");
                                        tf_nome.setText("");
                                    } catch (InformacoesInvalidas e) {
                                        alerta.setText(e.getMessage());
                                    }

                                }
                            } catch (Exception e) {
                                alerta.setText("Erro ao ler a data");
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
