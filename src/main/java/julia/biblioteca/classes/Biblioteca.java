package julia.biblioteca.classes;
import julia.biblioteca.classes.itens.CD;
import julia.biblioteca.classes.itens.Item;
import julia.biblioteca.classes.itens.Livro;
import julia.biblioteca.classes.itens.Revista;
import julia.biblioteca.classes.usuarios.SuperUsuario;
import julia.biblioteca.classes.usuarios.Usuario;
import julia.biblioteca.emprestimo.Emprestimo;
import julia.biblioteca.excessoes.InformacoesInvalidas;
import julia.biblioteca.excessoes.ItemIndisponivel;
import julia.biblioteca.excessoes.ItemNaoEmprestado;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Classe cuja função é o gerenciamento da biblioteca. Conecta o DisplayBanco aos Itens e Usuários.
 * Dados de toda a biblioteca (Itens que possui e usuários).
 */
public class Biblioteca {
    private String nome;
    private String cnpj;
    private Usuario contaLogada;
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    private ArrayList<SuperUsuario> funcionarios = new ArrayList<>();
    SuperUsuario superLogado;

    private String senhaSuper = "frgthyj";
    private int cod = 123456;

    /**
     * Contructor
     * @param nome da biblioteca (String)
     * @param cnpj da biblioteca (String)
     */
    public Biblioteca(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        contaLogada = null;
    }

    /**
     * Método para obter a lista de funcionários
     * @return lista de funcionários
     */
    public ArrayList<SuperUsuario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Obter o nome da biblioteca
     * @return String nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setar/ modificar o nome da biblioteca
     * @param nome String novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtem a conta logada
     * @return Uusario logado
     */
    public Usuario getContaLogada() {
        return contaLogada;
    }

    /**
     * Modifica a conta logada
     * @param contaLogada Usuario
     */
    public void setContaLogada(Usuario contaLogada) {
        this.contaLogada = contaLogada;
    }

    /**
     * Obtem a lista de itens
     * @return lista de itens
     */
    public ArrayList<Item> getItens() {
        return itens;
    }

    /**
     * Lista de usuários
     * @return lista dos usuários
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Retorna o super usuário logado
     * @return Superusuário
     */
    public SuperUsuario getSuperLogado() {
        return superLogado;
    }

    /**
     * MOdifica o valor do super logado
     * @param superLogado SuperUsuario
     */
    public void setSuperLogado(SuperUsuario superLogado) {
        this.superLogado = superLogado;
    }

    /**
     * Retorna a senha de segurança
     * @return String senha
     */
    public String getSenhaSuper() {
        return senhaSuper;
    }
    /**
     * Retorna o código de segurança
     * @return int cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * BUsca o item
     * Verifica se é apenas números que o usuário inseriu (aí porcura por ID)
     * Verifica se o usuário inseriu String com letras também -> procura por nome
     * @param titulo (String) título
     * @return Item buscado
     */
    public Item buscarItem(String titulo) {
        int id = -1;
        if (verificaNum(titulo)) {
            id = Integer.valueOf(titulo);
        }
        for(Item item : itens) {
            if (item.getTitulo().equals(titulo) || item.getId() == id) {
                return item;
            }
        }
        return null;
    }


    /**
     * Busca o item -> verifica se será por ID ou nome
     * @param busca String
     * @return Item buscado
     */
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
    /**
     * Verifica se a String contém apenas números
     * @param str (String enviada)
     * @return boolean
     */
    private boolean verificaNum(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.matches("[0-9]+");
    }

    /**
     * BUsca o empréstimo por id
     * @param id (INT)
     * @return retrna o Emprestimo buscado
     */
    public Emprestimo buscarEmprestimo(int id) {
        for(Emprestimo emprestimo : contaLogada.getEmprestimo()) {
            if (emprestimo.getItem().getId() == id && !emprestimo.isDevolvido()) {
                return emprestimo;
            }
        }
        return null;
    }



