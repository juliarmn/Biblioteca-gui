package julia.biblioteca.gui.controllers;

import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.itens.Item;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller para pesquisar itens
 */
public class PesquisarItemController implements Initializable {
    /**
     * Label tf_item-> substituído pelo dado presente no item da biblioteca
     */
    @FXML
    private Label tf_item;
    /**
     * Label título-> substituído pelo dado presente no item da biblioteca
     */
    @FXML
    private Label tf_titulo;
    /**
     * Button para sair
     */
    @FXML
    private Button button_sair;
    /**
     * Label alerta -> mensagem de erro
     */
    @FXML
    private Label alerta;
    /**
     * Label ID-> substituído pelo dado presente no item da biblioteca
     */
    @FXML
    private Label tf_id;
    /**
     * Label autor-> substituído pelo dado presente no item da biblioteca
     */
    @FXML
    private Label tf_autor;
    /**
     * TextField para dado inserido pelo usuário
     */
    @FXML
    private TextField tf_titulo_ou_id;
    /**
     * Label emprestado-> substituído pelo dado presente no item da biblioteca
     */
    @FXML
    private Label tf_emprestada;
    /**
     * Label disponível-> substituído pelo dado presente no item da biblioteca
     */
    @FXML
    private Label tf_disponivel;
    /**
     * Button para realizar a procura
     */
    @FXML
    private Button button_procurar;
    /**
     * Label ano publicação-> substituído pelo dado presente no item da biblioteca
     */
    @FXML
    private Label tf_anoPublicacao;
    /**
     * Label emprestado
     */
    @FXML
    private Label label_emp;
    /**
     * Label disponível
     */
    @FXML
    private Label label_disp;

    /**
     * Primeiro mantém os labels escondidos
     * Com o botão procurar, se achar mostra os dados do item
     * Se clicar em sair volta ao menu (verifica o tipo de usuário)
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.alerta.setText("");
        tf_item.setText("");
        tf_titulo.setText("");
        tf_id.setText("");
        tf_autor.setText("");
        tf_disponivel.setText("");
        tf_emprestada.setText("");
        tf_anoPublicacao.setText("");
        label_disp.setText("");
        label_emp.setText("");
        button_procurar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (tf_titulo_ou_id.getText().equals("") || tf_titulo_ou_id.getText() == null) {
                    setAlerta("Digite alguma informação");
                } else {
                    Item item = biblioteca.buscarItem(tf_titulo_ou_id.getText());
                    if (item != null) {
                        setItem(item);
                    } else {
                        setAlerta("Item não encontrado");
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

    /**
     * Coloca o alerta na tela
     * Fiz separado para depois poder apagar
     * @param alerta
     */
    private void setAlerta(String alerta) {
        this.alerta.setText(alerta);
        tf_item.setText("");
        tf_titulo.setText("");
        tf_id.setText("");
        tf_autor.setText("");
        tf_disponivel.setText("");
        tf_emprestada.setText("");
        tf_anoPublicacao.setText("");
        label_disp.setText("");
        label_emp.setText("");
    }

    /**
     * Coloca os dados de item na tela
     * @param item Item
     */
    private void setItem(Item item) {
        this.alerta.setText("");
        tf_item.setText(item.toString());
        tf_titulo.setText(item.getTitulo());
        tf_id.setText(String.valueOf(item.getId()));
        tf_autor.setText(item.getAutor());
        tf_disponivel.setText(String.valueOf(item.getQuantidadeDisponivel()));
        tf_emprestada.setText(String.valueOf(item.getQuantidadeEmprestada()));
        tf_anoPublicacao.setText(String.valueOf(item.getAnoPublicacao()));
        label_emp.setText("Emprestado: ");
        label_disp.setText("Disponível: ");
    }
}
