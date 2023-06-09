package julia.biblioteca.classes.usuarios;

/**
 *  Aluno é uma subclasse específica de usuário.
 *  É um dos tipos de usuário da biblioteca.
 */
public class Aluno extends Usuario {
    /**
     * String que guarda a informação do curso do aluno.
     */
    private String curso;
    /**
     * String que guarda a informação do período que o aluno cursa.
     */
    private String periodo;

    /**
     * Constructor daq classe Aluno.
     * @param nome (String) guarda o nome do aluno (heardado da super classe)
     * @param matricula (int) guarda a matrícula do aluno (heardado da super classe)
     * @param cpf (String) guarda o CPF do aluno (heardado da super classe)
     * @param senha (String) guarda a senha do aluno (heardado da super classe)
     * @param curso (String) guarda o curso do aluno (específico da classe Professor)
     * @param periodo (String) guarda o período que o aluno cursa (específico da classe Professor)
     */
    public Aluno(String nome, int matricula, String cpf, String senha, String curso, String periodo) {
        super(nome, matricula, cpf, senha);
        this.curso = curso;
        this.periodo = periodo;
    }

    /**
     * Calcula a multa do aluno durante os dias desde a data prevista de devolução (caso ele não devolva o item).
     * É 5 reais por dia.
     * @param quantidadeDeDias (long) Quantidade de dia que a multa foi contada
     * @return multa gerada.
     */
    public double calcularMulta(long quantidadeDeDias) {
        double multa = quantidadeDeDias * 5;
        this.setMulta(getMulta() + multa);
        return multa;
    }

    /**
     * Consegur imprimir seu tipo (tipo do usuário)
     * @return String indicando o tipo
     */
    @Override
    public String toString() {
        return "Aluno";
    }
}
