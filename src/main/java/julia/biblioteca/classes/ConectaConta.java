package julia.biblioteca.classes;

import julia.biblioteca.excessoes.DinheiroInsuficiente;
import julia.biblioteca.excessoes.InformacoesInvalidas;

public class ConectaConta {
    private String nome;
    private String cpf;
    private String senha;
    private int numCartao;
    private int cvv;
    private int anoCartao;
    private double valorNaConta;

    public ConectaConta(String nome, String cpf, String senha, int numCartao, int cvv, int anoCartao, double valorNaConta) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.numCartao = numCartao;
        this.cvv = cvv;
        this.anoCartao = anoCartao;
        this.valorNaConta = valorNaConta;
    }


    public boolean pix(double valor, String cnpj, String senhaDigitada) throws DinheiroInsuficiente, InformacoesInvalidas {
        if (cnpj.equals("63.486.153/0001-00") && this.senha.equals(senhaDigitada)) {
            if (this.valorNaConta >= valor) {
                this.valorNaConta -= valor;
                return true;
            } else {
                throw new DinheiroInsuficiente("Dinheiro insuficiente na conta");
            }
        } else {
           throw new InformacoesInvalidas("Informações inválidas");
        }
    }
//TODO: VALIDAR OS MÊS CORRETAMENTE
    public double pagarNoCredito(double valor, String cpf, String senha, int cvv, int ano, int numCartao ) throws DinheiroInsuficiente, InformacoesInvalidas {
        if (this.cpf.equals(cpf) && this.senha.equals(senha) && this.cvv == cvv && this.anoCartao == ano && this.numCartao == numCartao) {
            if(this.valorNaConta >= valor) {
                this.valorNaConta -= valor;
                return valor;
            } else {
                throw new DinheiroInsuficiente("Saldo do cartão insuficiente");
            }
        } else {
            throw new InformacoesInvalidas("Informações inválidas");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public boolean validaSenha(String senha) {
        return this.senha.equals(senha);
    }
}
