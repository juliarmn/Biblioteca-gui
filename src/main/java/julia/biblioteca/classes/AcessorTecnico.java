package julia.biblioteca.classes;

/**
 * Acessor Técnico é uma subclasse de Uuusário, herdando seus métodos e atributos.
 * É um usuário específico  da biblioteca.
 */

public class AcessorTecnico extends Usuario{
    /**
     * Atributo que guarda o tipo da seção que o acessor está associado (String).
     */
    private String tipoDaSecao;

    /**
     * Constructor daq classe AcessorTecnico.
     * @param nome (String) guarda o nome do acessor (heardado da super classe)
     * @param matricula (int) guarda a matrícula do acessor (heardado da super classe)
     * @param cpf (String) guarda o CPF do acessor (heardado da super classe)
     * @param senha (String) guarda a senha do acessor (heardado da super classe)
     * @param tipoDaSecao (String) guarda a seção do acessor (específico da classe Professor)
     */

    public AcessorTecnico(String nome, int matricula, String cpf, String senha, String tipoDaSecao) {
        super(nome, matricula, cpf, senha);
        this.tipoDaSecao = tipoDaSecao;
    }

    /**
     * Acesso aos dados guardados no campo (atributo) tipoDaSecao
     * @return tipoDaSecao
     */

    public String getTipoDaSecao() {
        return tipoDaSecao;
    }

    /**
     * Modificar o valor do atributo tipoDaSecao
      * @param tipoDaSecao
     */
    public void setTipoDaSecao(String tipoDaSecao) {
        this.tipoDaSecao = tipoDaSecao;
    }

    /**
     * Calcula a multa do Acessor Técnico
     * Aumenta 5,15 reais por dia
     * @param quantidadeDeDias quantos dias a multa foi aplicada
     * @return multa
     */
    //TODO: void
    public double calcularMulta(long quantidadeDeDias) {
        double multa = quantidadeDeDias * 5 * 1.15;
        this.setMulta(getMulta() + multa);
        return multa;
    }
}
