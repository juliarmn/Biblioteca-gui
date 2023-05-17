package julia.biblioteca.excessoes;
/*
* Exceção que verifica se o usuário possui dinheiro suficiente para pagar a multa.
 */
public class DinheiroInsuficiente extends Exception{
    /**
     * Herda um dos contructors da super classe Exception -> recebe a mensagem que tem que colocar na tela
     * @param message
     */
    public DinheiroInsuficiente(String message) {
        super(message);
    }
}
