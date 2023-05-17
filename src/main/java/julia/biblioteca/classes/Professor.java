package julia.biblioteca.classes;

/**
 * A classe Professor é uma subclasse da classe Usuario, herdando seus atributos e métodos.
 * É uma classe mais específica que define alguns atributos apenas dos professores.
 */
public class Professor extends Usuario {
    /**
     * String que salva a titulação do professor.
     */
    private String titulacao;
    /**
     * String que salva o departamento do professor.
     */
    private String departamento;

    /**
     * Construtor da classe Professor
     * @param nome (String) guarda o nome do professor (heardado da super classe)
     * @param matricula (int) guarda a matrícula do professor (heardado da super classe)
     * @param cpf (String) guarda o CPF do professor (heardado da super classe)
     * @param senha (String) guarda a senha do professor (heardado da super classe)
     * @param titulacao (String) guarda a titulação do professor (específico da classe Professor)
     * @param departamento (String) guarda o departamento do professor (específico da classe Professor)
     */
    public Professor(String nome, int matricula, String cpf, String senha, String titulacao, String departamento) {
        super(nome, matricula, cpf, senha);
        this.titulacao = titulacao;
        this.departamento = departamento;
    }

    /**
     * Calcula a multa específica para o professor (taxa de 25%)
     * A cada dia aumenta 5.25 reais
     * @param quantidadeDeDias (long) quantidade de dias que houve cobrança daq multa
     * @return multa gerada
     */
    public double calcularMulta(long quantidadeDeDias) {
        double multa = quantidadeDeDias * 5 * 1.25;
        this.setMulta(getMulta() + multa);
        return multa;
    }
}
