package julia.biblioteca.excessoes;

/**
 * Exceção que verifica se as informações colocadas em algum formulário é válida ou não (se existe em algum "banco de dados" -> são só dados salvos previamente)
 */
public class InformacoesInvalidas extends Exception{
    /**
     * Herda um dos contructors da super classe Exception -> recebe a mensagem que tem que colocar na tela
     * @param message
     */

    public InformacoesInvalidas(String message) {
        super(message);
    }

}