    /**
     * Adiciona o item de estrada da GUI na lista de itens da biblioteca
     * @param item Item
     */
    public void adicionarItem(Item item) {
        itens.add(item);
    }


    /**
     * Adiciona o usuário
     * @param user usuario
     */
    public void adicionarUsuario(Usuario user) {
        usuarios.add(user);
    }


    /**
     * Busca pelo CPF na lista de Super Usuários para verificar no login se é um usuário comum ou um administrador.
     * @param cpf String
     * @return boolean
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
     * Adiciona os itens na biblioteca
     * @param item Item
     */
    public void adicionarItemGUI(Item item) {
        getItens().add(item);
    }

    /**
     * Realiza o login
     * @param nome String nome para verificar
     * @param cpf String CPF para verificar
     * @param senha String senha para verificar
     * @throws InformacoesInvalidas se houver erro ao conectar
     */
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

    /**
     * Adiciona o empréstimo
     * @param item Item
     * @param dia dia
     * @param mes mes
     * @param ano ano
     * @throws InformacoesInvalidas se houver algum erro nas informações passadas
     */
    public void adicionarEmprestimo(Item item,  String dia, String mes, String ano) throws InformacoesInvalidas {
        contaLogada.emprestarGUI(item,  dia,  mes,  ano);
    }

    /**
     * Devolve o item
     * @param e Emprestimo a ser devolvido
     * @param dia dia de devolução
     * @param mes mes de devolução
     * @param ano ano de devolução
     * @throws ItemNaoEmprestado se houver algum erro nas informações passadas
     */
    public void devolverGUI(Emprestimo e, String dia, String mes, String ano) throws ItemNaoEmprestado {
       contaLogada.devolverGUI(e, dia, mes, ano);
    }

    /**
     * Procura o empréstimo
     * @param identificador int id
     * @return Emprestimo
     * @throws ItemNaoEmprestado se o item não foi emprestado
     */
    public Emprestimo procurarEmprestimo (String identificador) throws ItemNaoEmprestado {
        int id = -1;
        if (verificaNum(identificador)) {
            id = Integer.parseInt(identificador);
            return contaLogada.procurarEmprestimo(id);
        } else {
            return contaLogada.procurarEmprestimo(identificador);
        }
    }

    /**
     * Renova todos os itens -> adiciona mais 10 dias
     * @throws ItemNaoEmprestado se não tiver empresimos
     */
    public void renovarTodosGUI () throws ItemNaoEmprestado {
            contaLogada.renovarTodosGUI();
    }

    /**
     * Adiciona o usuário na lista de usuários
     * @param user Usuario
     * @throws Exception Caso o usuário já exista
     */
    public void adicionarUser(Usuario user) throws Exception {
        for (Usuario u : getUsuarios()) {
            if (u.getCpf().equals(user.getCpf()) || u.getMatricula() == user.getMatricula()) {
                throw new Exception("Já existe esse usuário");
            }
        }
        usuarios.add(user);
    }

    /**
     * Adiciona o funcionário se não tiver um usuário com esses dados
     * @param user Usuario
     * @throws Exception se já tiver alguém com esses dados
     */
    public void adicionarSuper(SuperUsuario user) throws Exception {
        for (SuperUsuario u : getFuncionarios()) {
            if (u.getCpf().equals(user.getCpf()) || u.getMatricula() == user.getMatricula()) {
                throw new Exception("Já existe esse usuário");
            }
        }
        funcionarios.add(user);
    }

    /**
     * Cadastra o super usuário
     * @param cod (int) código
     * @param senha (String) senha
     * @throws InformacoesInvalidas se já tiver um usuário com os mesmos dados
     */
    public void SuperUserAcessoCadastro(int cod, String senha) throws InformacoesInvalidas {
        if (this.cod == cod && this.senhaSuper.equals(senha)) {
            return;
        }
        else {
            throw new InformacoesInvalidas("Dados inválidos");
        }
    }
}
