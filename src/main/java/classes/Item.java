package classes;
import excessoes.ItemIndisponivel;
import excessoes.ItemNaoEmprestado;

/**
 * Classe abstrata que implementa a interface Emprestável.
 * Super classe de todos os itens da biblioteca.
 * Informações gerais compartilhadas por todos os itens específicos da biblioteca.
 */

public abstract class Item implements Emprestavel {
    /**
     * String que guarda o título do item (é uma forma de identifcar).
     */
    private String titulo;
    /**
     * Guarda o nome do autor (String).
     */
    private String autor;
    /**
     * Guarda a informação do ano em que o Item foi publicado.
     */
    private int anoPublicacao;
    /**
     * Guarda a informação de quantos itens estão disponíveis para empréstimo.
     */
    private int quantidadeDisponivel;
    /**
     * Guarda a infromação de quantos itens foram emprestados.
    */
    private int quantidadeEmprestada;

    private static int count = 0;
    private int id;

    /**
     * Constructor da classe Item.
     * id é único para cada item, para contar de forma crescente uso o static count.
     * Quantidade emprestada não entra porque ainda nada foi emprestado.
     * @param titulo (String) indica o título do item.
     * @param autor (String) autor do item.
     * @param anoPublicacao (int) ano de publicação.
     * @param quantidadeDisponivel (int) quantos itens desse tipo podem ser emprestados.
     */
    public Item(String titulo, String autor, int anoPublicacao, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        id = count;
        count += 100;
    }

    /**
     * Acessar o valor do título do objeto.
     * @return titulo (String)
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Modifica o valor presente no atributo de título.
     * @param titulo (String) novo valor.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Acessar o valor do autor do objeto.
     * @return autor (String)
     */

    public String getAutor() {
        return autor;
    }
    /**
     * Modifica o valor presente no atributo de autor.
     * @param autor (String) novo valor.
     */

    public void setAutor(String autor) {
        this.autor = autor;
    }
    /**
     * Acessar o valor armazenado do ano de publicação do objeto.
     * @return ano de publicação (int)
     */
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    /**
     * Modifica o valor presente no atributo de ano de publicação.
     * @param anoPublicacao (int) novo valor.
     */
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    /**
     * Acessar o valor da quantidade disponível do objeto.
     * @return quantidade disponível (int)
     */
    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    /**
     * Modifica o valor presente no atributo de quantidade disponível.
     * @param quantidadeDisponivel (int) novo valor.
     */
    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
    /**
     * Acessar o valor do atributo que representa a quantidade emprestada do objeto.
     * @return quantidade emprestada (int)
     */
    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }
    /**
     * Modifica o valor presente no atributo de quantidade emprestada.
     * @param quantidadeEmprestada (int) novo valor.
     */
    public void setQuantidadeEmprestada(int quantidadeEmprestada) {
        this.quantidadeEmprestada = quantidadeEmprestada;
    }
    /**
     * Acessar o valor do id do objeto.
     * @return id (int)
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica o valor presente no atributo de id.
     * @param id (int) novo valor.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sobrescreve o método da interface e lança uma nova Exceção (Se o item não está disponível).
     * Diminui em 1 unidade a quantidade dispoinível e aumenta em 1 a quantidade emprestada.
     * @return boolean que representa se conseguiu ou não emprestar.
     * @throws ItemIndisponivel Exceção que verifica se tem aquele item para emprestar.
     */
    @Override
    public boolean emprestarItem() throws ItemIndisponivel {
        if (getQuantidadeDisponivel() == 0) {
            throw new ItemIndisponivel("Esse item está indisponível");
        } else {
            setQuantidadeDisponivel(getQuantidadeDisponivel() - 1);
            setQuantidadeEmprestada(getQuantidadeDisponivel() + 1);
            return true;
        }
    }

    /**
     * Sobrescreve o método a interface e lança uma nova Exceção (se o item não foi emprestado).
     * aumenta em 1 unidade a quantidade dispoinível e diminui em 1 a quantidade emprestada.
     * @return boolean que indica se  devolveu ou não o item.
     * @throws ItemNaoEmprestado verifica se o item foi emprestado ou se o usuário está tentando devolver algo que não pegou emprestado.
     */
    @Override
    public boolean devolverItem() throws ItemNaoEmprestado {
        boolean result = true;
        if (getQuantidadeEmprestada() == 0) {
            throw new ItemNaoEmprestado("Esse item não foi emprestado");
        } else {
            setQuantidadeDisponivel(getQuantidadeDisponivel() + 1);
            setQuantidadeEmprestada(getQuantidadeEmprestada() - 1);
        }
        return result;
    }


}
