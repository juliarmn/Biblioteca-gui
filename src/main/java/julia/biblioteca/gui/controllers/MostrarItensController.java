package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.itens.Item;
import julia.biblioteca.classes.itens.Livro;
import julia.biblioteca.classes.itens.Revista;
import julia.biblioteca.classes.itens.CD;
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
 * Controllers para mostrar os itens
 * Implementa Initializable
 */
public class MostrarItensController implements Initializable {
    /**
     * Label do tipo do item -> substituido pelo dado oriundo dos itens da biblioteca
     */
    @FXML
    private Label tf_item;
    /**
     * Label do título -> substituido pelo dado oriundo dos itens da biblioteca
     */
    @FXML
    private Label tf_titulo;
    /**
     * Button botão para voltar para o menu
     */
    @FXML
    private Button button_sair;
    /**
     * Label do ID -> substituido pelo dado oriundo dos itens da biblioteca
     */
    @FXML
    private Label tf_id;
    /**
     * Label do autor -> substituido pelo dado oriundo dos itens da biblioteca
     */
    @FXML
    private Label tf_autor;
    /**
     * Label do ano de publicação -> substituido pelo dado oriundo dos itens da biblioteca
     */
    @FXML
    private Label tf_anoPublicacao;
    /**
     * Label do extra 1 -> substituido pelo dado oriundo dos itens da biblioteca
     */
    @FXML
    private Label tf_extra1;
    /**
     * Label do extra 2 -> substituido pelo dado oriundo dos itens da biblioteca
     */
    @FXML
    private Label tf_extra2;
    /**
     * Button botão para ir para o próximo
     */
    @FXML
    private Button button_prox;
    /**
     * Variável estática para contar os itens a serem listados
     */
    public static int i;

    /**
     * Implementa o Initializable
     * Com o botão próximo ele conta os itens da biblioteca e chama um método para mostrar os dados deles
     * No botão sair retorna para o menu
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
        i = 0;
        if (i <= biblioteca.getItens().size()) {
            setItem(biblioteca.getItens().get(i));
        }
        button_prox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                i ++;
                if (i <= biblioteca.getItens().size()) {
                    setItem(biblioteca.getItens().get(i));
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
    private void setItem(Item item) {
        tf_item.setText(item.toString());
        tf_id.setText(String.valueOf(item.getId()));
        tf_titulo.setText(item.getTitulo());
        tf_autor.setText(item.getAutor());
        tf_anoPublicacao.setText(String.valueOf(item.getAnoPublicacao()));
        if(item instanceof Livro) {
            tf_extra1.setText(((Livro) item).getEditora());
            tf_extra2.setText(((Livro) item).getIsbn());
        } else if (item instanceof Revista) {
            tf_extra1.setText(String.valueOf(((Revista) item).getVolume()));
            tf_extra2.setText(String.valueOf(((Revista) item).getNumero()));
        } else {
            tf_extra1.setText(String.valueOf(((CD) item).getVolume()));
            tf_extra2.setText(((CD) item).getGravadora());
        }
    }
}
