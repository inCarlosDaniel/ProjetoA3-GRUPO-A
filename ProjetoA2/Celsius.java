package temperatura;

// Classe Celsius representa uma temperatura em graus Celsius
// Ela herda da classe Temperatura e implementa a interface Conversao
public class Celsius extends Temperatura implements Conversao {

    // Construtor que recebe um valor numérico em Celsius
    public Celsius(double valor) {
        super(valor); // Chama o construtor da superclasse Temperatura
    }

    // Construtor sobrecarregado que recebe uma String com o valor
    public Celsius(String valorStr) {
        super(valorStr); // Também chama o construtor da superclasse
    }

    // Retorna o valor em Celsius (não há conversão, pois já está em Celsius)
    @Override
    public double converterParaCelsius() {
        return getValor();
    }

    // Converte o valor de Celsius para Fahrenheit usando a fórmula: (C × 9/5) + 32
    @Override
    public double converterParaFahrenheit() {
        return (getValor() * 9 / 5) + 32;
    }

    // Converte o valor de Celsius para Kelvin usando a fórmula: C + 273.15
    @Override
    public double converterParaKelvin() {
        return getValor() + 273.15;
    }

    // Método genérico de conversão (padrão: retorna valor em Celsius)
    @Override
    public double converter() {
        return converterParaCelsius(); // Usa o próprio método de conversão para Celsius
    }
}
