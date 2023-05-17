package julia.biblioteca.gui.controllers;


import julia.biblioteca.classes.Biblioteca;
import julia.biblioteca.classes.DisplayBiblioteca;
import julia.biblioteca.classes.Emprestimo;
import julia.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

public class MenuUserController {

    @FXML
    private Button button_op1;

    @FXML
    private Button button_op2;

    @FXML
    private Button button_op3;

    @FXML
    private Button button_op4;

    @FXML
    private Button button_op5;

    @FXML
    private Button button_op6;

    @FXML
    private Button button_op7;

    @FXML
    private Button button_op8;


    @FXML
    private Button button_deslogin;

    @FXML
    private Label alerta;

    @FXML
    public void initialize() {

        button_op1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                boolean temEmprestimo = false;
                for (Emprestimo emprestimo : biblioteca.getContaLogada().getEmprestimo()) {
                    if (!emprestimo.isDevolvido()) {
                        temEmprestimo = true;
                        break;
                    }
                }
                if (temEmprestimo) {
                    DBUtils.changeScene(actionEvent, "verEmprestimos.fxml", "Emprestimos");
                } else {
                    alerta.setText("Sem empréstimos");
                }
            }
        });
        button_op2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                boolean temEmprestimo = false;
                for (Emprestimo emprestimo : biblioteca.getContaLogada().getEmprestimo()) {
                    if (!emprestimo.isDevolvido()) {
                        temEmprestimo = true;
                        break;
                    }
                }
                if (temEmprestimo) {
                    DBUtils.changeScene(actionEvent, "Renovar.fxml", "Renovar");
                } else {
                    alerta.setText("Sem empréstimos");
                }
            }

        });
        button_op3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                boolean temEmprestimo = false;
                for (Emprestimo emprestimo : biblioteca.getContaLogada().getEmprestimo()) {
                    if (!emprestimo.isDevolvido()) {
                        temEmprestimo = true;
                        break;
                    }
                }
                if (temEmprestimo) {
                    DBUtils.changeScene(actionEvent, "DevolverItem.fxml", "Devolucoes");
                } else {
                    alerta.setText("Sem empréstimos");
                }


            }
        });
        button_op4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "EmprestarItem.fxml", "Emprestimos");
            }
        });
        button_op5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                if (biblioteca.getContaLogada().getMulta() != 0) {
                    DBUtils.changeScene(actionEvent, "PagarMulta.fxml", "Pagamento");
                } else {
                    alerta.setText("Sem multas");
                }
            }
        });
        button_op6.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "verItensBiblioteca.fxml", "Ver itens");

            }
        });
        button_op7.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "PesquisarItem.fxml", "Busca");
            }
        });
        button_op8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Biblioteca biblioteca = DBUtils.getDisplay().getBiblioteca();
                boolean temDevolucao = false;
                for (Emprestimo emprestimo : biblioteca.getContaLogada().getEmprestimo()) {
                    if (emprestimo.isDevolvido()) {
                        temDevolucao = true;
                        break;
                    }
                }
                if (temDevolucao) {
                    DBUtils.changeScene(actionEvent, "verEmprestimos.fxml", "Emprestimos");
                } else {
                    alerta.setText("Sem empréstimos");
                }
            }
        });
        button_deslogin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplay();
                displayBiblioteca.deslogarGUI();
                DBUtils.changeScene(actionEvent, "sample.fxml", "Tela Login");
            }
        });
    }



}
