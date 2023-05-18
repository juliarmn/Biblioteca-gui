package julia.biblioteca.classes.usuarios;

import julia.biblioteca.classes.*;
import julia.biblioteca.classes.itens.Item;
import julia.biblioteca.emprestimo.Emprestimo;
import julia.biblioteca.excessoes.InformacoesInvalidas;
import julia.biblioteca.excessoes.ItemNaoEmprestado;

import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class Usuario {
    private String nome;
    private int matricula;
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private double multa;
    private String cpf;
    private String senha;

    public Validacoes validacao = new Validacoes();

    private static int id = 0;

    public Usuario(String nome, int matricula, String cpf, String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.multa = 0;
        this.cpf = cpf;
        this.senha = senha;
        id += 100;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Emprestimo> getEmprestimo() {
        return emprestimos;
    }

    public void setEmprestimo(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public double getMulta() {
        return multa;
    }


    public void setMulta(double multa) {
        this.multa = multa;
    }

    //public abstract double calcularMulta();

    public abstract double calcularMulta(long diferencaDias);

    private Emprestimo buscaEmprestimo() throws ItemNaoEmprestado {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o ID do item que quer devolver: ");
        int id = scan.nextInt();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getItem().getId() == id) {
                return emprestimo;
            }
        }
        throw new ItemNaoEmprestado("Esse item não foi emprestado");
    }

    public void devolucao(Emprestimo emprestimo) throws ItemNaoEmprestado {
        Scanner scan = new Scanner(System.in);
        int dia, mes, ano;
        do {
            System.out.print("Insira o dia de hoje: ");
            dia = scan.nextInt();
            System.out.print("Insira o mês de hoje: ");
            mes = scan.nextInt();
            System.out.print("Insira o ano de hoje: ");
            ano = scan.nextInt();
            if (!validacao.validaAno(ano) || !validacao.validaMes(mes) || !validacao.validaDia(dia, ano, mes)) {
                System.out.println("Data inválida");
            }
        } while (!validacao.validaAno(ano) || !validacao.validaMes(mes) || !validacao.validaDia(dia, ano, mes));
        mes--; //ENUM
        ano -= 1900;

        emprestimo.setDataDevolucaoReal(new Date(ano, mes, dia));
        if (emprestimo.getDataDevolucaoPrevista().compareTo(emprestimo.getDataDevolucaoReal()) < 0) {
            long diferencaEmMilissegundos = Math.abs(emprestimo.getDataDevolucaoReal().getTime() - emprestimo.getDataDevolucaoPrevista().getTime());
            long diferencaEmDias = TimeUnit.DAYS.convert(diferencaEmMilissegundos, TimeUnit.MILLISECONDS);

            System.out.println("A data de devolução passou em " + diferencaEmDias + " dias");
            calcularMulta(diferencaEmDias);
            System.out.println("Você tem uma multa de " + this.multa);
        }
        emprestimo.setDevolvido(true);
    }

    public void renovarItem(int id) throws ItemNaoEmprestado {
        for (Emprestimo e : getEmprestimo()) {
            if (e.getItem().getId() == id) {
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(e.getDataDevolucaoPrevista());
                calendario.add(Calendar.DAY_OF_MONTH, 10);
                Date dataSomada = calendario.getTime();
                e.setDataDevolucaoPrevista(dataSomada);
                return;
            }
        }
        throw new ItemNaoEmprestado("Esse item não está nos seus empréstimos");
    }

    public void renovarTodos() throws ItemNaoEmprestado {
        for (Emprestimo emprestimo : getEmprestimo()) {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(emprestimo.getDataDevolucaoPrevista());
            calendario.add(Calendar.DAY_OF_MONTH, 10);
            Date dataSomada = calendario.getTime();
            emprestimo.setDataDevolucaoPrevista(dataSomada);
        }
        if (getEmprestimo() == null) {
            throw new ItemNaoEmprestado("Não tem nenhum empréstimo na conta");
        }
    }

    public void adicionarEmprestimo(Item item) {
        Emprestimo emprestimo;
        Scanner scan = new Scanner(System.in);
        int dia, mes, ano;
        do {
            System.out.print("Insira o dia de hoje: ");
            dia = scan.nextInt();
            System.out.print("Insira o mês de hoje: ");
            mes = scan.nextInt();
            System.out.print("Insira o ano de hoje: ");
            ano = scan.nextInt();
            if (!validacao.validaAno(ano) || !validacao.validaMes(mes) || !validacao.validaDia(dia, ano, mes)) {
                System.out.println("Data inválida");
            }
        } while (!validacao.validaAno(ano) || !validacao.validaMes(mes) || !validacao.validaDia(dia, ano, mes));
        mes--; //ENUM
        ano -= 1900;

        Date dataDeEmprestimo = new Date(ano, mes, dia);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dataDeEmprestimo);
        calendario.add(Calendar.DAY_OF_MONTH, 10);
        Date dataDeDevolucaoPrevista = calendario.getTime();

        emprestimo = new Emprestimo(item, dataDeEmprestimo, dataDeDevolucaoPrevista);
        emprestimo.setDevolvido(false);
        emprestimos.add(emprestimo);
    }

    public boolean verificarSenha(String senha) {
        return (this.senha.equals(senha));
    }



    public void verHistorico() {
        for (Emprestimo emprestimo : getEmprestimo()) {
            if (emprestimo.isDevolvido()) ;
        }
    }

    public void emprestarGUI(Item item, String dia, String mes, String ano) throws InformacoesInvalidas {
        Emprestimo emprestimo;
        try {
            Date dataDeEmprestimo = new Date(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
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

    public void devolverGUI(Emprestimo emprestimo, String dia, String mes, String ano) throws ItemNaoEmprestado {
        emprestimo.setDataDevolucaoReal(new Date(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia)));

        if (emprestimo.getDataDevolucaoPrevista().compareTo(emprestimo.getDataDevolucaoReal()) < 0) {
            long diferencaEmMilissegundos = Math.abs(emprestimo.getDataDevolucaoReal().getTime() - emprestimo.getDataDevolucaoPrevista().getTime());
            long diferencaEmDias = TimeUnit.DAYS.convert(diferencaEmMilissegundos, TimeUnit.MILLISECONDS);

            calcularMulta(diferencaEmDias);
        }
        emprestimo.setDevolvido(true);
        emprestimo.getItem().devolverItem();
    }

    public Emprestimo procurarEmprestimo(int identificador) throws ItemNaoEmprestado {

        for (Emprestimo e : getEmprestimo()) {
            if (e.getItem().getId() == identificador) {
                return e;
            }
        }

        throw new ItemNaoEmprestado("Item não emprestado");
    }

    public Emprestimo procurarEmprestimo(String identificador) throws ItemNaoEmprestado {

        for (Emprestimo e : getEmprestimo()) {
            if (e.getItem().getTitulo().equals(identificador)) {
                return e;
            }
        }

        throw new ItemNaoEmprestado("Item não emprestado");

    }

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

