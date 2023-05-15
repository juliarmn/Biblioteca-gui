package classes;

/**
 * Classe criada para  validar algumas informações.
 * Faz a validação a data inserida pelo usuário.
 */
public class Validacoes {
    /**
     * Valida o ano
     * A biblioteca começou em 2010
     * Estamos em 2023
     * @param ano inserido pelo usuário
     * @return boolean que indica se o usuário inseriu uma data válida
     */

    public boolean validaAno(int ano) {
        if (ano < 2010 || ano > 2023) {
            return false;
        }
        return true;
    }

    /**
     * Valida se o mê sinserido é um valor correto
     * @param mes que o usuário digitou
     * @return boolean se o mês for válido ou não
     */
    public boolean validaMes(int mes) {
        return mes >= 1 && mes <= 12;
    }

    /**
     * Valida o dia inserido pelo usuário
     * Verifica se o ano é bissexto ou não (ver se fevereiro tem 29 dias)
     * Verifica o mês inserido (ver se colocou o valor correto de dias de acordo com mês)
     * @param dia que o usuário colocou
     * @param ano inserido pelo usuário
     * @param mes inseirod pelo usuário
     * @return boolean que indica se o dia inserido está correto ou não
     */
    public boolean validaDia(int dia, int ano, int mes) {
        boolean bissexto;
        //Ano

        if (ano % 4 == 0 && ano % 100 != 0) {
            bissexto = true;
        } else if (ano % 4 !=  0 && ano % 400 == 0) {
            bissexto = true;
        } else {
            bissexto = false;
        }
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            return dia <= 31 && dia >= 1;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return dia <= 30 && dia >= 1;
        } else {
            if (bissexto) {
                return dia <= 29 && dia >= 1;
            } else {
                return dia <= 28 && dia >= 1;
            }
        }

    }
}
