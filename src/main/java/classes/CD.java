package classes;

/**
 * Classe que herda a classe Item, sendo um item específico presente na biblioteca.
 */
public class CD extends Item{
    /**
     * Atributo que guarda a informação do volume do CD.
     */
    private int volume;
    /**
     * Atributo que guarda a informação da gravadora do CD.
     */
    private String gravadora;

    /**
     * Construto de CD
     * @param titulo (String) título do CD (herdado da super classe).
     * @param autor (String) autor do CD (herdado da super classe).
     * @param anoPublicacao (int) ano em que foi publicado o CD (herdado da super classe).
     * @param quantidadeDisponivel (int) quantos CD's podem ser emprestados (herdado da super classe).
     * @param volume (int) volume do CD (específico da classe CD).
     * @param gravadora (String) gravadora do CD (específico da classe CD).
     */
    public CD(String titulo, String autor, int anoPublicacao, int quantidadeDisponivel, int volume, String gravadora) {
        super(titulo, autor, anoPublicacao, quantidadeDisponivel);
        this.volume = volume;
        this.gravadora = gravadora;
    }

    /**
     * Permite visualizar o valor do volume do CD.
     * @return (int) volume.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Permite alterar o valor do volume.
     * @param volume inteiro
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Permite visualizar o valor da gravadora do CD.
     * @return (String) gravadora.
     */
    public String getGravadora() {
        return gravadora;
    }
    /**
     * Permite alterar o nome da gravadora.
     * @param gravadora String
     */
    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }

    /**
     * Método sobrescrito sa super classe
     * Transformar o nome da classe para String (mostrar qual a classe que herdou)
     * @return String indicando a classe ("CD")
     */
    @Override
    public String toString() {
        return "CD";
    }
}
