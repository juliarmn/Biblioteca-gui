package julia.biblioteca.classes;

/**
 * SuperUsuario é a classe que representa algum funcionário da biblioteca, que pode fazer operações diferentes de um usuário comum.
 * Ele herda a super classe Usuario.
 */

import java.util.Scanner;

public class SuperUsuario extends Usuario{
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

    /**
     * Método que pega do usuário as informações do novo item e cria novas instâncias
     * @return novo Item para ser adicionado na biblioteca
     */

    public Item adicionarItem () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o tipo do item");
        String tipo = scan.nextLine();
        System.out.println("Digite o titulo");
        String titulo = scan.nextLine();
        System.out.println("Digite o autor");
        String autor = scan.nextLine();
        System.out.println("Digite o ano de publicação");
        int anoPublicacao = scan.nextInt();
        System.out.println("Digite a quantidade disponível");
        int quantidadeDisponivel = scan.nextInt();
        if (tipo.equals("Livro")) {
            System.out.println("Digite a editora");
            String editora = scan.nextLine();
            System.out.println("Digite o isbn");
            String isbn = scan.nextLine();
            Livro livro = new Livro(titulo, autor, anoPublicacao, quantidadeDisponivel, editora, isbn);
            return livro;
        } else if (tipo.equals("Revista")) {
            System.out.println("Digite o volume");
            int volume = scan.nextInt();
            System.out.println("Digite o número");
            int numero = scan.nextInt();
            Revista revista = new Revista(titulo, autor, anoPublicacao, quantidadeDisponivel, volume, numero);
            return revista;
        } else {
            System.out.println("Digite o volume");
            int volume = scan.nextInt();
            System.out.println("Digite a gravadora");
            String gravadora = scan.nextLine();
            CD cd = new CD(titulo, autor, anoPublicacao, quantidadeDisponivel, volume, gravadora);
            return cd;
        }
    }

}
