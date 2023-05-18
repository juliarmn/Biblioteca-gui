package julia.biblioteca.gui;

import julia.biblioteca.HelloApplication;
import julia.biblioteca.classes.usuarios.AcessorTecnico;
import julia.biblioteca.classes.usuarios.Aluno;
import julia.biblioteca.classes.usuarios.Professor;
import julia.biblioteca.classes.DisplayBiblioteca;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DBUtils {
    private static DisplayBiblioteca display;

    public static DisplayBiblioteca getDisplay() {
        return display;
    }

    static {
        try {
            display = new DisplayBiblioteca();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title) {

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxmlFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void cadastrarAluno(ActionEvent event, String nome, int matricula, String cpf, String senha, String curso, String periodo) {
        display.getBiblioteca().adicionarUsuario(new Aluno(nome, matricula, cpf, senha, curso, periodo));
        DBUtils.changeScene(event, "sample.fxml", "Tela Login");
    }

    public static void cadastrarProfessor(ActionEvent event, String nome, int matricula, String cpf, String senha, String departamento, String titulacao) {
        display.getBiblioteca().adicionarUsuario(new Professor(nome, matricula, cpf, senha, departamento, titulacao));
        DBUtils.changeScene(event, "sample.fxml", "Tela Login");

    }

    public static void cadastrarAcessor(ActionEvent event, String nome, int matricula, String cpf, String senha, String secao) {
        display.getBiblioteca().adicionarUsuario(new AcessorTecnico(nome, matricula, cpf, senha, secao));
        DBUtils.changeScene(event, "sample.fxml", "Tela Login");
    }
}
