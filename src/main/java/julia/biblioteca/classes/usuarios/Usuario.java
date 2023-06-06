package julia.biblioteca.classes.usuarios;

import julia.biblioteca.classes.*;
import julia.biblioteca.classes.itens.Item;
import julia.biblioteca.emprestimo.Emprestimo;
import julia.biblioteca.excessoes.InformacoesInvalidas;
import julia.biblioteca.excessoes.ItemNaoEmprestado;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Classe usuário que implementa alguns métodos
 * Classe abstrata
 */
public abstract class Usuario {
    private String nome;
    private int matricula;
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private double multa;
    private String cpf;
    private String senha;

    public Validacoes validacao = new Validacoes();

    private static int id = 0;

    /**
     * Constructor
     * @param nome String nome
     * @param matricula String matrícula
     * @param cpf String cpf
     * @param senha String senha
     */
    public Usuario(String nome, int matricula, String cpf, String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.multa = 0;
        this.cpf = cpf;
        this.senha = senha;
        id += 100;
    }

    /**
     * Retorna o CPF do usuário
     * @return String
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna o nome
     * @return String nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Modifica o nome
     * @param nome String nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a matrícula
     * @return int
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * Lista de empréstimos do usuário
     * @return a lista
     */
    public ArrayList<Emprestimo> getEmprestimo() {
        return emprestimos;
    }

    /**
     * Retorna a multa do usuário
     * @return double multa
     */
    public double getMulta() {
        return multa;
    }

    /**
     * MOdifica o valor da multa
     * @param multa double
     */
    public void setMulta(double multa) {
        this.multa = multa;
    }

    /**
     * Método abstrato para calcular multa
     * Cada subclasse implementa esse método de forma diferente
     * @param diferencaDias long
     * @return multa (double)
     */
    public abstract double calcularMulta(long diferencaDias);


    /**
     * Verifica a senha do usuário
     * @param senha String -> inserida pelo usuário
     * @return boolean
     */
    public boolean verificarSenha(String senha) {
        return (this.senha.equals(senha));
    }

    /**
     * Permite realizar o empréstimo
     * Verifica a data inserida
     * Seta a data de devolução em 10 dias
     * @param item Item a ser adicionado
     * @param dia int dia
     * @param mes int mes
     * @param ano int ano
     * @throws InformacoesInvalidas se as informações inseridas estiverem incorretas (inválidas)
     */
    public void emprestarGUI(Item item, String dia, String mes, String ano) throws InformacoesInvalidas {
        Emprestimo emprestimo;
        try {
            Date dataDeEmprestimo = new Date(Integer.parseInt(ano) - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia));
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dataDeEmprestimo);
            calendario.add(Calendar.DAY_OF_MONTH, 10);
            Date dataDeDevolucaoPrevista = calendario.getTime();
            emprestimo = new Emprestimo(item, dataDeEmprestimo, dataDeDevolucaoPrevista);
            emprestimo.setDevolvido(false);
            emprestimos.add(emprestimo);
            emprestimo.getItem().emprestarItem();
        } catch (Exception e) {
            throw new InformacoesInvalidas("Erro ao adicionar");
        }
    }

    /**
     * Método para devolver
     * Calcula a diferença de dias
     * Se passou da data calcula a multa
     * @param emprestimo Emprestimo
     * @param dia int dia
     * @param mes int mes
     * @param ano int ano
     * @throws ItemNaoEmprestado se o item devolvido não tiver sido emprestado
     */
    public void devolverGUI(Emprestimo emprestimo, String dia, String mes, String ano) throws ItemNaoEmprestado {
        emprestimo.setDataDevolucaoReal(new Date(Integer.parseInt(ano)-1900, Integer.parseInt(mes)-1, Integer.parseInt(dia)));

        if (emprestimo.getDataDevolucaoPrevista().compareTo(emprestimo.getDataDevolucaoReal()) < 0) {
            long diferencaEmMilissegundos = Math.abs(emprestimo.getDataDevolucaoReal().getTime() - emprestimo.getDataDevolucaoPrevista().getTime());
            long diferencaEmDias = TimeUnit.DAYS.convert(diferencaEmMilissegundos, TimeUnit.MILLISECONDS);

            calcularMulta(diferencaEmDias);
        }
        emprestimo.setDevolvido(true);
        emprestimo.getItem().devolverItem();
    }

    /**
     * Procura o empréstimo na conta
     * @param identificador ID
     * @return o Emprestimo achado
     * @throws ItemNaoEmprestado se o item não tiver sido emprestado
     */
    public Emprestimo procurarEmprestimo(int identificador) throws ItemNaoEmprestado {

        for (Emprestimo e : getEmprestimo()) {
            if (e.getItem().getId() == identificador) {
                return e;
            }
        }

        throw new ItemNaoEmprestado("Item não emprestado");
    }

    /**
     * Procura o empréstimo do id inserido
     * @param identificador int id
     * @return Emprestimo do item
     * @throws ItemNaoEmprestado se não tiver esse empŕestimo
     */
    public Emprestimo procurarEmprestimo(String identificador) throws ItemNaoEmprestado {

        for (Emprestimo e : getEmprestimo()) {
            if (e.getItem().getTitulo().equals(identificador)) {
                return e;
            }
        }

        throw new ItemNaoEmprestado("Item não emprestado");

    }

    /**
     * Renova todos os itens da GUI -> adiciona mais 10 dias
     * @throws ItemNaoEmprestado se não tiver empréstimos
     */
    public void renovarTodosGUI() throws ItemNaoEmprestado {

        boolean temEmprestado = false;
        for (Emprestimo emprestimo : getEmprestimo()) {
            if (!emprestimo.isDevolvido()) {
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(emprestimo.getDataDevolucaoPrevista());
                calendario.add(Calendar.DAY_OF_MONTH, 10);
                Date dataSomada = calendario.getTime();
                emprestimo.setDataDevolucaoPrevista(dataSomada);
                temEmprestado = true;
            }
        }
        if (getEmprestimo().size() == 0 || !temEmprestado) {
            throw new ItemNaoEmprestado("Não tem nenhum empréstimo na conta");
        }
    }

}

