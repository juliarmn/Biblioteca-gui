package julia.biblioteca.classes;

/**
 * Livro é uma subclasse de Item (super classe)
 * herda seus atributos e métodos
 */
public class Livro extends Item{
    /**
     * Guarda o valor da editora (String).
     */
    private String editora;
    /**
     * Guarda o valor do ISBN.
     */
    private String isbn;

    /**
     * Constructor da classe Livro
     * @param titulo (String) título do CD (herdado da super classe).
     * @param autor (String) autor do CD (herdado da super classe).
     * @param anoPublicacao (int) ano em que foi publicado o CD (herdado da super classe).
     * @param quantidadeDisponivel (int) quantos CD's podem ser emprestados (herdado da super classe).
     * @param editora (String) editora do livro (da subclasse).
     * @param isbn (int) valor do isbn do livro (da subclasse).
     */
    public Livro(String titulo, String autor, int anoPublicacao, int quantidadeDisponivel, String editora, String isbn) {
        super(titulo, autor, anoPublicacao, quantidadeDisponivel);
        this.editora = editora;
        this.isbn = isbn;
    }
    /**
     * Permite visualizar o valor da editora.
     * @return (String) editora.
     */
    public String getEditora() {
        return editora;
    }
    /**
     * Permite alterar o valor do editora.
     * @param editora (String) -> novo valor de editora
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }
    /**
     * Permite visualizar o valor do isbn.
     * @return (String) isbn.
     */
    public String getIsbn() {
        return isbn;
    }
    /**
     * Permite alterar o valor do isbn.
     * @param isbn (String) -> novo valor de isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    /**
     * Método sobrescrito sa super classe
     * Transformar o nome da classe para String (mostrar qual a classe que herdou)
     * @return String indicando a classe ("CD")
     */
    @Override
    public String toString() {
        return "Livro";
    }
}
