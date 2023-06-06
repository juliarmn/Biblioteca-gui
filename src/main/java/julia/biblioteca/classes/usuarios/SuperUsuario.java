package julia.biblioteca.classes.usuarios;

/**
 * SuperUsuario é a classe que representa algum funcionário da biblioteca, que pode fazer operações diferentes de um usuário comum.
 * Ele herda a super classe Usuario.
 */

import julia.biblioteca.classes.itens.CD;
import julia.biblioteca.classes.itens.Item;
import julia.biblioteca.classes.itens.Livro;
import julia.biblioteca.classes.itens.Revista;

import java.util.Scanner;

public class SuperUsuario extends Usuario {
    /**
     * Possui um atributo inteiro que representa o código de identificação do funcionário.
     */
    private int codigoFuncionario;

    /**
     * Constructor do super usuário
     * @param nome (String) nome do usuário (herdado da Super Classe)
     * @param matricula (int) matrícula (herdado da Super Classe)
     * @param cpf (String) CPF do usuário (herdado da Super Classe)
     * @param senha (String) senha
     * @param codigoFuncionario (int) código do funcionário (específico da subclasse)
     */
    public SuperUsuario(String nome, int matricula, String cpf, String senha, int codigoFuncionario) {
        super(nome, matricula, cpf, senha);
        this.codigoFuncionario = codigoFuncionario;
    }

    /**
     * Acessa o valor inerente ao atributo de código de funcionário
     * @return (int) código do funcionário.
     */
    public int getCodigoFuncionario() {
        return codigoFuncionario;
    }

    /**
     * Modifica o valor do código de funcionário
     * @param codigoFuncionario
     */
    public void setCodigoFuncionario(int codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    /**
     * Sobrescreve o método calcularMulta da super classe
     * Como ele não terá empréstimos, ele sempre retornará 0
     * @param diferencaDias (int) -> oriundo da assinatura do método da superclasse
     * @return 0
     */
    @Override
    public double calcularMulta(long diferencaDias) {
        return 0;
    }



}
