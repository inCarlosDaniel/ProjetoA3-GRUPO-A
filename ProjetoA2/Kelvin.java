package temperatura;

/**
 * Classe que representa uma temperatura em Kelvin.
 * Estende a classe abstrata Temperatura e implementa a interface Conversao.
 */
public class Kelvin extends Temperatura implements Conversao {

    /**
     * Construtor que recebe um valor numérico em Kelvin.
     * @param valor Valor da temperatura em Kelvin.
     */
    public Kelvin(double valor) {
        super(valor);
    }

    /**
     * Construtor sobrecarregado que recebe o valor da temperatura como String.
     * A string é convertida para double pela superclasse Temperatura.
     * @param valorStr Valor da temperatura em Kelvin como texto.
     */
    public Kelvin(String valorStr) {
        super(valorStr);
    }

    /**
     * Converte a temperatura de Kelvin para Celsius.
     * Fórmula: K - 273.15
     * @return Temperatura convertida em Celsius.
     */
    @Override
    public double converterParaCelsius() {
        return getValor() - 273.15;
    }

    /**
     * Converte a temperatura de Kelvin para Fahrenheit.
     * Fórmula: (K - 273.15) × 9/5 + 32
     * @return Temperatura convertida em Fahrenheit.
     */
    @Override
    public double converterParaFahrenheit() {
        return (getValor() - 273.15) * 9 / 5 + 32;
    }

    /**
     * Retorna o próprio valor em Kelvin.
     * @return Temperatura em Kelvin.
     */
    @Override
    public double converterParaKelvin() {
        return getValor();
    }

    /**
     * Método genérico de conversão (padrão para Celsius).
     * Pode ser usado quando a unidade de destino não for especificada.
     * @return Temperatura convertida para Celsius.
     */
    @Override
    public double converter() {
        return converterParaCelsius();
    }
}
