package julia.biblioteca.excessoes;

/**
 * Exceção que verifica se um item que está tentando ser devolvido foi emprestado ou não
 */
public class ItemNaoEmprestado extends Exception{
    /**
     * Herda um dos contructors da super classe Exception -> recebe a mensagem que tem que colocar na tela
     * @param message
     */
    public ItemNaoEmprestado(String message) {
        super(message);
    }
}
