package temperatura;

/**
 * Classe abstrata que representa uma temperatura genérica.
 * Serve como base para classes específicas como Celsius, Fahrenheit e Kelvin.
 */
public abstract class Temperatura {
    // Atributo que armazena o valor numérico da temperatura
    private double valor;

    /**
     * Construtor padrão que recebe um valor numérico.
     * @param valor Valor da temperatura.
     */
    public Temperatura(double valor) {
        this.valor = valor;
    }

    /**
     * Construtor sobrecarregado que recebe o valor da temperatura como String.
     * A string é convertida para double, aceitando tanto "." quanto "," como separador decimal.
     * @param valorStr Valor da temperatura como texto.
     */
    public Temperatura(String valorStr) {
        this.valor = Double.parseDouble(valorStr.replace(",", "."));
    }

    /**
     * Método getter para acessar o valor da temperatura.
     * @return Valor da temperatura.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Método setter para alterar o valor da temperatura com um double.
     * @param valor Novo valor da temperatura.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Método setter sobrecarregado que permite alterar o valor da temperatura com uma String.
     * A string é convertida para double.
     * @param valorStr Novo valor da temperatura como texto.
     */
    public void setValor(String valorStr) {
        this.valor = Double.parseDouble(valorStr.replace(",", "."));
    }

    /**
     * Método abstrato que será implementado pelas subclasses.
     * Deve retornar a conversão padrão da temperatura (geralmente para Celsius).
     * @return Temperatura convertida.
     */
    public abstract double converter();
}
