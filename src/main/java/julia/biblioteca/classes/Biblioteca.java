package julia.biblioteca.classes;

/**
 * Classe cuja função é o gerenciamento da biblioteca. Conecta o DisplayBanco aos Itens e Usuários.
 * Dados de toda a biblioteca (Itens que possui e usuários).
 */

import julia.biblioteca.excessoes.InformacoesInvalidas;
import julia.biblioteca.excessoes.ItemIndisponivel;
import julia.biblioteca.excessoes.ItemNaoEmprestado;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    private String nome;
    private String cnpj;
    private Usuario contaLogada;
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    private ArrayList<SuperUsuario> funcionarios = new ArrayList<>();
    SuperUsuario superLogado;

    private String senhaSuper = "anadfthu";
    private int cod = 1987666;
    public Biblioteca(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        contaLogada = null;
    }

    public ArrayList<SuperUsuario> getFuncionarios() {
        return funcionarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Usuario getContaLogada() {
        return contaLogada;
    }

    public void setContaLogada(Usuario contaLogada) {
        this.contaLogada = contaLogada;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public SuperUsuario getSuperLogado() {
        return superLogado;
    }

    public void setSuperLogado(SuperUsuario superLogado) {
        this.superLogado = superLogado;
    }

    public String getSenhaSuper() {
        return senhaSuper;
    }

    public int getCod() {
        return cod;
    }

    public void mostrarItens() {
        for(Item item : itens) {
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
            System.out.println("--------------------------------------");
        }
    }

    public Item buscarItem(String titulo) {
        int id = -1;
        if (verificaNumero(titulo)) {
            id = Integer.valueOf(titulo);
        }
        for(Item item : itens) {
            if (item.getTitulo().equals(titulo) || item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    private static boolean verificaNumero(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.matches("[0-9]+");
    }
    public Item buscarItem(int id) {
        for(Item item : itens) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Item buscarItemGui(String busca) {
        int id = -1;
        if (verificaNum(busca)) {
            id = Integer.parseInt(busca);
        }
        for(Item item : itens) {
            if (item.getId() == id || item.getTitulo().equals(busca)) {
                return item;
            }
        }
        return null;
    }

    private boolean verificaNum(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.matches("[0-9]+");
    }

    public Emprestimo buscarEmprestimo(int id) {
        for(Emprestimo emprestimo : contaLogada.getEmprestimo()) {
            if (emprestimo.getItem().getId() == id && !emprestimo.isDevolvido()) {
                return emprestimo;
            }
        }
        return null;
    }

    public void emprestarItem() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do item que queres emprestar");
        String nome = scan.nextLine();
        Item item = buscarItem(nome);
        if (item != null) {
            if (contaLogada.getMulta() > 0) {
                System.out.println("Não pode emprestar (MULTA PENDENTE!!!)");
            } else {
                try {
                    item.emprestarItem();
                    contaLogada.adicionarEmprestimo(item);
                } catch (ItemIndisponivel e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void devolverItem() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o id do item que quer devolver");
        int id = scan.nextInt();
        Emprestimo emprestimo = buscarEmprestimo(id);
        if (emprestimo != null) {
                try {
                    emprestimo.getItem().devolverItem();
                    contaLogada.devolucao(emprestimo);
                } catch (ItemNaoEmprestado e) {
                    System.out.println(e.getMessage());
            }
        }
    }

    public void addFunc(SuperUsuario user) {
        funcionarios.add(user);
    }
    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public boolean logarUsuario(String nome, String cpf, String senha) throws InformacoesInvalidas {

            for (SuperUsuario user : getFuncionarios()) {
                if (user.getNome().equals(nome) && user.getCpf().equals(cpf)) {
                    if (user.verificarSenha(senha)) {
                        this.contaLogada = user;
                        return true;
                    } else {
                        throw new InformacoesInvalidas("Não foi possível logar (senha inválida)");
                    }
                }
            }
        throw new InformacoesInvalidas("Não foi possível logar (informações inválidas)");
    }

    public void adicionarUsuario(Usuario user) {
        usuarios.add(user);
    }

    public void consultarEmprestimo() {
        for (Emprestimo emprestimo : contaLogada.getEmprestimo()) {
            if (!emprestimo.isDevolvido()) {
                System.out.println("Tipo de emprestimo: " + emprestimo.getItem().toString());
                System.out.println("Título: " + emprestimo.getItem().getTitulo());
                System.out.println("Autor: " + emprestimo.getItem().getAutor());
                System.out.println("ID: " + emprestimo.getItem().getId());
                System.out.println("Ano publicação" + emprestimo.getItem().getAnoPublicacao());
                System.out.println("Data de emprestimo: " + emprestimo.getDataEmprestimo());
                System.out.println("Data de devolução prevista: " + emprestimo.getDataDevolucaoPrevista());
            }
        }
        if (contaLogada.getMulta() > 0) {
            System.out.println("Existem multas pendentes");
        }
    }

    public void consultarMulta() {
        if (contaLogada.getMulta() == 0) {
            System.out.println("Usuário não possui nenhuma multa pendente");
        } else {
            System.out.println("Sua multa é de " + contaLogada.getMulta() + " reais");
        }
    }

    public void renovarTodos() {
        try {
            contaLogada.renovarTodos();
        } catch (ItemNaoEmprestado e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Função que renova 1 item por ID
     * Procura e vẽ se esse item existe na biblioteca.
     * @param id
     */
//TODO: arrumar aqui
    public void renovarItem (int id) {
        for (Item item : getItens()) {
            if (item.getId() == id) {//Existe pelo menos o item com esse id
                try {
                    getContaLogada().renovarItem(id);
                } catch (ItemNaoEmprestado e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Função que verifca se existe multa na contaLogada.
     * Caso exista, chama o método de pagar multa (Classe do Usuário).
     * Caso não, envia uma mensagem e sai do método.
     */

    public void pagarMulta()  {
        if (this.contaLogada.getMulta() == 0) {
            System.out.println("Não há multa a pagar");
            return;
        }
        this.contaLogada.pagarMulta();
    }

    /**
     * Busca pelo CPF na lista de Super Usuários para verificar no login se é um usuário comum ou um administrador.
     * @param cpf
     * @return
     */

    public boolean buscarSuperUsuario(String cpf) {
        for (SuperUsuario user : funcionarios) {
            if(cpf.equals(user.getCpf())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Faz um set que muda o valor do SuperLogado para o valor do Objeto de super usuário que foi logado de acordo com as informções fornecidas.
     * @param nome String (compara com as Strings da lista de super usuários já cadastrados)
     * @param cpf String (compara com os CPF's da lista de super usuários já cadastrados)
     * @param senha String (compara com as senhas da lista de super usuários já cadastrados)
     * @throws InformacoesInvalidas se algum dos dados fornecidos não existir comoum super usuário da biblioteca, lança a excessão
     */
    public void logarSuper(String nome, String cpf, String senha) throws InformacoesInvalidas {
        for (SuperUsuario user : getFuncionarios()) {
            if (user.getNome().equals(nome) && user.getCpf().equals(cpf)) {
                if (user.verificarSenha(senha)) {
                    this.superLogado = user;
                    return;
                } else {
                    throw new InformacoesInvalidas("Não foi possível logar (senha inválida)");
                }
            }
        }
        throw new InformacoesInvalidas("Não foi possível logar (informações inválidas)");
    }

    /**
     * Lista todeos os itens presentes na biblioteca e algumas de suas informações.
     */
    public void verItensBiblioteca() {
        for (Item item : getItens()) {
            System.out.println("Livro: ");
            System.out.println("Titulo: " + item.getTitulo());
            System.out.println("Autor: " + item.getAutor());
            System.out.println("ID: " + item.getId());
            System.out.println("Ano publicação: " + item.getAnoPublicacao());
            if (item instanceof Livro) {
                System.out.println("Editora" + ((Livro) item).getEditora());
                System.out.println("ISBN" + ((Livro) item).getIsbn());
            } else if (item instanceof Revista) {
                System.out.println("Volume: " + ((Revista) item).getVolume());
                System.out.println("Número: " + ((Revista) item).getNumero());
            } else {
                System.out.println("Volume: " + ((CD)item).getVolume());
                System.out.println("Gravadora: " + ((CD)item).getGravadora());
            }
        }
    }

    public void verHistorico() {
        contaLogada.verHistorico();
    }

    /**
     * Lista os usuários para uma função da Super Usuário.
     * Por questões de privacidade não mostra todas as informações, mas apenas o nome e matrícula para ver quais usuários estão cadastrados.
     */
    public void listaUsuarios() {
        for (Usuario user : this.usuarios) {
            System.out.println("Nome" + user.getNome());
            System.out.println("matrícula" + user.getMatricula());
            System.out.println("---------------------------");
        }
    }

    /**
     * Adicioana o item à lista de itens da biblioteca.
     */
    public void adicionarItem() {
        getItens().add(this.superLogado.adicionarItem());
    }

    //Métodos para GUI
    public void loginGui(String nome,String cpf, String senha) throws InformacoesInvalidas {
        for (SuperUsuario s : getFuncionarios()) {
            if (s.getCpf().equals(cpf) && s.getNome().equals(nome)) {
                if (s.verificarSenha(senha)) {
                    this.superLogado = s;
                    return;
                } else {
                    throw new InformacoesInvalidas("Senha incorreta");
                }
            }
        }
        for (Usuario u : getUsuarios()) {
            if (u.getCpf().equals(cpf) && u.getNome().equals(nome)) {
                if (u.verificarSenha(senha)) {
                    this.contaLogada = u;
                    return;
                } else {
                    throw new InformacoesInvalidas("Senha incorreta");
                }
            }
        }
        throw new InformacoesInvalidas("Usuário não encontrado");
    }

    public void adicionarEmprestimo(Item item,  String dia, String mes, String ano) throws InformacoesInvalidas {
        contaLogada.emprestarGUI(item,  dia,  mes,  ano);
    }

    public void devolverGUI(Emprestimo e, String dia, String mes, String ano) throws ItemNaoEmprestado {
       contaLogada.devolverGUI(e, dia, mes, ano);
    }

    public Emprestimo procurarEmprestimo (String identificador) throws ItemNaoEmprestado {
        int id = -1;
        if (verificaNum(identificador)) {
            id = Integer.parseInt(identificador);
            return contaLogada.procurarEmprestimo(id);
        } else {
            return contaLogada.procurarEmprestimo(identificador);
        }
    }
    public void renovarTodosGUI () throws ItemNaoEmprestado {
            contaLogada.renovarTodosGUI();
    }

    public void adicionarUser(Usuario user) throws Exception {
        for (Usuario u : getUsuarios()) {
            if (u.getCpf().equals(user.getCpf()) || u.getMatricula() == user.getMatricula()) {
                throw new Exception("Já existe esse usuário");
            }
        }
        usuarios.add(user);
    }
    public void adicionarSuper(SuperUsuario user) throws Exception {
        for (SuperUsuario u : getFuncionarios()) {
            if (u.getCpf().equals(user.getCpf()) || u.getMatricula() == user.getMatricula()) {
                throw new Exception("Já existe esse usuário");
            }
        }
        usuarios.add(user);
    }

    public void SuperUserAcessoCadastro(int cod, String senha) throws InformacoesInvalidas {
        if (this.cod == cod && this.senhaSuper.equals(senha)) {
            return;
        }
        else {
            throw new InformacoesInvalidas("Dados inválidos");
        }
    }
}
