package classes;
/*
 * Interface que declara as assinaturas dos métodos para emprestar um item e devolver um item.
 * Ainda, existem as excessões que serão lançadas por cada método.
 */

import excessoes.ItemIndisponivel;
import excessoes.ItemNaoEmprestado;

public interface Emprestavel {
    boolean emprestarItem() throws ItemIndisponivel;
    boolean devolverItem() throws ItemNaoEmprestado;
}
