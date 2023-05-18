package julia.biblioteca.classes.itens;

import julia.biblioteca.classes.itens.Item;

/**
 * Classe Revista que herda atributos e métodos da super classe Item
 */
public class Revista extends Item {
    /**
     * Atributo inteiro que representa o volume da revista
     */
    private int volume;
    /**
     * Atributo inteiro que representa o número da revista
     */
    private int numero;

    /**
     * Constructor da classe Revista
     * @param titulo (String) titulo da revista (herda da super classe)
     * @param autor (String) autor da revista (herda da super classe)
     * @param anoPublicacao (int) ano da publicação da revista (herda da super classe)
     * @param quantidadeDisponivel (int) quantidade disponível (herda da super classe)
     * @param volume (int) volume da revista (própria da subclasse)
     * @param numero (int) número da revista (própria da subclasse)
     */
    public Revista(String titulo, String autor, int anoPublicacao, int quantidadeDisponivel,
                  int volume, int numero) {
        super(titulo, autor, anoPublicacao, quantidadeDisponivel);
        this.volume = volume;
        this.numero = numero;
    }

    /**
     * Acessa o atributo de volume
     * @return volume (int)
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Permite modificar o atributo de volume
     * @param volume (inteiro) novo valor a ser assumido
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }
    /**
     * Acessa o atributo de número da revista
     * @return revista (int)
     */
    public int getNumero() {
        return numero;
    }
    /**
     * Permite modificar o atributo de número
     * @param numero (inteiro) novo valor a ser assumido
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    /**
     * Método sobrescrito sa super classe
     * Transformar o nome da classe para String (mostrar qual a classe que herdou)
     * @return String indicando a classe ("Revista")
     */
    @Override
    public String toString() {
        return "Revista";
    }
}
