package temperatura;

/**
 * Classe que representa uma temperatura em Fahrenheit.
 * Estende a classe abstrata Temperatura e implementa a interface Conversao.
 */
public class Fahrenheit extends Temperatura implements Conversao {

    /**
     * Construtor que recebe um valor em Fahrenheit (double).
     * @param valor Valor da temperatura em Fahrenheit.
     */
    public Fahrenheit(double valor) {
        super(valor);
    }

    /**
     * Construtor sobrecarregado que recebe uma string com o valor em Fahrenheit.
     * A string é convertida para double no construtor da superclasse.
     * @param valorStr Valor da temperatura em Fahrenheit como String.
     */
    public Fahrenheit(String valorStr) {
        super(valorStr);
    }

    /**
     * Converte a temperatura de Fahrenheit para Celsius.
     * Fórmula: (F - 32) × 5/9
     * @return Temperatura em Celsius.
     */
    @Override
    public double converterParaCelsius() {
        return (getValor() - 32) * 5 / 9;
    }

    /**
     * Retorna o próprio valor, pois já está em Fahrenheit.
     * @return Temperatura em Fahrenheit.
     */
    @Override
    public double converterParaFahrenheit() {
        return getValor();
    }

    /**
     * Converte a temperatura de Fahrenheit para Kelvin.
     * Fórmula: (F - 32) × 5/9 + 273.15
     * @return Temperatura em Kelvin.
     */
    @Override
    public double converterParaKelvin() {
        return (getValor() - 32) * 5 / 9 + 273.15;
    }

    /**
     * Método genérico de conversão, que neste caso retorna o valor em Celsius.
     * @return Temperatura em Celsius (comportamento padrão).
     */
    @Override
    public double converter() {
        return converterParaCelsius();
    }
}
