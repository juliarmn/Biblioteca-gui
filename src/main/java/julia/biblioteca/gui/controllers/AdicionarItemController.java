package julia.biblioteca.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.itens.CD;
import julia.biblioteca.classes.itens.Livro;
import julia.biblioteca.classes.itens.Revista;
import julia.biblioteca.gui.DBUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller do FXML de adicionar item
 * Implementa Initializable (interface)
 */
public class AdicionarItemController implements Initializable {
    /**
     * TextField para pegar a informação de título
     */
    @FXML
    private TextField tf_titulo;
    /**
     * TextField para pegar a informação de autor
     */
    @FXML
    private TextField tf_autor;
    /**
     * TextField para pegar a quantidade de itens
     */
    @FXML
    private TextField tf_qtd;
    /**
     * TextField para pegar o ano de publicação
     */
    @FXML
    private TextField tf_ano;
    /**
     * TextField do valor extra 1 (cada item tem um tipo específico)
     */
    @FXML
    private TextField tf_extra1;
    /**
     * TextField do valor extra 2 (cada item tem um tipo específico)
     */
    @FXML
    private TextField tf_extra2;
    /**
     * TextField do tipo de item
     */
    @FXML
    private TextField tf_tipo;
    /**
     * Botão para sair (tipo Button)
     */
    @FXML
    private Button button_sair;
    /**
     * Botão para adicionar o item (tipo Button)
     */
    @FXML
    private Button button_adicionar;
    /**
     * Label para mandar uma mensagem de alerta
     */
    @FXML
    private Label alerta;

    /**
     * Sobrescreve um método da interface Initializable
     * Define que quando apertar no botão adicionar, adiciona o item -> pega os dados dos text fields
     * Quando apertar em sair, ele volta para o menu
     * @param url url
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_adicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_tipo.getText().equals("") || tf_titulo.getText().equals("") || tf_ano.getText().equals("") ||
                        tf_qtd.getText().equals("") || tf_autor.getText().equals("") || tf_extra1.getText().equals("")
                        || tf_extra2.getText().equals("")) {
                    alerta.setText("Campos vazios");
                } else {
                    if (tf_tipo.getText().toLowerCase().equals("livro") ) {
                        biblioteca.adicionarItem(new Livro(tf_titulo.getText(), tf_autor.getText(), Integer.parseInt(tf_ano.getText()),
                                Integer.parseInt(tf_qtd.getText()), tf_extra1.getText(), tf_extra2.getText()));
                    } else if (tf_tipo.getText().toLowerCase().equals("revista")) {
                        biblioteca.adicionarItem(new Revista(tf_titulo.getText(), tf_autor.getText(), Integer.parseInt(tf_ano.getText()),
                                Integer.parseInt(tf_qtd.getText()), Integer.parseInt(tf_extra1.getText()),  Integer.parseInt(tf_extra2.getText())));
                    } else if (tf_tipo.getText().toLowerCase().equals("cd")) {
                        biblioteca.adicionarItem(new CD(tf_titulo.getText(), tf_autor.getText(), Integer.parseInt(tf_ano.getText()),
                                Integer.parseInt(tf_qtd.getText()), Integer.parseInt(tf_extra1.getText()), tf_extra1.getText()));
                    } else {
                        // Mensagem de erro
                        alerta.setText("Tipo inválido");
                    }
                    //Limpar o texto
                    tf_titulo.setText("");
                    tf_tipo.setText("");
                    tf_extra1.setText("");
                    tf_ano.setText("");
                    tf_extra2.setText("");
                    tf_qtd.setText("");
                    tf_autor.setText("");
                }

            }
        });
        button_sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    DBUtils.changeScene(event, "MenuSuper.fxml", "MenuSuper");
            }
        });
    }
}
