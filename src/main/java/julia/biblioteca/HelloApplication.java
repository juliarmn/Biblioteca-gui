package julia.biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Método Main para aplicar os arquivos FXML
 */
public class HelloApplication extends Application {
    /**
     * Inicializar o arquivo da biblioteca
     * @param stage (Stage)
     * @throws IOException excessão lançada caso dẽ erradoa leitura de arquivo.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setTitle("Biblioteca da Juloia");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * método main
     * @param args (String)
     */
    public static void main(String[] args) {
        launch();
    }
}