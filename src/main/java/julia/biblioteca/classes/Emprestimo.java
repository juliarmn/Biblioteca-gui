package julia.biblioteca.classes;

import java.util.Date;

public class Emprestimo {

    private Item item;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoReal;
    private boolean devolvido;

    public Emprestimo(Item item, Date dataEmprestimo, Date dataDevolucaoPrevista) {
        this.item = item;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Date getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(Date dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public boolean isDevolvido() {
        return devolvido;
    }
}
