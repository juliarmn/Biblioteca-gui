package gui.controllers;

import classes.Biblioteca;
import classes.DisplayBiblioteca;
import classes.Item;
import classes.Livro;
import classes.Revista;
import classes.CD;
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

public class MostrarItensController implements Initializable {

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
    private Label tf_anoPublicacao;

    @FXML
    private Label tf_extra1;

    @FXML
    private Label tf_extra2;

    @FXML
    private Button button_prox;
    public static int i;

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
        tf_titulo.setText(item.getTitulo());
        tf_autor.setText(item.getAutor());
        tf_anoPublicacao.setText(String.valueOf(item.getAnoPublicacao()));
        if(item instanceof Livro) {
            tf_extra1.setText(((Livro) item).getEditora());
            tf_extra1.setText(((Livro) item).getIsbn());
        } else if (item instanceof Revista) {
            tf_extra1.setText(String.valueOf(((Revista) item).getVolume()));
            tf_extra1.setText(String.valueOf(((Revista) item).getNumero()));
        } else {
            tf_extra1.setText(String.valueOf(((CD) item).getVolume()));
            tf_extra1.setText(((CD) item).getGravadora());
        }
    }
}
