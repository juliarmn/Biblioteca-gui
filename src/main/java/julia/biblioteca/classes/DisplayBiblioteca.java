package julia.biblioteca.classes;
import julia.biblioteca.classes.itens.CD;
import julia.biblioteca.classes.itens.Item;
import julia.biblioteca.classes.itens.Livro;
import julia.biblioteca.classes.itens.Revista;
import julia.biblioteca.classes.usuarios.AcessorTecnico;
import julia.biblioteca.classes.usuarios.Aluno;
import julia.biblioteca.classes.usuarios.Professor;
import julia.biblioteca.classes.usuarios.SuperUsuario;
import julia.biblioteca.excessoes.InformacoesInvalidas;
import javafx.event.ActionEvent;
import julia.biblioteca.gui.DBUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe para conectar a biblioteca com seus controllers e ler os arquivos
 */
public class DisplayBiblioteca {
    private Biblioteca biblioteca;

    /**
     * Constructor
     * Lê o arquivo de biblioteca
     * @throws Exception se houver erro na leitura do arquivo
     */
    public DisplayBiblioteca() throws Exception {
        String path = "src/main/java/julia/biblioteca/classes/";
        String biblio = path + "biblioteca.txt";
        String itens = path + "itens.txt";
        File arquivo = new File(biblio);
        Scanner scan;
        try {
            scan = new Scanner(arquivo);
        } catch (FileNotFoundException ex) {
            throw new Exception("Sem arquivo de biblioteca");
        }

        while (scan.hasNextLine()) {
            //Ler a linha
            String linha = scan.nextLine();
            //Separar os elementos pelo #
            String[] campos = linha.split("#");
            //Shadowing:
            String nome = campos[0];
            String cnpj = campos[1];
            this.biblioteca = new Biblioteca(nome, cnpj);
        }
        File arquivoItem = new File(itens);
        lerArquivoItem(arquivoItem);
    }

    /**
     * Leitura do arquivo de itens
     * @param arquivoItem String do caminho do arquivo
     * @throws Exception se houver erro na leitura
     */
    public void lerArquivoItem(File arquivoItem) throws Exception {
        Scanner scan;
        try {
            scan = new Scanner(arquivoItem);
        } catch (FileNotFoundException ex) {
            throw new Exception("Sem arquivo de Itens");
        }

        while (scan.hasNextLine()) {
            //Ler a linha
            String linha = scan.nextLine();
            //Separar os elementos pelo #
            String[] campos = linha.split("#");
            //Shadowing:
            String tipo = campos[0];
            String titulo = campos[1];
            String autor = campos[2];
            int anoPublicacao = Integer.parseInt(campos[3]);
            int quantidadeDisponivel = Integer.parseInt(campos[4]);
            if (tipo.equals("Livro")) {
                String editora = campos[5];
                String isbn = campos[6];
                Livro livro = new Livro(titulo, autor, anoPublicacao, quantidadeDisponivel, editora, isbn);
                this.biblioteca.adicionarItem(livro);
            } else if (tipo.equals("Revista")) {
                int volume = Integer.parseInt(campos[5]);
                int numero = Integer.parseInt(campos[6]);
                Revista revista = new Revista(titulo, autor, anoPublicacao, quantidadeDisponivel, volume, numero);
                this.biblioteca.adicionarItem(revista);
            } else {
                int volume = Integer.parseInt(campos[5]);
                String gravadora = campos[6];
                CD cd = new CD(titulo, autor, anoPublicacao, quantidadeDisponivel, volume, gravadora);
                this.biblioteca.adicionarItem(cd);
            }
        }
        scan.close();

    }

    /**
     * Retorna o elemnto do tipo Biblioteca instanciado na classe
     * @return Biblioteca
     */
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }


    /**
     * Permite que o usuário que inserir os dados na GUI possa logar
     * @param event ActionEvent
     * @param nome String nome do usuário
     * @param cpf String CPF inserido
     * @param senha String senha inserida
     * @throws InformacoesInvalidas caso não exista usuário com essas informações
     */
    public void loginGui (ActionEvent event, String nome, String cpf, String senha) throws InformacoesInvalidas {
        biblioteca.loginGui(nome, cpf, senha);
        telaUsuarioGui(event);

    }

    /**
     * Chama a tela de menu da GUI
     * @param event ActionEvent
     */
    private void telaUsuarioGui(ActionEvent event) {
        if (biblioteca.getSuperLogado() != null) {
            DBUtils.changeScene(event, "MenuSuper.fxml", "Menu Super");
        } else if (biblioteca.getContaLogada() != null) {
            DBUtils.changeScene(event, "MenuUser.fxml", "Menu");
        }

    }

    /**
     * Desloga o usuário
     */
    public void deslogarGUI() {
        biblioteca.setContaLogada(null);
        biblioteca.setSuperLogado(null);
    }
}
