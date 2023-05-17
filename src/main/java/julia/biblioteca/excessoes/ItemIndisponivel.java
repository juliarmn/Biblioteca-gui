package julia.biblioteca.excessoes;

/**
 * Exceção que verifica se o item escolhido ainda tem disponibilidade para ser emprestado.
 */
public class ItemIndisponivel extends Exception{
    /**
     * Herda um dos contructors da super classe Exception -> recebe a mensagem que tem que colocar na tela
     * @param message
     */
    public ItemIndisponivel(String message) {
        super(message);
    }
}
