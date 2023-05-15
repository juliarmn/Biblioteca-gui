package classes;

import excessoes.DinheiroInsuficiente;
import excessoes.InformacoesInvalidas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConectaBanco {

    private static ArrayList<ConectaConta> contas = new ArrayList<>();
    private static ConectaConta contaLogadaBanco;
    public ConectaBanco(String banco) throws Exception{
        File arquivo = new File(banco);
        Scanner scan;
        try {
            scan = new Scanner(arquivo);
        } catch (FileNotFoundException ex) {
            throw new Exception("Sem arquivo de banco");
        }

        while (scan.hasNextLine()) {
            //Ler a linha
            String linha = scan.nextLine();
            //Separar os elementos pelo #
            String[] campos = linha.split("#");
            //Shadowing:
            String nome = campos[0];
            String cpf = campos[1];
            String senha = campos[2];
            int numCartao = Integer.parseInt(campos[3]);
            int cvv = Integer.parseInt(campos[4]);
            int anoCartao = Integer.parseInt(campos[5]);
            double valorNaConta = Double.parseDouble(campos[6]);
            contas.add(new ConectaConta(nome, cpf, senha, numCartao, cvv, anoCartao, valorNaConta));
        }
    }

    public static double pagarPorPix(Scanner scan) {
        System.out.println("Digite a chave: ");
        String chave = scan.nextLine();
        System.out.println("Digite o valor do pix: ");
        double valor = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite a senha: ");
        String senha = scan.nextLine();
        try {
            contaLogadaBanco.pix(valor, chave, senha);
            return valor;
        } catch (InformacoesInvalidas | DinheiroInsuficiente e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static double pagarCredito(double valor, String cpf, String senha, int cvv, int ano, int numCartao) {
        try {
            return contaLogadaBanco.pagarNoCredito(valor, cpf, senha, cvv, ano, numCartao);
        } catch (InformacoesInvalidas | DinheiroInsuficiente e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static ConectaConta conectarConta (Scanner scan) throws InformacoesInvalidas {
        System.out.println("Digite seu cpf: ");
        String cpf = scan.nextLine();
        System.out.println("Digite sua senha: ");
        String senha = Arrays.toString(System.console().readPassword());
        for (ConectaConta conta : contas) {
            if (conta.getCpf().equals(cpf) && conta.validaSenha(senha)) {
                contaLogadaBanco = conta;
                return conta;
            }
        }
        throw new InformacoesInvalidas("Erro ao entrar na conta");
    }

    public static void desconectarConta() {
        contaLogadaBanco = null;
    }
}
