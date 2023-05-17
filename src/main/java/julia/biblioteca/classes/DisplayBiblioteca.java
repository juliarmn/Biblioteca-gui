package julia.biblioteca.classes;
import julia.biblioteca.excessoes.InformacoesInvalidas;
import javafx.event.ActionEvent;
import julia.biblioteca.gui.DBUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DisplayBiblioteca {
    private Biblioteca biblioteca;

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

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void telaUsuario(Scanner scan) {
        System.out.println("Bem vindo à biblioteca");
        int op;
        do {
            do {
                System.out.println("Digite 1 para login e 2 para cadastrar usuário");
                op = scan.nextInt();
                if (op < 1 || op > 2) {
                    System.out.println("Opção inválida");
                }
            } while (op < 1 || op > 2);
            if (op == 1) {
                scan.nextLine();
                loginUsuario(scan);
            } else {
                cadastrarUsuario(scan);
            }
        } while (biblioteca.getContaLogada() == null && biblioteca.getSuperLogado() == null);
    }

    public void loginGui (ActionEvent event, String nome, String cpf, String senha) throws InformacoesInvalidas {
        biblioteca.loginGui(nome, cpf, senha);
        telaUsuarioGui(event);

    }

    private void telaUsuarioGui(ActionEvent event) {
        if (biblioteca.getSuperLogado() != null) {
            DBUtils.changeScene(event, "MenuSuper.fxml", "MenuSuper");
        } else if (biblioteca.getContaLogada() != null) {
            DBUtils.changeScene(event, "MenuUser.fxml", "Menu");
        }

    }

    public void loginUsuario(Scanner scan) {
        System.out.println("Nome: ");
        String nome = scan.nextLine();
        System.out.println("CPF: ");
        String cpf = scan.nextLine();

        System.out.println("Senha: ");
        String senha = Arrays.toString(System.console().readPassword());
        if (biblioteca.buscarSuperUsuario(cpf)) {
            logarSuperUser(nome, cpf, senha, scan);
            return;
        }
        try {
            biblioteca.logarUsuario(nome, cpf, senha);
            menu(scan);
        } catch (InformacoesInvalidas e) {
            System.out.println(e.getMessage());
        }
    }

    public void logarSuperUser(String nome, String cpf, String senha, Scanner scan) {
        try {
            biblioteca.logarSuper(nome, cpf, senha);
            menuSuper(scan);
        } catch (InformacoesInvalidas e) {
            System.out.println(e.getMessage());
        }
        telaUsuario(scan);
    }

    public void menuSuper(Scanner scan) {
        int op;
        do {
            do {
                System.out.println("\t\t---MENU---");
                System.out.println("1) Adicionar um item");
                System.out.println("2) Buscar itens por id");
                System.out.println("3) Buscar itens por nome");
                System.out.println("4) Lista de usuários");
                System.out.println("5) Ver todos os itens da biblioteca");
                System.out.println("0) Deslogar");
                op = scan.nextInt();
                if (op < 0 || op > 5) {
                    System.out.println("Opção inválida");
                }
            } while (op < 0 || op > 5);
            scan.nextLine();
            switch (op) {
                case 1 -> biblioteca.adicionarItem();
                case 2 -> {
                    System.out.println("Digite o ID");
                    int id = scan.nextInt();
                    buscarItemId(id);
                }
                case 3 -> {
                    System.out.println("Digite o nome");
                    String nome = scan.nextLine();
                    buscarItemNome(nome);
                }
                //TODO: mensagem
                case 4 -> verificarUsers();
                case 5 -> verItensBiblio();
                case 0 -> deslogarConta(scan);
            }
        } while (op != 7);
    }

    public void verificarUsers() {
        biblioteca.listaUsuarios();
    }

    public void cadastrarUsuario(Scanner scan) {
        int tipoUsuario;
        //TODO: try catch de exceptiion
        do {
            System.out.println("Digite 1 se é aluno, 2 se é professor, 3 para Acessor Técnico ou 4 para Super Usuário");
            tipoUsuario = scan.nextInt();
        } while (tipoUsuario < 1 || tipoUsuario > 4);
        scan.nextLine();
        if (tipoUsuario == 4) {
            int cod;
            String senha;
            System.out.println("Digite o código: ");
            cod = scan.nextInt();
            scan.nextLine();
            if (cod != biblioteca.getCod()) {
                System.out.println("Inválido");
                return;
            }
            System.out.println("Digite a senha: ");
            senha = Arrays.toString(System.console().readPassword());
            if (!(senha.equals(biblioteca.getSenhaSuper()))) {
                System.out.println("Inválido");
                return;
            }
            cadastroSuper(scan);
        } else {
            System.out.println("Digite seu nome: ");
            String nome = scan.nextLine();
            System.out.println("Digite sua matrícula: ");
            int matricula = scan.nextInt();
            scan.nextLine();
            System.out.println("Digite seu CPF");
            String cpf = scan.nextLine();
            String senha;
            System.out.println("Digite sua senha: ");
            senha = Arrays.toString(System.console().readPassword());
            if (tipoUsuario == 1) {
                System.out.println("Digite seu curso: ");
                String curso = scan.nextLine();
                System.out.println("Digite seu período: ");
                String periodo = scan.nextLine();
                biblioteca.adicionarUsuario(new Aluno(nome, matricula, cpf, senha, curso, periodo));
            } else if (tipoUsuario == 2) {
                System.out.println("Digite a titulação: ");
                String titulacao = scan.nextLine();
                System.out.println("Digite o departamento: ");
                String departamento = scan.nextLine();
                biblioteca.adicionarUsuario(new Professor(nome, matricula, cpf, senha, titulacao, departamento));
            } else {
                System.out.println("Digite a Seção: ");
                String secao = scan.nextLine();
                biblioteca.adicionarUsuario(new AcessorTecnico(nome, matricula, cpf, senha, secao));
            }
            System.out.println("Usuário cadastrado com sucesso");
            telaUsuario(scan);
        }
    }

    public void cadastroSuper(Scanner scan) {
        System.out.println("Digite seu nome: ");
        String nome = scan.nextLine();
        System.out.println("Digite sua matrícula: ");
        int matricula = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite seu CPF");
        String cpf = scan.nextLine();
        String senha;
        System.out.println("Digite sua senha: ");
        senha = Arrays.toString(System.console().readPassword());
        System.out.println("Digite o código do funcionário");
        int codigoFuncionario = scan.nextInt();
        biblioteca.addFunc(new SuperUsuario(nome, matricula, cpf, senha, codigoFuncionario));
        telaUsuario(scan);
    }

    public void menu(Scanner scan) {
        int op;
        do {
            do {
                //TODO: opção de histórico
                System.out.println("\t\t---MENU---");
                System.out.println("1) Ver empréstimos");
                System.out.println("2) Ver multa");
                System.out.println("3) Renovar empréstimos");
                System.out.println("4) Pagar Multa");
                System.out.println("5) Fazer empréstimo");
                System.out.println("6) Fazer devolução");
                System.out.println("7) Buscar item por id");
                System.out.println("8) Buscar item por nome");
                System.out.println("9) Ver itens da biblioteca");
                System.out.println("10) Ver histórico");
                System.out.println("0) Deslogar");
                op = scan.nextInt();
                if (op < 0 || op > 10) {
                    System.out.println("Opção inválida");
                }
            } while (op < 0 || op > 10);
            switch (op) {
                case 1 -> consultarEmprestimos();
                case 2 -> consultarMulta();
                case 3 -> {
                    int opcaoRenovar;
                    do {
                        System.out.println("Deseja renovar todos os empréstimos (1) ou apenas um específico (2)?");
                        opcaoRenovar = scan.nextInt();
                        if (opcaoRenovar < 1 || opcaoRenovar > 2) {
                            System.out.println("Opção inválida");
                        }
                    } while (opcaoRenovar < 1 || opcaoRenovar > 2);

                    if (opcaoRenovar == 1) {
                        biblioteca.renovarTodos();
                    } else {
                        System.out.println("Digite o id do item que quer renovar");
                        int id = scan.nextInt();
                        biblioteca.renovarItem(id);
                    }
                }
                case 4 -> pagarMulta();
                case 5 -> emprestarItem();
                case 6 -> devolverItem();
                case 7 -> {
                    System.out.println("Digite o ID que quer buscar");
                    int id = scan.nextInt();
                    buscarItemId(id);
                }
                case 8 -> {
                    scan.nextLine();
                    System.out.println("Digite o nome do item");
                    String nome = scan.nextLine();
                    buscarItemNome(nome);
                }
                case 9 -> verItensBiblio();
                case 10 -> verHistorico();
                case 0 -> {
                    deslogarConta(scan);
                }
            }
        } while (op != 0);
    }
//TODO: mensagem se não achar devolução
//TODO: separador de mostrar empréstimos
    public void consultarEmprestimos() {
        biblioteca.consultarEmprestimo();
    }

    public void consultarMulta() {
        biblioteca.consultarMulta();
    }

    public void pagarMulta() {
        biblioteca.pagarMulta();
    }

    public void emprestarItem() {
        biblioteca.emprestarItem();
    }

    public void devolverItem() {
        biblioteca.devolverItem();
    }

    public void buscarItemId(int id) {
        Item item = biblioteca.buscarItem(id);
        if(item == null) {
            System.out.println("Não achou");
        } else {
            System.out.println("Item encontrado");
            if (item instanceof Revista) {
                System.out.println("Revista:");
                System.out.println(item.getId() + " " + item.getTitulo() + " " + item.getAutor() + " " + item.getAnoPublicacao() +
                        " " + ((Revista) item).getNumero() + " " + ((Revista) item).getVolume());
            }
            else if (item instanceof CD) {
                System.out.println("CD:");
                System.out.println(item.getId() + " " + item.getTitulo() + " " + item.getAutor() + " " + item.getAnoPublicacao() +
                        " " + ((CD) item).getVolume() + " " + ((CD) item).getGravadora());
            }
            else {
                System.out.println("Livro:");
                System.out.println(item.getId() + " " + item.getTitulo() + " " + item.getAutor() + " " + item.getAnoPublicacao() +
                        " " + ((Livro) item).getEditora() + " " + ((Livro) item).getIsbn());
            }
        }
    }

    public void buscarItemNome(String nome) {
        Item item =  biblioteca.buscarItem(nome);
        if(item == null) {
            System.out.println("Não achou");
        } else {
            System.out.println("Item encontrado");
            if (item instanceof Revista) {
                System.out.println("Revista:");
                System.out.println(item.getId() + " " + item.getTitulo() + " " + item.getAutor() + " " + item.getAnoPublicacao() +
                        " " + ((Revista) item).getNumero() + " " + ((Revista) item).getVolume());
            }
            else if (item instanceof CD) {
                System.out.println("CD:");
                System.out.println(item.getId() + " " + item.getTitulo() + " " + item.getAutor() + " " + item.getAnoPublicacao() +
                        " " + ((CD) item).getVolume() + " " + ((CD) item).getGravadora());
            }
            else {
                System.out.println("Livro:");
                System.out.println(item.getId() + " " + item.getTitulo() + " " + item.getAutor() + " " + item.getAnoPublicacao() +
                        " " + ((Livro) item).getEditora() + " " + ((Livro) item).getIsbn());
            }
        }
    }

    public void verItensBiblio() {
        biblioteca.verItensBiblioteca();
    }

    public void verHistorico() {biblioteca.verHistorico();}

    public void deslogarConta(Scanner scan) {
        System.out.println("Até a próxima");
        biblioteca.setContaLogada(null);
        telaUsuario(scan);
    }

    public void deslogarGUI() {
        biblioteca.setContaLogada(null);
        biblioteca.setSuperLogado(null);
    }
}
